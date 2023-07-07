<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-card class="csi-menu-item ">
      <q-item
        class="q-py-md"
      >
        <q-item-section side>
          <q-skeleton type="QAvatar" size="28px" class="bg-primary-light"/>
        </q-item-section>
        <q-item-section>
          <q-skeleton type="text" class="bg-grey-5" style="max-width: 200px"/>
        </q-item-section>
      </q-item>
    </q-card>
</template>

<script>
export default {
  name: "FseTacMenuItemSkeleton",

computed:{


},
  methods: {

  }
}
</script>

<style scoped>

</style>
