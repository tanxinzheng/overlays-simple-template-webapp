/**
 * Created by tanxinzheng on 2018/6/9.
 */
export default {
  props: {
    pagingSizeOption: {
      type: Array,
      require: false,
      default () {
        return [
          10, 30, 50, 100, 200, 300, 500
        ]
      }
    }
  },
  data () {
    return {
      pagingInfo: {
        pageNum: 1
      },
      maxSize: this.pagingSizeOption[0]
    }
  },
  methods: {
    skipPage: function (num) {
      this.pagingInfo.pageNum = num
      this.loadData()
    }
  },
  watch: {
    maxSize (data) {
      this.pagingInfo.pageSize = data
      this.pagingInfo.pageNum = 1
      this.loadData()
    }
  }
}
