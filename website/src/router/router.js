import Vue from 'vue'
import Vuex from 'vuex'
import Router from 'vue-router'
import Access from '../components/factory/access'
import routerItem from './route.conf'
import adminRouter from '../modules/admin'
import accountRouter from '../modules/account'

Vue.use(Router)
Vue.use(Vuex)

const buildMeta = function (item) {
  for (var i = 0; i < item.length; i++) {
    if(item[i].meta){
      item[i].meta.text = item[i].text
    }else{
      item[i].meta = {
        text: item[i].text
      }
    }
    if(item[i].children){
      buildMeta(item[i].children)
    }
  }
  for (let obj of item) {
    obj.meta = {
      text: obj.text
    }
  }
}

routerItem.push(adminRouter)
routerItem.push(accountRouter)

buildMeta(routerItem)

const routers = new Router({
  // mode: 'history',
  // base: __dirname,
  routes: routerItem
})

routers.beforeEach((to, from, next) => {

  if(to.matched){
    let matched = to.matched;
    let breadcrumbs = [];
    for (let obj of matched) {
      breadcrumbs.push({
        text: obj.meta.text,
        path: obj.path,
        name: obj.name
      });
    }
    routers.app.$options.store.commit('updateBreadcrumbs', breadcrumbs)
  }
  if (Access.isAuthenticated()) {
    next()
  } else {
    Access.logout()
  }
})

export default routers
