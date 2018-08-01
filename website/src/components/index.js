import Vue from 'vue'
import Vuex from 'vuex'
import VueAxios from 'vue-axios'
import axios from './api'
import LoadingService from './loading/LoadingService'
import underscore from 'underscore'

import Store from './store/index'
import Breadcrumb from './breadcrumbs/Breadcrumb.vue'
import Table from './table/Table.vue'
import Dialog from './dialog/Dialog.vue'
import Alert from './alert/Alert.vue'
import FormData from './form/FormData.vue'
import Loading from './loading/Loading.vue'
import Avatar from './avatar/Avatar.vue'
import UploaderText from './uploader/UploaderText.vue'
import DataTable from './XDataTable/index'
import Demo from './demo/index'

import utils from './factory/utils'

const VueMo = {
  store: Store,
  components: [
    Table,
    Dialog,
    Alert,
    FormData,
    Loading,
    Breadcrumb,
    Avatar,
    UploaderText,
    DataTable
  ],
  factorys: [
    utils,
    LoadingService
  ]
}

Vue.use(Vuex)
Vue.use(VueAxios, axios)

VueMo.install = function (Vue) {
  for (let component of VueMo.components) {
    Vue.component(component.name, component)
  }

  Vue.prototype._ = underscore
  for (let factory of VueMo.factorys) {
    for (let prop in factory) {
      Vue.prototype[prop] = factory[prop]
    }
  }
}
export default VueMo
