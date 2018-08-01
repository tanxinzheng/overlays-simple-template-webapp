<template>
  <div class="mo-empty-state">
    <v-icon color="teal">info</v-icon>&nbsp;&nbsp;未找到匹配的数据
  </div>
</template>

<script>
  export default {
    name: 'mo-empty-state',
    data () {
      return {}
    },
    methods: {}
  }
</script>

<style>
  .mo-empty-state {
    height: 100px;
    font-size: 16px;
    text-align: center;
    margin-top: 70px;
  }
</style>
