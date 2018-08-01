<template>
  <v-app class="login-app">
    <router-view></router-view>
    <mo-alert></mo-alert>
  </v-app>
</template>

<script>
  import MoAlert from './components/alert/Alert'
  export default {
    components: {MoAlert},
    data () {
      return {
      }
    },
    methods: {
    },
    created: function () {
    }
  }
</script>

<style>
  html {
    background: url(assets/background.jpg) no-repeat center center fixed;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
  }

  #app {
     background: none;
  }
  .login-center {
    position: absolute;
    top: 35%;
    left: 50%;
    margin: -100px 0 0 -250px;
    width: 500px;
    font-size: 16px;
    text-align: center;
    z-index: 99;
  }
  .login-right {
    text-align: right;
    font-size: 14px;
    margin-top: 10px;
  }
  .login-right a:link {
    margin-left: 10px;
    text-decoration: none;
    color: aquamarine;
  }
  .login-footer div.row:first-child {
    margin-top: 10px;
  }
  .login-footer div span:first-child {
    margin-top: 10px;
    font-size: 12px;
  }
</style>
