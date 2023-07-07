<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-banner class="csi-banner" v-bind="attrs" v-on="listeners" :class="classes">
    <div class="row items-center q-pa-xs q-col-gutter-md" >
      <div class="col-auto">
        <q-icon v-if="icon" :name="icon.name" size="sm"  :color="icon.color"/>
      </div>
      <div class="text-body1 col">
        <slot />
        <div class="q-mt-sm" v-if="actions">
          <slot name="action" />
        </div>

      </div>
      <div class="text-body1 col-md-auto  col-12" v-if="actionsInline">
        <slot name="action" />
      </div>


    </div>


  </q-banner>
</template>

<script>

const VALID_TYPES ={
  INFO: 'info',
  WARNING: 'warning',
  POSITIVE: 'positive',
  NEGATIVE: 'negative',
  FSE: 'fse'
}
export default {
  name: "CsiBanner",
  props:{
    type:{type:String, default: 'info'},
    noIcon:{type:Boolean, default:false},
    actions:{type:Boolean, default:false},
    actionsInline:{type:Boolean, default:false}
    // icon:{type:String, default: null},
    // iconColor:{type:String, default: null},
    // iconSize:{type:String, default: 'md'},
  },
  data(){
    return {
    }
  },
  computed:{
    attrs() {
      const { ...attrs } = this.$attrs;
      return attrs;
    },
    listeners() {
      const { ...listeners } = this.$listeners;
      return listeners;
    },
    classes(){
      let result = [];
      if(this.type) result.push(`csi-banner--${this.type}`);
      if(this.icon) result.push(`csi-banner--with-icon`);
      return result
    },
    icon(){
      let icon = {}

      switch (this.type){
        case VALID_TYPES.FSE:
          icon= {
            name: 'error_outline',
            color: 'yellow-7'
          }
          break;
        case VALID_TYPES.WARNING:
          icon= {
            name: 'error_outline',
            color: 'orange-6'
          }
          break;
        case VALID_TYPES.NEGATIVE:
          icon= {
            name: 'error_outline',
            color: 'red-7'
          }
          break;

        case VALID_TYPES.INFO:
          icon= {
            name: 'info_outline',
            color: 'blue-5'
          }
          break;

        case VALID_TYPES.POSITIVE:
          icon= {
            name: 'info_outline',
            color: 'green-7'
          }
          break;
        default:
          icon= {
            name: 'info_outline',
            color: 'blue-5'
          }
      }


      return icon
    }
  }
}
</script>

<style lang="sass">
.csi-banner
  border-left-width: 8px
  border-left-style: solid
  border-left-color: transparent

.csi-banner
  &.csi-banner--info
    background-color: $blue-2
    border-left-color: $blue-4

  &.csi-banner--warning
    background-color: $orange-2
    border-left-color: $orange-5

  &.csi-banner--positive
    background-color: $green-3
    border-left-color: $green-6

  &.csi-banner--negative
    background-color: $red-3
    border-left-color: $red-6

  &.csi-banner--fse
    background-color: $yellow-2
    border-left-color: $yellow-5

  &.csi-banner--with-icon
    padding-left: 8px !important

</style>
