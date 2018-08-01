/**
 * Created by tanxinzheng on 2018/6/15.
 */
export default {
  data () {
    return {
      checkboxAll: {
        selected: false
      },
      selected: []
    }
  },
  methods: {
    toggleAll () {
      if (!this.datasource) {
        return
      }
      this.selected = []
      for (var i = 0; i < this.datasource.length; i++) {
        this.datasource[i].checked = this.checkboxAll.selected
        if (this.datasource[i].checked) {
          this.selected.push(this.datasource[i])
        }
      }
      if (this.selected.length > 0 && this.selected.length === this.datasource.length) {
        this.$set(this.checkboxAll, 'indeterminate', false)
        this.$set(this.checkboxAll, 'selected', true)
      }
    },
    unCheckedAll () {
      if (!this.datasource) {
        return
      }
      let num = 0
      this.selected = []
      for (let i = 0; i < this.datasource.length; i++) {
        if (this.datasource[i].checked) {
          num++
          this.selected.push(this.datasource[i])
        }
      }
      // 子集勾选数量等于集合总数则勾选全选，否则取消全选
      if (num === 0) {
        this.$set(this.checkboxAll, 'indeterminate', false)
        this.$set(this.checkboxAll, 'selected', false)
      } else if (num === this.datasource.length) {
        this.$set(this.checkboxAll, 'indeterminate', false)
        this.$set(this.checkboxAll, 'selected', true)
      } else {
        this.$set(this.checkboxAll, 'indeterminate', true)
        this.$set(this.checkboxAll, 'selected', false)
      }
    },
    clearCheckState () {
      let vm = this;
      this.$set(this.checkboxAll, 'indeterminate', false)
      this.$set(this.checkboxAll, 'selected', false)
    }
  }
}
