<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-card :class="{'bg-transparent': $q.screen.lt.md}" class="relative-position " flat>
    <q-card-section  :class="[$q.screen.gt.sm ?'q-pa-sm': 'no-padding']">
      <template v-if="loading">
        <tac-table-grid-skeleton :columns="columns"/>
      </template>
      <template v-else>
        <q-table
          :columns="columns"
          :data="data"
          :grid="$q.screen.lt.md"
          dense
          flat
          hide-bottom
          hide-pagination
          :rows-per-page-options="[0]"
        >

          <!-- GRID ITEM -->
          <!-- --------- -->
          <!--              <template #item="props">-->
          <!--                <div class="col-12 col-sm-6">-->
          <!--                  <fse-tac-table-grid-item-symptom-->
          <!--                    :row="props.row"-->
          <!--                  />-->
          <!--                </div>-->
          <!--              </template>-->

          <template #item="props">
            <div class="q-pa-sm col-xs-12 col-sm-6">
              <q-card class="q-py-sm">

                <q-card-section v-for="col in props.cols" :key="col.name" class="q-py-sm">
                  <div class="fse-tac-grid-item__field-label">
                    {{ col.label }}
                  </div>
                  <div class="fse-tac-grid-item__field-value">
                    {{ col.value }}
                  </div>
                </q-card-section>
              </q-card>
            </div>
          </template>

        </q-table>
      </template>
    </q-card-section>

    <template v-if="hasMoreItems">
      <div class="q-py-md text-center">
        <q-btn
          :loading="loadingMore"
          color="primary"
          dense
          flat
          @click="$emit('get-more-items')"
        >
          Carica altri
        </q-btn>
      </div>
    </template>
  </q-card>


</template>

<script>
import TacTableGridSkeleton from "components/sanitary-notebook/TacTableGridSkeleton";

export default {
  name: "FseTacTableGrid",
  components: {TacTableGridSkeleton},
  props: {
    loading: {type: Boolean, default: false},
    columns: {type: Array, default: () => []},
    data: {type: Array, default: () => []},
    hasMoreItems: {type: Boolean, default: false},
    loadingMore: {type: Boolean, default: false},
  }
}
</script>

<style scoped>

</style>
