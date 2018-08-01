<template>
  <v-breadcrumbs divider="/">
    <v-breadcrumbs-item
      v-for="(item, index) in items"
      :key="index">
      {{ item.text }}
    </v-breadcrumbs-item>
  </v-breadcrumbs>
</template>

<script>
  import {mapState} from 'vuex'
  export default {
    name: 'mo-breadcrumb',
    computed: mapState({
      items: state => state.breadcrumbs
    })
  }
</script>

<style>
</style>
