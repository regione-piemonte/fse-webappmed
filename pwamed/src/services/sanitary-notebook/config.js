/*
 * Copyright Regione Piemonte - 2023
 * SPDX-License-Identifier: EUPL-1.2
 */

export const ENTITY_CODE_MAP = {
  DETECTION: "RILEVAZIONI",
  SYMPTOMS: "SINTOMI",
  PAINS: "DOLORI",
  EVENTS: "EVENTI",
  DRUGS: "FARMACI",
  DIET: "DIETA",
  STRUCTURE_CONTACT: "CONTATTI_STRUTTURA"
};

export const GROUP_CODE_MAP = {
  PRESSURE: "PRES_GR",
  WEIGHT: "PESO_GR",
  TEMPERATURE: "TEMP_GR",
  INSULIN: "INS_GR",
  GLYCEMIA: "GLIC_GR",
  OXYMETRY: "OSS_GR",
  CHOLESTEROL: "COL_GR",
  HEMOGLOBIN: "EMO_GR",
  BREATH_FREQ: "FREQ_RESP_GR"
};

export const DESCRIPTOR_CODE_MAP = {
  PRESSURE: "PRES",
  DIASTOLIC_PRESSURE: "PRES_DIA",
  SYSTOLIC_PRESSURE: "PRES_SIST",
  HEART_RATE: "FREQ_CARD",
  OXYMETRY: "OSS",
  GLYCEMIA: "GLIC",
  INSULIN: "INS",
  INSULIN_FARM: "INS_FARM",
  TEMPERATURE: "TEMP",
  WEIGHT: "PESO",
  BREATH_FREQ: "FREQ_RESP",
  WAIST_CIRC: "CIRC_VITA",
  HEIGHT: "ALT",
  CHOLESTEROL_HDL: "COL_HDL",
  CHOLESTEROL_LDL: "COL_LDL",
  CHOLESTEROL_TOT: "COL_TOT",
  TRIGLYCERIDES: "TRIGL",
  HEMO_GLYCEMIA: "EMO_GLIC"
};

export const DESCRIPTOR_CODE_MAP_LABEL = {
  [DESCRIPTOR_CODE_MAP.PRESSURE]: "Pressione",
  [DESCRIPTOR_CODE_MAP.DIASTOLIC_PRESSURE]: "Pressione diastolica (min.)",
  [DESCRIPTOR_CODE_MAP.SYSTOLIC_PRESSURE]: "Pressione sistolica (max.)",
  [DESCRIPTOR_CODE_MAP.HEART_RATE]: "Frequenza cardiaca a riposo",
  [DESCRIPTOR_CODE_MAP.OXYMETRY]: "Ossimetria",
  [DESCRIPTOR_CODE_MAP.GLYCEMIA]: "Glicemia a digiuno",
  [DESCRIPTOR_CODE_MAP.INSULIN]: "Insulina",
  [DESCRIPTOR_CODE_MAP.INSULIN_FARM]: "Insulina tipo farmaco",
  [DESCRIPTOR_CODE_MAP.TEMPERATURE]: "Temperatura",
  [DESCRIPTOR_CODE_MAP.WEIGHT]: "Peso",
  [DESCRIPTOR_CODE_MAP.BREATH_FREQ]: "Frequenza respiratoria",
  [DESCRIPTOR_CODE_MAP.WAIST_CIRC]: "Circonferenza vita",
  [DESCRIPTOR_CODE_MAP.HEIGHT]: "Altezza",
  [DESCRIPTOR_CODE_MAP.CHOLESTEROL_HDL]: "HDL",
  [DESCRIPTOR_CODE_MAP.CHOLESTEROL_LDL]: "LDL",
  [DESCRIPTOR_CODE_MAP.CHOLESTEROL_TOT]: "TOT",
  [DESCRIPTOR_CODE_MAP.TRIGLYCERIDES]: "Trigliceridi",
  [DESCRIPTOR_CODE_MAP.HEMO_GLYCEMIA]: "Emoglobina"
};


export const DETECTIONS_MAP = {
  WEIGHT: 'WEIGHT',
  CHOLESTEROL: 'CHOLESTEROL',
  HEMOGLOBIN: 'HEMOGLOBIN',
  PRESSURE: 'PRESSURE',
  BREATH_FREQUENCY: 'BREATH_FREQUENCY',
  GLYCEMIA: 'GLYCEMIA',
  INSULIN: 'INSULIN',
  OXIMETRY: 'OXIMETRY',
  TEMPERATURE: 'TEMPERATURE'
}

export const VISUALIZATION_MAP = {
  GRAPH: 1,
  TABLE: 2
};

export const STRUCTURE_TYPE_CODE_MAP = {
  ASR: "STRUT_SAN",
  NO_CONVENTIONAL: "MNC"
};

export const TAC_TABLE_LIMIT = 20


export const GRAPH_COLORS_MAP = ["#f2cb05", "#d68800", "#00a120"]
