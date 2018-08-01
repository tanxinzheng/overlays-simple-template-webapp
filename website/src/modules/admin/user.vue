<template>
  <div>
    <mo-table
      :url="url"
      show-create-button="true"
      :search-params="searchParam"
      :form-columns="formColumns"
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
        url: '/user',
        searchParam: [
            {name:'keyword',label: '用户名/姓名'}
        ],
        columns: [
          {name: 'username', label: '用户名', link: true},
          {name: 'nickname', label: '姓名'},
          {name: 'email', label: '邮箱'},
          {name: 'phoneNumber', label: '手机号码'},
          {name: 'createdTime', label: '注册时间'},
          {name: 'lastLoginTime', label: '最后登录时间'},
          {name: 'locked', label: '锁定', type: 'checkbox'}
        ],
        formColumns: [
          {
            name: 'username',
            label: '用户名',
            type: 'text',
            rules: [
              (v) => !!v || '请输入用户名',
              (v) => (v && v.length >= 4 && v.length <= 10) || '请输入[6-10]位数的字符'
            ]
          },
          {name: 'nickname', label: '姓名', type: 'text'},
          {name: 'email', label: '邮箱', type: 'text'},
          {name: 'phoneNumber', label: '手机号码', type: 'text'},
          {name: 'locked', label: '锁定', type: 'checkbox'}
        ]
      }
    },
    created: function () {
      vm = this
    },
    methods: {
      openAdd () {
        vm.$refs.formData.open()
      },
      openView (data) {
        vm.$refs.formData.open(data)
      }
    }
  }
</script>

<style>

</style>
