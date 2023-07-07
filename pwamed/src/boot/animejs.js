/*
 * Copyright Regione Piemonte - 2023
 * SPDX-License-Identifier: EUPL-1.2
 */


import anime from "animejs/lib/anime.es.js";

export default ({ app, router, store, Vue }) => {
  Vue.prototype.$anime = anime;
};
