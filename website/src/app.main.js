// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Vuex from 'vuex'
import App from './app'
import router from './router/router'
import Vuetify from 'vuetify'
import uploader from 'vue-simple-uploader'
import VueMo from './components/index'
import Access from './components/factory/access'
import VueStomp from "vue-stomp";

// <!--<script src="http://cdn.bootcss.com/sockjs-client/1.1.1/sockjs.min.js"></sc/*ript>-->
//   <!--<script src="http://cdn.bootcss.com/stomp.js/2.3.3/stomp.js"></script>-->*/

// import 'vuetify/dist/vuetify.css'
import 'font-awesome/css/font-awesome.css'
import './assets/font.css'
import './stylus/main.styl'

Vue.config.productionTip = false
Vue.use(Vuex)
Vue.use(Vuetify)
Vue.use(VueMo)
Vue.use(uploader)
Vue.use(VueStomp, "http://localhost:8080/endpoint");

let store = VueMo.store

/* eslint-disable no-new */
if (Access.isAuthenticated()) {
  new Vue({
    el: '#app',
    store,
    router,
    template: '<App/>',
    components: {App}
  })
}
