<template>
  <v-toolbar
    color="blue darken-3"
    dark
    app
    clipped-left
    fixed
    height="48"
  >
    <v-toolbar-title :style="$vuetify.breakpoint.smAndUp ? 'width: 220px; min-width: 200px' : 'min-width: 72px'"
                     class="ml-0">
      <v-toolbar-side-icon @click="toggleSide()"></v-toolbar-side-icon>
      <span class="hidden-xs-only">Momen</span>
    </v-toolbar-title>
    <v-spacer></v-spacer>
    <v-toolbar-items class="hidden-sm-and-down">
      <v-btn flat>
        <v-icon>search</v-icon>
      </v-btn>
      <v-btn flat to="/management">
        <v-icon>settings</v-icon>
      </v-btn>
      <v-btn flat :to="{name:'account.profile'}">
        <v-icon>apps</v-icon>
      </v-btn>
      <v-menu
        offset-y
        :close-on-content-click="false"
      >
        <v-btn slot="activator" flat>
          <v-badge color="indigo" right overlap v-if="notificationCountUnread.number > 0">
            <span slot="badge">{{notificationCountUnread.number}}</span>
            <v-icon>notifications</v-icon>
          </v-badge>
          <v-icon v-if="notificationCountUnread.number == 0">notifications</v-icon>
        </v-btn>
        <v-card width="300">
          <v-list  two-line subheader>
            <v-subheader color="primary">我的消息</v-subheader>
            <v-divider></v-divider>
            <template v-for="(item, index) in notifications">
              <v-divider></v-divider>
              <v-list-tile :to="{name:'account.notification', params: {id: item.id}}">
                <v-list-tile-content>
                  <v-list-tile-title v-html="item.title"></v-list-tile-title>
                  <v-list-tile-sub-title v-html="item.sendTime"></v-list-tile-sub-title>
                </v-list-tile-content>
              </v-list-tile>
            </template>
          </v-list>
        </v-card>
      </v-menu>

      <v-menu
        offset-y
        :close-on-content-click="false"
        bottom
        v-model="showAccount"
      >
        <v-btn flat slot="activator">
          <v-avatar size="26px">
            <img :src="account.avatarUrl" :alt="account.username">
          </v-avatar>
        </v-btn>
        <v-card>
          <v-toolbar flat class="transparent" height="43px">
            <v-list class="pa-0">
              <v-list-tile class="list__tile--link" :to="{name:'account.profile'}">
                <v-list-tile-content>
                  <v-list-tile-title>{{account.username}}</v-list-tile-title>
                </v-list-tile-content>
              </v-list-tile>
            </v-list>
          </v-toolbar>
          <v-divider></v-divider>
          <v-list class="pt-0" dense>
            <v-divider></v-divider>
            <v-list-tile class="list__tile--link">
              <v-list-tile-content>
                <v-list-tile-title @click="logout()">退出</v-list-tile-title>
              </v-list-tile-content>
            </v-list-tile>
          </v-list>
        </v-card>
      </v-menu>
    </v-toolbar-items>
  </v-toolbar>
</template>

<script>
  import Bus from '../components/factory/bus.js'
  import Access from '../components/factory/access'
  export default {
    name: 'app-toolbar',
    data () {
      return {
        drawer: true,
        showAccount: false,
        notificationCountUnread: {},
        account: {},
        menu: false,
        notifications: []
      }
    },
    created: function () {
      this.getAccountInfo()
      this.getNotification()
      this.countNotification()
    },
    methods: {
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
      go () {
        this.$router.push({
          name: 'admin.user',
          params: {
              username: this.account.username
          }
        })
      },
      countNotification() {
        this.axios.get('/account/notification/count/unread').then((data) => {
          this.notificationCountUnread = data.data;
        })
      },
      getNotification () {
        this.axios.get('/account/notification', {
            params: {
              pageSize: 10,
              pageNum: 1
            }
        }).then((data) => {
          this.notifications = data.data.data
        })
      },
      toggleSide: function () {
        this.drawer = !this.drawer
        Bus.$emit('toggleNav', this.drawer)
      }
    }
  }
</script>

<style>

</style>
