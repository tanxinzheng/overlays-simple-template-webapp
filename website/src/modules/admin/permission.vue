<template>
  <div>
    <mo-table
      :url="url"
      :columns="columns">
    </mo-table>
  </div>
</template>

<script>
  let vm
  export default {
    name: '',
    data () {
      return {
        url: '/permission',
        columns: [
          {name: 'permissionCode', label: '权限代码'},
          {name: 'permissionName', label: '权限名称'},
          {name: 'description', label: '权限描述'},
          {name: 'createdUserName', label: '创建人', type:'avatar', itemText: 'nickname'},
          {name: 'createdTime', label: '创建时间'},
          {name: 'updatedUserName', label: '更新人', type:'avatar', itemText: 'nickname'},
          {name: 'updatedTime', label: '更新时间'},
          {name: 'active', label: '激活', type: 'checkbox'}
        ]
      }
    },
    created: function () {
      vm = this
    }
  }
</script>

<style>

</style>
