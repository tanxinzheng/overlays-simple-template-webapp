<template>
  <v-dialog
    v-model="isOpen"
    transition="dialog-bottom-transition"
    :overlay=false
    :width="width"
    scrollable
  >
    <v-card>
      <v-toolbar style="flex: 0 0 auto;" dark class="primary">
        <v-toolbar-title>数据字典</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn icon @click.native="dismiss()" dark>
          <v-icon>close</v-icon>
        </v-btn>
      </v-toolbar>
      <v-card-text>
        <v-form v-model="formValid" ref="form" lazy-validation>
          <div v-for="column in formColumns">
            <v-flex v-if="column.type === 'checkbox'">
              <v-checkbox
                primary
                :rules="column.rules"
                :label="column.label"
                v-model="formData[column.name]"
              ></v-checkbox>
            </v-flex>
            <v-flex v-if="column.type === 'select'">
              <v-select
                :label="column.label"
                :placeholder="column.placeholder"
                v-model="formData[column.name]"
                :rules="column.rules"
                :items="column.options"
                required
              ></v-select>
            </v-flex>
            <v-flex v-if="column.type === 'text'">
              <v-text-field
                :placeholder="column.placeholder"
                :label="column.label"
                :rules="column.rules"
                v-model="formData[column.name]"
              ></v-text-field>
            </v-flex>
          </div>
          <div>
            <v-spacer></v-spacer>
            <v-btn color="primary"
                   @click="submit()"
                   :loading="buttons.saveLoading"
                   :disabled="!formValid">保存</v-btn>
          </div>
        </v-form>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
  import _ from 'underscore'
  let vm

  function initFormColumns() {
    if (vm.formColumns) {
      for (let obj of vm.formColumns) {
        if(!obj.type){
          obj.type = 'text'
        }
      }
    }
  }
  export default {
    name: 'mo-form-data',
    props: {
      idKey: {
        type: String,
        default: () => 'id'
      },
      url: {
        require: true
      },
      width: {
        type: Number,
        default: 640
      },
      formColumns: {
        require: true,
        type: Array,
        default: function () {
          return []
        },
        validator (items) {
          for (let i = 0; i < items.length; i++) {
            if (!_.property('label')(items[i])) {
              throw new Error('The [form-columns] must be have \'label\' property')
            }
            if (!_.property('name')(items[i])) {
              throw new Error('The [form-columns] must be have \'name\' property')
            }
            if (!_.property('type')(items[i])) {
              throw new Error('The [form-columns] must be have \'type\' property')
            }
            if (!items[i]['placeholder']) {
              if(items[i]['type'] === 'text'){
                items[i]['placeholder'] = '请输入' + items[i]['label']
              }
              if(items[i]['type'] === 'select'){
                items[i]['placeholder'] = '请选择' + items[i]['label']
              }
            }
          }
          return true
        }
      }
    },
    created () {
      vm = this
      initFormColumns()
    },
    data () {
      return {
        buttons: {
          saveLoading: false
        },
        isOpen: false,
        formValid: true,
        formData: {}
      }
    },
    methods: {
      open (data) {
        if (data) {
          vm.formData = JSON.parse(JSON.stringify(data))
        } else {
          vm.$refs.form.reset()
        }
        vm.isOpen = true
        return new Promise(function (resolve, reject) {
          vm.dialogResolve = resolve
        })
      },
      close (data) {
        vm.isOpen = false
        vm.dialogResolve(data)
      },
      dismiss () {
        vm.isOpen = false
      },
      submit () {
        if (!vm.$refs.form.validate()) {
          return
        }
        vm.buttons.saveLoading = true
        if (vm.formData[vm.idKey]) {
          vm.axios.put(vm.url + '/' + vm.formData.id, vm.formData).then((data) => {
            vm.close()
            vm.$alert('修改成功')
          }).finally(() => {
            vm.buttons.saveLoading = false
          })
        } else {
          vm.axios.post(vm.url, vm.formData).then((data) => {
            vm.close()
            vm.$alert('保存成功')
          }).finally(() => {
            vm.buttons.saveLoading = false
          })
        }
      }
    }
  }
</script>

<style>

</style>
