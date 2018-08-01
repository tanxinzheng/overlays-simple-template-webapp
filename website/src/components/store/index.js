/**
 * Created by tanxinzheng on 2018/6/9.
 */
import vue from 'vue'
import vuex from 'vuex'
vue.use(vuex);
export default new vuex.Store({
  state:{
    breadcrumbs: [],
    alert:{}
  },
  /**
   * 同步方法
   */
  mutations: {
    updateBreadcrumbs: (state, data) => {
      state.breadcrumbs = data
    }
  },
  /**
   * 异步方法
   */
  actions: {

  }
});
