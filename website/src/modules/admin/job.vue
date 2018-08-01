<template>
  <div>
    <mo-table
      :url="url"
      @view="openView"
      :form-columns="formColumns"
      :columns="columns">
    </mo-table>
    <mo-form-data
      ref="formData"
      :url="url"
      :form-columns="formColumns"
    >
    </mo-form-data>
  </div>
</template>

<script>
  let vm
  export default {
    name: '',
    data () {
      return {
        url: '/schedule/task',
        columns: [
          {name: 'jobGroup', label: 'Job组', link: true},
          {name: 'jobName', label: 'Job名称'},
          {name: 'triggerGroup', label: '触发器组'},
          {name: 'triggerName', label: '触发器名称'},
          {name: 'triggerState', label: '触发器状态'},
          {name: 'description', label: '描述'},
          {name: 'cronExpression', label: 'CRON表达式'},
          {name: 'prevFireTime', label: '上次触发时间'},
          {name: 'nextFireTime', label: '下次触发时间'},
          {name: 'startTime', label: '开始时间'},
          {name: 'endTime', label: '结束时间'}
        ],
        formColumns: [
          {name: 'jobGroup', label: 'Job组', type: 'text', disabled: true},
          {name: 'jobName', label: 'Job名称', type: 'text', disabled: true},
          {name: 'triggerGroup', label: '触发器组' , type: 'text', disabled: true},
          {name: 'triggerName', label: '触发器名称', type: 'text', disabled: true},
          {name: 'description', label: '描述', type: 'text', disabled: true},
          {name: 'cronExpression', label: 'CRON表达式', type: 'text'},
        ]
      }
    },
    created: function () {
      vm = this
    },
    methods: {
      openView (data) {
        vm.$refs.formData.open().then(function () {
          vm.loadData()
        })
      }
    }
  }
</script>

<style>

</style>
