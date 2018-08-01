<template>
  <div>
    <v-container fluid grid-list-md>
      <v-navigation-drawer
        width="200"
        fixed
        clipped
        app
        v-model="drawer"
        :mini-variant.sync="mini"
        mini-variant-width="56"
      >
        <v-toolbar flat class="transparent">
          <v-list class="pa-0">
            <v-list-tile avatar>
              <v-list-tile-action>
                <v-btn icon>
                  <v-icon>settings</v-icon>
                </v-btn>
              </v-list-tile-action>
              <v-list-tile-content>
                <v-list-tile-title>
                  <h3>管理中心</h3>
                </v-list-tile-title>
              </v-list-tile-content>
              <v-list-tile-action>
                <v-btn icon @click.native.stop="mini = !mini">
                  <v-icon>chevron_left</v-icon>
                </v-btn>
              </v-list-tile-action>
            </v-list-tile>
          </v-list>
        </v-toolbar>
        <v-list dense>
          <v-divider></v-divider>
          <template v-for="(item, i) in items">
            <v-layout
              row
              v-if="item.heading"
              align-center
              :key="i">
              <v-flex xs6>
                <v-subheader v-if="item.heading">
                  {{ item.heading }}
                </v-subheader>
              </v-flex>
              <v-flex xs6 class="text-xs-center">
                <a href="#!" class="body-2 black--text">EDIT</a>
              </v-flex>
            </v-layout>
            <v-list-group v-else-if="item.children" v-model="item.model" no-action>
              <v-list-tile slot="item" @click="go(item)">
                <v-list-tile-action>
                  <v-icon>{{ item.model ? item.icon : item['icon-alt'] }}</v-icon>
                </v-list-tile-action>
                <v-list-tile-content>
                  <v-list-tile-title>
                    {{ child.text }}
                    <!--<router-link :to="{path:child.path}">{{ child.text }}</router-link>-->
                  </v-list-tile-title>
                </v-list-tile-content>
              </v-list-tile>
              <v-list-tile
                v-for="(child, i) in item.children"
                :key="i"
                @click="go(child)">
                <v-list-tile-action v-if="child.icon">
                  <v-icon>{{ child.icon }}</v-icon>
                </v-list-tile-action>
                <v-list-tile-content>
                  <v-list-tile-title>
                    <!--<router-link :to="{path:child.path}">{{ child.text }}</router-link>-->
                    {{ child.text }}
                  </v-list-tile-title>
                </v-list-tile-content>
              </v-list-tile>
            </v-list-group>
            <v-list-tile v-else :to="item.path">
              <v-list-tile-action>
                <v-icon>{{ item.icon }}</v-icon>
              </v-list-tile-action>
              <v-list-tile-content>
                <v-list-tile-title :to="item.path">
                  <!--<router-link :to="{path:item.path}">{{ item.text }}</router-link>-->
                  {{ item.text }}
                </v-list-tile-title>
              </v-list-tile-content>
            </v-list-tile>
          </template>
        </v-list>
      </v-navigation-drawer>
      <mo-breadcrumb></mo-breadcrumb>
      <router-view></router-view>
    </v-container>
  </div>
</template>

<script>
  import Bus from '../../components/factory/bus.js'
  import Access from '../../components/factory/access'
  import nav from '../admin'

  export default {
    name: '',

    data () {
      return {
        mini: false,
        drawer: true,
        showAccount: false,
        account: {},
        menu: false,
        items: nav.children
      }
    },
    created: function () {
      let vm = this
      Bus.$on('toggleNav', function (data) {
        vm.drawer = data
      })
      this.getAccountInfo()
      let socket = new WebSocket("ws://localhost:8000/websocket");
      //打开事件
      socket.onopen = function() {
        console.log("Socket 已打开");
        //socket.send("这是来自客户端的消息" + location.href + new Date());
      };
      //获得消息事件
      socket.onmessage = function(msg) {
        console.log(msg.data);
        //发现消息进入    调后台获取
//        getCallingList();
      };
      //关闭事件
      socket.onclose = function() {
        console.log("Socket已关闭");
      };
      //发生了错误事件
      socket.onerror = function(error) {
        console.log("Socket发生了错误", error);
      }
//      this.onConnected()
    },
    methods: {
      onConnected(frame) {
        console.log('连接成功: ' + frame);
        this.$stompClient.subscribe('/gs-guide-websocket', this.responseCallback, this.onFailed);
      },
      responseCallback(frame) {
        console.log("接收信息:" + frame.body);
      },
      onFailed(frame) {
        console.log('连接失败: ' + JSON.stringify(frame));
      },
      logout: function () {
        this.axios.post('/logout').then(() => {
          Access.logout()
          Access.redirectLoginPage()
        })
      },
      getAccountInfo: function () {
        this.axios.get('/account').then((data) => {
          this.account = data.data
        })
      },
      openNotification: function () {

      },
      toggleSide: function () {
        this.drawer = !this.drawer
        Bus.$emit('toggleNav', this.drawer)
      }
    },
    stompClient:{
      monitorIntervalTime: 100,
      stompReconnect: true,
      timeout(orgCmd) {
      }
    }
  }
</script>

<style>
  .navigation-drawer--mini-variant .list__tile {
    padding: 0 4px;
  }
  .list__tile__action {
    min-width: 40px;
  }
</style>
