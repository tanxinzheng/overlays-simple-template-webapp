<template>
  <div>
    <v-layout row wrap>
      <v-flex xs2>
        <v-card height="100%">
          <v-toolbar flat height="48" class="transparent">
            <!--<v-toolbar-side-icon></v-toolbar-side-icon>-->
            <v-toolbar-title>文件库</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn icon @click="addGroup()">
              <v-icon>add</v-icon>
            </v-btn>
          </v-toolbar>
          <v-divider></v-divider>
          <v-list class="transparent">
            <v-list-tile
              v-for="link in links"
              :key="link"
              v-bind:class="{'primary--text':currentLink==link}"
              @click="switchType(link)">
              <v-list-tile-title v-text="link"></v-list-tile-title>
            </v-list-tile>
          </v-list>
        </v-card>
      </v-flex>
      <v-flex xs10>
        <mo-table
          ref="moTable"
          :title="currentLink"
          :action-buttons="buttons"
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
          <v-toolbar-title>上传文件</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-btn icon @click.native="close()">
            <v-icon>close</v-icon>
          </v-btn>
        </v-toolbar>
        <v-divider></v-divider>
        <v-card-text>
          <uploader :options="uploadOption"
                    @file-complete="fileComplete"
                    class="uploader-example">
            <uploader-unsupport></uploader-unsupport>
            <uploader-drop>
              <uploader-label><v-icon x-large>fa-upload</v-icon><br>将文件拖到此处，或<a>点击上传</a></uploader-label>
            </uploader-drop>
            <span class="grow">最大上传文件大小50M</span>
            <uploader-list></uploader-list>
          </uploader>
        </v-card-text>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
  let vm
  export default {
    data () {
      return {
        currentLink: '全部文件',
        uploadOption: {
          target: '/api/file/upload',
          testChunks: false,
          headers: {
            Authorization : localStorage.getItem('token')
          }
        },
        dialog: false,
        links: ['全部文件', '我的文件', '文件分组'],
        url: '/attachment',
        columns: [
          {name: 'originName', label: '文件名'},
          {name: 'attachmentSize', label: '文件大小'},
          {name: 'attachmentGroup', label: '分类'},
          {name: 'uploadTime', label: '上传时间'},
          {name: 'uploadUserName', label: '上传人', type:'avatar', itemText: 'nickname'}
        ],
        buttons: [{
          color: 'primary',
          label: '上传文件',
          click  () {
            vm.dialog = true
          }
        }]
      }
    },
    created: function () {
      vm = this
    },
    methods: {
      fileComplete () {
        this.$refs.moTable.loadData()
      },
      switchType (link) {
        this.currentLink = link
        this.$refs.moTable.loadData({
          type: link
        });
      },
      close () {
        this.dialog = false
        this.$refs.moTable.loadData()
      },
      addGroup () {

      },
      deleteGroup () {

      }
    }
  }
</script>

<style>
  .uploader-example .uploader-drop {
    height: 150px;
    text-align: center;
    font-size: 16px;
    padding-top: 35px;
  }
</style>
