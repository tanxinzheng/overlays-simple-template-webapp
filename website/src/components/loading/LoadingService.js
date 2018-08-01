/**
 * Created by tanxinzheng on 17/12/10.
 */
import Bus from '../factory/bus'
import Constant from '../factory/constant'

export default {
  close: function () {
    Bus.$emit(Constant.httpLoading, false)
  },
  open: function (option) {
    Bus.$emit(Constant.httpLoading, true)
  }
}
