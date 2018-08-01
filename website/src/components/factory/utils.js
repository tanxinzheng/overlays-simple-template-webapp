/**
 * Created by tanxinzheng on 17/12/10.
 */
import Bus from './bus'
import Constant from './constant'

export default {
  $alert: function (text, option) {
    return new Promise((resolve, reject) => {
      Bus.$emit(Constant.eventKeyAlert, {
        text: text,
        option: option,
        resolve: resolve,
        reject: reject
      })
    })
  },
  $confirm: function (text) {
    return new Promise((resolve, reject) => {
      Bus.$emit(Constant.eventKeyConfirm, {
        text: text,
        resolve: resolve,
        reject: reject
      })
    })
  }
}
