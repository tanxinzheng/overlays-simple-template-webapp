<template>
  <div>
    <mo-table url="/account/action_log" :columns="columns"></mo-table>
  </div>
</template>

<script>
    export default {
        data () {
            return {
              columns: [
                {name: 'account', label: '用户名称', type: 'avatar', itemText: 'username'},
                {name: 'actionName', label: '操作名称'},
                {name: 'actionDate', label: '操作时间'},
                {name: 'clientIp', label: '客户端IP'},
              ]
            }
        },
        created: function () {

        },
        methods: {}
    }
</script>

<style>

</style>
