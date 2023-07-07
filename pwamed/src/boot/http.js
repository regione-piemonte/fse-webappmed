/*
 * Copyright Regione Piemonte - 2023
 * SPDX-License-Identifier: EUPL-1.2
 */

import axios from "axios";
import {LocalStorage, SessionStorage, uid} from "quasar";
import store from "src/store";
import {IS_DEV, IS_TEST, LCCE_SESSION_TOKEN} from "src/services/global/config";
import {MOCK_USER_CF_IRIDE, MOCK_USER_IDENTITY_IRIDE} from "src/services/mocks";

export const httpAuth = axios.create({
  baseURL: process.env.APP_API_BASE_URL_AUTH
});

export const httpPublic = axios.create({
  baseURL: process.env.APP_API_BASE_URL_PUBLIC
});

apiInterceptorRequestId(httpAuth)
apiInterceptorRequestId(httpPublic, false)


function apiInterceptorRequestId(http, isAuth = true) {
  http.interceptors.request.use(config => {

    let tokenLCCE = SessionStorage.getItem(LCCE_SESSION_TOKEN)

    config.headers["X-Request-Id"] = uid();
    config.headers["X-Codice-Servizio"] = config.headers["X-Codice-Servizio"] ?? process.env.APP_CODE;
    config.headers["X-Codice-Verticale"] = config.headers["X-Codice-Verticale"] ?? "APIOPSAN";
    if (tokenLCCE) {
      config.params = config.params || {}
      config.params["tokenLCCE"] = tokenLCCE;
    }
    if (isAuth) {
      let roleParams = store.getters["getRoleParams"]
      config.headers["ruolo"] = roleParams?.ruolo ?? null;
      config.headers["collocazione"] = roleParams?.collocazione ?? null;
      config.headers["regime"] = roleParams?.regime ?? null;
    }
    if (IS_DEV) {
      config.headers["X-Forwarded-for"] = "127.0.0.1"
      config.headers["Shib-Iride-IdentitaDigitale"] = MOCK_USER_IDENTITY_IRIDE
      config.headers["Shib-Identita-CodiceFiscale"] = MOCK_USER_CF_IRIDE;
    }

    return config;
  });
}
