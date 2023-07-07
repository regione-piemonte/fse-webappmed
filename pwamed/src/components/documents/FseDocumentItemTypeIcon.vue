<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-icon v-bind="attrs" />
</template>

<script>
import { DOCUMENT_TYPE_CODE_MAP } from "../../services/documents/config";

const ICON_STETHOSCOPE = "img:icone/documenti/stetoscopio.svg";
const ICON_MICROSCOPE = "img:icone/documenti/microscopio.svg";
const ICON_RADIOGRAPHY = "img:icone/documenti/radiografia.svg";
const ICON_VACCINATION_CERTIFICATE = "img:icone/documenti/certificato-vaccinale.svg";

const ICON_MAP = {
  [DOCUMENT_TYPE_CODE_MAP.VERBAL]: ICON_STETHOSCOPE,
  [DOCUMENT_TYPE_CODE_MAP.TRIAGE]: ICON_STETHOSCOPE,
  [DOCUMENT_TYPE_CODE_MAP.REPORT]: ICON_MICROSCOPE,
  [DOCUMENT_TYPE_CODE_MAP.HISTORY]: ICON_STETHOSCOPE,
  [DOCUMENT_TYPE_CODE_MAP.SDO]: ICON_STETHOSCOPE,
  [DOCUMENT_TYPE_CODE_MAP.DISCHARGE]: ICON_STETHOSCOPE,
  [DOCUMENT_TYPE_CODE_MAP.CYCLE_REPORT]: ICON_MICROSCOPE,
  [DOCUMENT_TYPE_CODE_MAP.OPERATIVE_ACT]: ICON_STETHOSCOPE,
  [DOCUMENT_TYPE_CODE_MAP.LIS_REPORT]: ICON_MICROSCOPE,
  [DOCUMENT_TYPE_CODE_MAP.RADIOLOGY_REPORT]: ICON_RADIOGRAPHY,
  [DOCUMENT_TYPE_CODE_MAP.PATHOLOGICAL_ANATOMY_REPORT]: ICON_MICROSCOPE,
  [DOCUMENT_TYPE_CODE_MAP.DISCHARGE_LETTER]: ICON_STETHOSCOPE,
  [DOCUMENT_TYPE_CODE_MAP.FIRST_AID_REPORT]: ICON_STETHOSCOPE,
  [DOCUMENT_TYPE_CODE_MAP.LAB_REPORT]: ICON_MICROSCOPE,
  [DOCUMENT_TYPE_CODE_MAP.RADIOLOGY_REPORT_2]: ICON_RADIOGRAPHY,
  [DOCUMENT_TYPE_CODE_MAP.PATHOLOGICAL_ANATOMY_REPORT_2]: ICON_MICROSCOPE,
  [DOCUMENT_TYPE_CODE_MAP.SPECIALIZED_REPORT]: ICON_MICROSCOPE,
  [DOCUMENT_TYPE_CODE_MAP.VACCINATION_CARD]: ICON_VACCINATION_CERTIFICATE,
  [DOCUMENT_TYPE_CODE_MAP.VACCINATION_CARD_2]: ICON_VACCINATION_CERTIFICATE,
  [DOCUMENT_TYPE_CODE_MAP.VACCINATION_CERTIFICATE]: ICON_VACCINATION_CERTIFICATE,
  [DOCUMENT_TYPE_CODE_MAP.VACCINATION_CERTIFICATE_2]: ICON_VACCINATION_CERTIFICATE,
};

export default {
  name: "FseDocumentItemTypeIcon",
  inheritAttrs: false,
  props: {
    type: { type: [String, Number], required: false, default: null }
  },
  data() {
    return {};
  },
  computed: {
    attrs() {
      const { ...attrs } = this.$attrs;
      if (!("name" in attrs)) attrs.name = this.icon;
      return attrs;
    },
    listeners() {
      const { ...listeners } = this.$listeners;
      return listeners;
    },
    icon() {
      let type = this.type?.toString?.();
      return ICON_MAP[type] || ICON_STETHOSCOPE;
    }
  },
  created() {},
  methods: {}
};
</script>

<style scoped lang="sass"></style>
