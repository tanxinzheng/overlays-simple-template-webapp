<template>
  <mo-table
    :url="'/dictionary'"
    show-attach-button="true"
    :columns="columns">
  </mo-table>
</template>

<script>
  export default {
    name: '',
    data () {
      return {
        columns: [
          {name: 'groupCode', label: '类型代码'},
          {name: 'groupName', label: '类型描述'},
          {name: 'dictionaryName', label: '字典名称'},
          {name: 'dictionaryCode', label: '字典代码'},
          {name: 'sort', label: '排序'},
          {name: 'active', label: '激活', type: 'checkbox'},
          {name: 'isShow', label: '显示', type: 'checkbox'},
          {name: 'createdTime', label: '创建时间'}
        ]
      }
    },
    created: function () {
    },
    methods: {
    }
  }
</script>

<style>

</style>
