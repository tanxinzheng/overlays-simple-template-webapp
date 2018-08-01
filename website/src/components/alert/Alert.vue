<template>
  <div>
    <v-snackbar
      :timeout="option.timeout"
      :color="option.color"
      :top="option.position === 'top'"
      :bottom="option.position === 'bottom'"
      :right="option.position === 'right'"
      :left="option.position === 'left'"
      :multi-line="option.mode === 'multi-line'"
      :vertical="option.mode === 'vertical'"
      v-model="show">
      <v-icon icon dark>info</v-icon>
      &nbsp;&nbsp;{{ text }}
      <v-btn dark icon @click.native="close()">
        <v-icon icon>close</v-icon>
      </v-btn>
    </v-snackbar>
  </div>
</template>

<script>
  import Bus from '../factory/bus'
  import Constant from '../factory/constant'
  let vm
  export default {
    name: 'mo-alert',
    data () {
      return {
        show: false,
        option: {
          color: 'info',
          mode: '',
          position: 'right',
          timeout: 1800
        },
        text: ''
      }
    },
    created () {
      vm = this
      Bus.$on(Constant.eventKeyAlert, function (data) {
        vm.show = true
        vm.text = data.text
        if (data.option) {
          vm._.extend(vm.option, data.option)
        }
      })
    },
    methods: {
      close () {
        vm.show = false
        vm.option.resolve(false)
      }
    }
  }
</script>

<style>
</style>
