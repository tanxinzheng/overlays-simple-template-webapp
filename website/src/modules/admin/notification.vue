<template>
  <div>
    <v-layout row wrap>
      <v-flex xs12>
        <mo-table
          ref="moTable"
          :table-buttons="buttons"
          :url="url"
          :columns="columns">
        </mo-table>
      </v-flex>
    </v-layout>
    <v-dialog
      v-model="dialog"
      width="800"
      transition="dialog-bottom-transition"
      scrollable
    >
      <v-card tile>
        <v-toolbar height="55" card class="transparent">
          <v-toolbar-title>发送消息</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-btn icon @click.native="close()">
            <v-icon>close</v-icon>
          </v-btn>
        </v-toolbar>
        <v-divider></v-divider>
        <v-card-text>
          <v-select
            :items="users"
            v-model="item.receiver"
            item-text="groupName"
            item-value="groupCode"
            label="用户"
            placeholder="选择用户"
            multiple
            autocomplete
          ></v-select>
          <v-select
            :items="groups"
            v-model="item.receiveGroup"
            item-text="groupName"
            item-value="id"
            label="用户组"
            placeholder="选择用户组"
            multiple
            autocomplete
          ></v-select>
          <v-text-field
            v-model="item.title"
            label="标题"
            placeholder="邮件标题"
            required
          ></v-text-field>
          <v-text-field
            v-model="item.body"
            label="内容"
            placeholder="邮件内容"
            multi-line
            required
          ></v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-btn dark color="primary" @click="send()">发送</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
  let vm;
  export default {
    data () {
      return {
        dialog: false,
        item: {},
        users: [],
        groups:[],
        url: '/notification_template',
        columns: [
          {name: 'templateCode', label: '模板代码'},
          {name: 'templateName', label: '模板名称'},
          {name: 'templateTitle', label: '模板标题'},
          {name: 'active', label: '是否启用', type: 'checkbox'},
        ],
        buttons: [
          {
            label: '编辑模板',
            click (item) {

            }
          },
          {
            label: '发送消息',
            click (item) {
              vm.item = {
                templateCode: item.tempateCode,
                title: item.templateTitle,
                body: item.templateBody
              };
              vm.dialog = true
            }
          }
        ]
      }
    },
    created: function () {
      vm = this;
      this.getGroup()
      this.getUsers()
    },
    methods: {
      getUsers () {
        this.axios.get('/user', {
          params: {
            pageSize: 10,
            pageNum: 1
          }
        }).then((response) => {
          this.users = response.data.data
        })
      },
      getGroup () {
        this.axios.get('/group', {
          params: {
            pageSize: 10,
            pageNum: 1
          }
        }).then((response) => {
          this.groups = response.data.data
        })
      },
      send () {
        this.axios.post('/notification', this.item).then((response) => {
          this.dialog.alert('发送成功');
        })
      }
    }
  }
</script>

<style>

</style>
