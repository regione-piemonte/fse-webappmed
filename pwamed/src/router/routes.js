/*
 * Copyright Regione Piemonte - 2023
 * SPDX-License-Identifier: EUPL-1.2
 */

export const ERROR_404 = {
  path: "*",
  name: "error404",
  component: () => import("pages/PageError404.vue")
};


// PAGINE VERTICALE
// ---------------------------------------------------------------------------------------------------------------------
export const PROFILE = {
  path: "profilo-utente",
  name: "userProfile",
  component: () => import("pages/PageProfile.vue")
};

export const LCCE_HOME = {
  path: "lcce",
  name: "lcceHome",
  component: () => import("pages/PageLcceHome.vue"),

};

export const ECWDME_HOME = {
  path: "ecwdme",
  name: "ecwdmeHome",
  component: () => import("pages/PageEcwdmeHome.vue"),

};


export const HOME = {
  path: "regimi",
  name: "home",
  component: () => import("pages/PageHome.vue"),

};

export const QR_CODE_HOME = {
  path: "scelta-ruolo-collocazione",
  name: "qrCodeHome",
  component: () => import("pages/PageQrCodeHome.vue"),
};

// --- PAGINE FIGLIE DI PAZIENTE ----//
//--------------------------------------------------------//
export const DOCUMENTS = {
  path: "documenti",
  name: "documents",
  component: () => import("pages/documents/PageDocumentsList.vue"),
};

export const DOCTOR_REPORTS = {
  path: "i-tuoi-referti",
  name: "doctorReports",
  component: () => import("pages/documents/PageDoctorReports.vue"),
};


export const EPISODES = {
  path: "episodi",
  name: "episodes",
  component: () => import("pages/episodes/PageEpisodes.vue"),
};

export const PRESCRIPTIONS = {
  path: "ricette",
  name: "prescriptions",
  component: () => import("pages/prescriptions/PagePrescriptions.vue"),
};

export const VACCINATIONS = {
  path: "vaccinazioni",
  name: "vaccinations",
  component: () => import("pages/vaccinations/PageVaccinations.vue"),
};


export const PROFILE_SYNTHETIC = {
  path: "profilo-sanitario-sintetico",
  name: "profileSynthetic",
  component: () => import("pages/PagePatientProfileSynthetic.vue"),
};

export const EXEMPTIONS = {
  path: "esenzioni",
  name: "exemptions",
  component: () => import("pages/exemptions/PageExemptions.vue"),
};

export const SCREENING = {
  path: "screening",
  name: "screening",
  component: () => import("pages/screening/PageScreening.vue"),
};

export const SANITARY_NOTEBOOK_MENU = {
  path: "taccuino",
  name: "sanitaryNotebookMenu",
  component: () => import("pages/sanitary-notebook/PageTacSanitaryNotebookMenu.vue"),
};

export const CONSENTS_ACTIVATION = {
  path: "gestione-consensi",
  name: "consents",
  component: () => import("pages/PageConsentsActivation.vue"),
};

export const PATIENT_INFO = {
  path: "/",
  name: "patientInfo",
  component: () => import("pages/PagePatientInfo.vue"),
  children:[
    DOCUMENTS,
    DOCTOR_REPORTS,
    EPISODES,
    PRESCRIPTIONS,
    VACCINATIONS,
    PROFILE_SYNTHETIC,
    EXEMPTIONS,
    SCREENING,
    SANITARY_NOTEBOOK_MENU
  ]

};





// --- DOCUMENTI ----//
//--------------------------------------------------------//
export const REPORT_DETAIL = {
  path: "referto",
  name: "reportDetail",
  component: () => import("pages/documents/PageReportDetail.vue"),
};

export const REPORT_COMPARE = {
  path: "confronta-referti",
  name: "reportCommpare",
  component: () => import("pages/documents/PageReportCompare.vue"),
};

export const REPORT_IMAGES = {
  path: "immagini",
  name: "reportImages",
  component: () => import("pages/documents/PageReportImages.vue"),
};

export const IMAGE_DETAIL = {
  path: "immagini/:accessionNr",
  name: "imageDetail",
  component: () => import("pages/documents/PageReportImageDetail.vue"),
};

export const TRANSCRIPTION = {
  path: "trascrizione",
  name: "transcription",
  component: () => import("pages/documents/PageTranscription.vue"),
};

export const DOCUMENT_DETAIL = {
  path: "documenti/:id",
  name: "report",
  component: () => import("pages/documents/PageDocument.vue"),
  children:[
    REPORT_DETAIL,
    REPORT_IMAGES,
    IMAGE_DETAIL,
    TRANSCRIPTION
  ]
};
//--------------------------------------------------------//



// --- TACCUINO ----//
//--------------------------------------------------------//

export const TAC_DETECTIONS = {
  path: "rilevazioni",
  name: "tacDetections",
  component: () => import("pages/sanitary-notebook/PageTacDetections.vue"),
};

export const TAC_DRUGS = {
  path: "farmaci",
  name: "farmaci",
  component: () => import("pages/sanitary-notebook/PageTacDrugs.vue"),
};
export const TAC_DIET = {
  path: "dieta",
  name: "dieta",
  component: () => import("pages/sanitary-notebook/PageTacDiet.vue"),
};

export const TAC_EVENTS = {
  path: "eventi",
  name: "eventi",
  component: () => import("pages/sanitary-notebook/PageTacEvents.vue"),
};

export const TAC_PAIN = {
  path: "dolori",
  name: "dolori",
  component: () => import("pages/sanitary-notebook/PageTacPain.vue"),
};

export const TAC_STRUCTURE_CONTACT = {
  path: "contatti-struttura",
  name: "tacStructureContact",
  component: () => import("pages/sanitary-notebook/PageTacStructureContact.vue"),
};

export const TAC_GENERAL_NOTES = {
  path: "note-generali",
  name: "tacGeneralNotes",
  component: () => import("pages/sanitary-notebook/PageTacGeneralNotes.vue"),
};

export const TAC_SYMPTOMS = {
  path: "sintomi",
  name: "tacSymptoms",
  component: () => import("pages/sanitary-notebook/PageTacSymptoms.vue"),
};

export const SANITARY_NOTEBOOK = {
  path: "taccuino/:id",
  name: "sanitaryNotebook",
  component: () => import("pages/sanitary-notebook/PageTacSanitaryNotebook.vue"),
  children:[
    TAC_DETECTIONS,
    TAC_DRUGS,
    TAC_DIET,
    TAC_EVENTS,
    TAC_PAIN,
    TAC_STRUCTURE_CONTACT,
    TAC_GENERAL_NOTES,
    TAC_SYMPTOMS

  ]
};

//--------------------------------------------------------//

// --- SCREENING ----//
//--------------------------------------------------------//

export const SCREENING_DETAIL = {
  path: "screening/:id/dettaglio",
  name: "screeningDetail",
  component: () => import("pages/screening/PageScreeningDetail.vue"),
};


export const PATIENT = {
  path: "paziente/",
  name: "patient",
  component: () => import("pages/PagePatient.vue"),
  children:[
    PATIENT_INFO,
    DOCUMENT_DETAIL,
    REPORT_COMPARE,
    CONSENTS_ACTIVATION,
    SANITARY_NOTEBOOK,
    SCREENING_DETAIL
  ]

};


export const APP = {
  path: "",
  name: "app",
  component: () => import("pages/AppFse.vue"),
  children: [
    QR_CODE_HOME,
    HOME,
    PATIENT
  ]
};

export const LAYOUT_APP = {
  path: "/",
  component: () => import("layouts/LayoutApp.vue"),
  children: [
    APP,
    LCCE_HOME,
    ECWDME_HOME
  ]
};


const routes = [LAYOUT_APP];

// Always leave this as last one
if (process.env.MODE !== "ssr") {
  routes.push(ERROR_404);
}



export default routes;



