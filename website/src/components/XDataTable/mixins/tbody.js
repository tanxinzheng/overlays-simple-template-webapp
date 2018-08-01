/**
 * Created by tanxinzheng on 2018/5/27.
 */
export default {
  methods: {
    genBodyData (item, children) {
      const data = {
        domProps:{
          innerHTML:item[children]
        }
      }
      return [data, children]
    },
    genSlotBody () {
      const children = [];
      for (let obj of this.items) {
        const row = this.$scopedSlots.body(obj);
        children.push(this.$createElement('tr', row, children))
      }
      return children;
    },
    genDefaultBody (item) {
      const cols = [];
      for (let header of this.headers) {
        const array = [header.prop]
        const col = this.$createElement('td', ...this.genBodyData(item, array))
        cols.push(col);
      }
      return cols
    },
    getBody () {
      let children = []
      if(this.$scopedSlots.body){
        children = this.genSlotBody();
      } else {
        const row = this.headers.map(o => this.genDefaultHeader(o))
        children = [this.$createElement('tr', row, children)]
        for (let obj of this.items) {
          const row = this.genDefaultBody(obj)
          children.push(this.$createElement('tr', row, children))
        }
      }
      return this.$createElement('tbody', [children])
    }
  }
}
