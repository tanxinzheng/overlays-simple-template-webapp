<template>
  <v-dialog class="mo-confirm" v-model="show" max-width="450" persistent>
    <v-card>
      <v-toolbar color="primary" dark height="40">
        <v-icon>error_outline</v-icon>
        <v-toolbar-title>{{title}}</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn icon @click.nation="cancel()">
          <v-icon>close</v-icon>
        </v-btn>
      </v-toolbar>
      <v-card-text style="font-size: 14px;">{{text}}</v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn small="" color="error" @click.native="cancel()">取消</v-btn>
        <v-btn small="" color="primary" @click.native="ok()">确认</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
  import Constant from '../factory/constant'
  import Bus from '../factory/bus'
  let vm
  export default {
    name: 'mo-dialog',
    data () {
      return {
        show: false,
        text: '',
        title: '',
        option: {}
      }
    },
    created () {
      vm = this
      Bus.$on(Constant.eventKeyConfirm, function (data) {
        vm.show = true
        vm.text = data.text
        vm.color = 'info'
        vm.option = data
      })
    },
    methods: {
      ok () {
        vm.show = false
        vm.option.resolve(true)
      },
      cancel () {
        vm.show = false
        vm.option.reject(false)
      }
    }
  }
</script>

<style>
</style>
