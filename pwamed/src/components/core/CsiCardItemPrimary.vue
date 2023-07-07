<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <div
    class="csi-card-item-primary no-margin bg-grey-3 q-py-md q-px-md"
    :class="classes"
  >
    <slot>
      <div v-if="checkbox" class="col-auto q-pr-md self-start" :class="{ 'text-center': $q.screen.gt.xs }">
       <q-checkbox dense v-model="selected" @input="onSelect" size="xs"></q-checkbox>
      </div>
      <div class="col-auto q-pr-md" :class="{ 'text-center': $q.screen.gt.xs }">
        <slot name="icon" />
      </div>
      <template v-if="inline">
        <div class="col text-left" >
          <slot name="text" />
        </div>
      </template>
      <template v-else>
        <div class="col-auto" :class="{ 'text-center': $q.screen.gt.xs }">
          <slot name="text" />
        </div>
      </template>
      <div class="col-auto" :class="{ 'text-center': $q.screen.gt.xs }">
        <slot name="iconright" />
      </div>
    </slot>
  </div>
</template>

<script>
export default {
  name: "CsiCardItemPrimary",
  props:{
    inline:{type:Boolean, default:false},
    checkbox:{type:Boolean, default:false},
    isSelected: {type:Boolean, default:false},
  },
  data() {
    return {
      selected:false
    };
  },
  watch:{
    isSelected:{
      handler(val){
        this.selected= val
      }
    }
  },
  computed:{
    classes(){
      let classes=null

      if(this.inline)
        classes = 'row items-center full-height'
      else
        classes = 'q-col-gutter-y-md full-height'

      return classes
    }
  },
  methods:{
    onSelect(val) {
      this.$emit('on-selected', val)
    }
  }
};
</script>

<style lang="sass"></style>
