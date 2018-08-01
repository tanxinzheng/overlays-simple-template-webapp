// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Vuetify from 'vuetify'
import VueMo from './components/index'
import Router from 'vue-router'

import AccessPage from './access'
import Login from './modules/access/Login.vue'
import Register from './modules/access/Register.vue'
import FindPassword from './modules/access/FindPassword.vue'

import './assets/font.css'

import './stylus/main.styl'
// import 'vuetify/dist/vuetify.css'
import 'font-awesome/css/font-awesome.css'

Vue.config.productionTip = false
Vue.use(Vuetify)
Vue.use(VueMo)
Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/login',
      name: 'Login',
      text: '登录',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      text: '注册',
      component: Register
    },
    {
      path: '/find_password',
      name: 'FindPassword',
      text: '找回密码',
      component: FindPassword
    },
    {
      path: '/',
      redirect: '/login'
    }
  ]
})

router.beforeEach((to, from, next) => {
  next()
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  template: '<AccessPage/>',
  router,
  components: {AccessPage}
})
