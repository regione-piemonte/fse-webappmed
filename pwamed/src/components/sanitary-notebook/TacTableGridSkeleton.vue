<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <div>
    <div class="row q-col-gutter-md" v-if="$q.screen.lt.md">
      <div v-for="i in 6" :key="'list-skeleton--' + i" class="col-12 col-sm-6">
        <q-card class="fit">
            <q-card-section v-for="column in columns" :key="column.name" >
              <div class="text-bold text-caption">
                {{column.label}}
              </div>
              <div class="tac-table-grid-item__field-value">
                <q-skeleton type="text"  style="max-width: 200px"/>
              </div>
            </q-card-section>
          </q-card>
      </div>


    </div>
    <template v-else>
      <q-markup-table flat>
        <thead>
        <tr class="q-px-none">
          <th v-for="column in columns" :key="column.name" class="text-left">
            {{column.label}}
          </th>

        </tr>
        </thead>

        <tbody>
        <tr v-for="n in 5" :key="n">
          <td class="" v-for="column in columns" :key="column.name">
            <q-skeleton animation="blink" type="text" width="60px" />
          </td>

        </tr>
        </tbody>
      </q-markup-table>
    </template>


  </div>



</template>

<script>
export default {
name: "TacTableGridSkeleton",
  props:{
  columns: {type:Array, default: ()=> []}
  }
}
</script>

<style scoped>

</style>
