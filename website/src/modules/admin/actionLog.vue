<template>
  <div>
    <mo-table url="/action_log" :columns="columns"></mo-table>
  </div>
</template>

<script>
  export default {
    name: '',
    data () {
      return {
        columns: [
          {name: 'userIdDesc', label: '用户名称', type: 'avatar', itemText: 'username'},
          {name: 'actionName', label: '操作名称'},
          {name: 'actionDate', label: '操作时间'},
          {name: 'clientIp', label: '客户端IP'},
          {name: 'targetClass', label: '类名'},
          {name: 'targetMethod', label: '方法名'}
        ]
      }
    },
    methods: {}
  }
</script>

<style>

</style>
