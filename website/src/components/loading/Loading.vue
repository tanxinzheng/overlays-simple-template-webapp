<template>
  <div>
    <div class="body-loading" v-if="show">
      <v-progress-circular size="64" indeterminate color="primary"></v-progress-circular>
    </div>
  </div>
</template>

<script>
  import Constant from '../factory/constant'
  import Bus from '../factory/bus'
  let vm
  export default {
    name: 'mo-loading',
    created () {
      vm = this
      Bus.$on(Constant.httpLoading, function (data) {
        vm.show = data
      })
    },
    data () {
      return {
        show: true
      }
    }
  }
</script>

<style>
  .body-loading {
    z-index: 10;
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(255, 255, 255, .1);
  }
  .body-loading > div {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  }
</style>
