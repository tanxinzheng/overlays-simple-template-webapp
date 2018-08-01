<template>
  <div>
    <v-layout>
      <v-flex sm4>
        <v-card class="mo-card-height-md">
          <v-toolbar color="transparent" flat height="48" >
            <!--<v-toolbar-side-icon></v-toolbar-side-icon>-->
            <v-toolbar-title>用户组</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn icon @click="deleteGroup()" v-if="currentGroup.id">
              <v-icon>delete</v-icon>
            </v-btn>
            <v-btn icon @click="addGroup()">
              <v-icon>add</v-icon>
            </v-btn>
          </v-toolbar>
          <v-list two-line subheader>
            <template  v-for="item in groups" >
            <v-divider></v-divider>
            <v-list-tile avatar v-bind:key="item.groupName" @click="choseGroup(item)" height="50" v-bind:class="{'mo-card-selection': item.id == currentGroup.id}" >
              <v-list-tile-avatar>
                <v-icon>group</v-icon>
              </v-list-tile-avatar>
              <v-list-tile-content>
                <v-list-tile-title>
                  {{ item.groupName }} （{{ item.groupCode }}）
                </v-list-tile-title>
                <v-list-tile-sub-title>{{ item.description }}</v-list-tile-sub-title>
              </v-list-tile-content>
              <v-list-tile-action>
                <v-list-tile-action-text>{{ item.groupTypeDesc }}</v-list-tile-action-text>
                <v-list-tile-action-text>
                  <v-chip color="primary" text-color="white" small v-if="item.active" disabled="">已启用</v-chip>
                  <v-chip color="secondary" text-color="white" small v-if="!item.active" disabled="">未启用</v-chip>
                </v-list-tile-action-text>
              </v-list-tile-action>
            </v-list-tile>
            </template>
            <v-divider></v-divider>
          </v-list>
        </v-card>
      </v-flex>
      <v-flex sm8 style="margin-left: 20px; max-height: 600px; min-height: 600px;">
        <v-card class="mo-card-height-md">
          <v-toolbar color="transparent" flat="" height="48">
            <v-toolbar-title>{{currentGroup.groupName}}</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn icon @click="openUser()" v-if="currentGroup.id">
              <v-icon>add</v-icon>
            </v-btn>
          </v-toolbar>
          <v-list two-line subheader>
            <template v-for="(user, index) in users">
              <v-divider></v-divider>
              <v-list-tile avatar v-bind:key="user.id">
              <v-list-tile-avatar>
                <mo-avatar size="40px" :avatarModel="user" avatarTextKey="nickname"></mo-avatar>
              </v-list-tile-avatar>
              <v-list-tile-content>
                <v-list-tile-title>{{ user.nickname }}</v-list-tile-title>
              </v-list-tile-content>
              <v-list-tile-action>
                <v-btn icon ripple @click="unbind(user)" v-if="currentGroup.id">
                  <v-icon color="red">delete</v-icon>
                </v-btn>
              </v-list-tile-action>
            </v-list-tile>
            </template>
            <v-divider></v-divider>
          </v-list>
        </v-card>
      </v-flex>
    </v-layout>
    <v-dialog
      v-model="isOpen"
      transition="dialog-bottom-transition"
      :overlay=false
      scrollable
      width="650"
    >
      <v-card>
        <v-toolbar style="flex: 0 0 auto;" dark class="primary">
          <v-toolbar-title>添加用户至用户组</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-btn icon @click.native="dismiss()" dark>
            <v-icon>close</v-icon>
          </v-btn>
        </v-toolbar>
        <v-card-text>
          <v-select
            label="选择用户"
            v-model="selectUsers"
            :items="unbindUsers"
            item-text="username"
            item-value="id"
            chips
            multiple
            required
          ></v-select>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="warning" flat @click.stop="dismiss()">取消</v-btn>
          <v-btn color="primary" @click="saveChose()">保存</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
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
        selectUsers: [],
        isOpen: false,
        unbindUsers: [],
        users: [],
        groups: [],
        currentGroup: {
          groupName: '所有用户'
        },
        url:"/group",
        formColumns: [
          {name: 'groupType', label: '用户组类型', type: 'select', options: [
              {text:'系统用户组', value: 'SYSTEM'},
            {text:'自定义用户组', value: 'CUSTOM'}
          ]},
          {name: 'groupCode', label: '用户组代码', type: 'text', placeholder:"如：GROUP_ADMIN"},
          {name: 'groupName', label: '用户组名称', type: 'text'},
          {name: 'description', label: '描述', type: 'text'},
          {name: 'active', label: '启用', type: 'checkbox'}
        ]
      }
    },
    created () {
      vm = this
      this.loadData()
    },
    methods: {
      loadData () {
        this.loadGroup()
        this.loadAllUser()
      },
      loadAllUser () {
        this.axios.get('/user', {
          params: {
            pageSize: 10,
            pageNum: 1
          }
        }).then((response) => {
          this.users = response.data.data
        })
      },
      loadGroup () {
        this.axios.get('/group', {
          params: {
            pageSize: 10,
            pageNum: 1
          }
        }).then((response) => {
          this.groups = response.data.data
        })
      },
      addGroup () {
        this.$refs.formData.open().then(function(){
          vm.loadGroup()
        })
      },
      openUser () {
        if (!this.currentGroup || !this.currentGroup.id) {
          return
        }
        this.isOpen = true
        this.unbindUsers = []
        this.axios.get('/group/' + this.currentGroup.id + '/user/unbind').then((response) => {
          this.unbindUsers = response.data
        })
      },
      dismiss () {
        this.selectUsers = []
        this.unbindUsers = []
        this.isOpen = false
      },
      choseGroup (item) {
        if (!item || !item.id) {
          return
        }
        this.currentGroup = item
        this.loadRelation()
      },
      loadRelation () {
        this.axios.get('/group/' + this.currentGroup.id + '/user', {
          params: {
            pageSize: 10,
            pageNum: 1
          }
        }).then((response) => {
          this.users = response.data
        })
      },
      saveChose () {
        if (!this.selectUsers || this.selectUsers.length === 0) {
          return
        }
        this.axios.post('/group/' + this.currentGroup.id + '/user', this.selectUsers).then((response) => {
          this.users = response.data
          this.dismiss()
          this.loadRelation()
        })
      },
      deleteGroup () {
        if (this.currentGroup.id) {
          this.$confirm('确认删除［' + this.currentGroup.groupName + '］用户组？').then((data) => {
            this.axios.delete('/group/' + this.currentGroup.id).then(() => {
              this.$alert('删除成功')
              this.loadGroup()
              this.currentGroup = {
                groupName: '所有用户'
              }
              this.loadAllUser()
            })
          })
        }
      },
      unbind (user) {
        if (user.id) {
          this.$confirm('确认将［' + user.nickname + '］用户从［' + this.currentGroup.groupName + '］用户组中移除？').then((data) => {
            this.axios.delete('/user/group/' + user.id).then(() => {
              this.$alert('删除成功')
              this.loadRelation()
            })
          })
        }
      }
    }
  }
</script>

<style>
.mo-card-selection {
  background-color: #b3d4fc;
}
.mo-card-height-md {
  max-height: 600px;
  min-height: 600px;
}
</style>
