/**
 * Created by tanxinzheng on 2018/5/26.
 */
export default {
  name: 'my-slot',

  data () {
    return {
      actionsClasses: 'datatable__actions',
      actionsRangeControlsClasses: 'datatable__actions__range-controls',
      actionsSelectClasses: 'datatable__actions__select',
      actionsPaginationClasses: 'datatable__actions__pagination'
    }
  },

  methods : {
    genHeaderData (header, children) {
      const classes = ['column']
      const data = {
        key: header[this.headerText],
        attrs: {
          role: 'columnheader',
          scope: 'col',
          width: header.width || null,
          'aria-label': header[this.headerText] || '',
          'aria-sort': 'none'
        }
      }

      classes.push(`text-xs-${header.align || 'left'}`)
      if (Array.isArray(header.class)) {
        classes.push(...header.class)
      } else if (header.class) {
        classes.push(header.class)
      }
      data.class = classes

      return [data, children]
    },
    genHeader (header) {
      const array = [header[this.headerText]]
      return this.$createElement('th', ...this.genHeaderData(header, array))
    },
    getSlotHeader () {
      let children = []
      const row = this.headers.map(o => this.genHeader(o))
      children = [this.$createElement('tr', row, children)]
      return this.$createElement('thead', [children])
    },
    getSlotBody () {
      const items = this.items;
      const row = [];
      for (let obj of items) {
        row.push(this.$createElement('h4', this.$scopedSlots.body(obj)))
      }
      console.log('body elem', row)
      return this.$createElement('div', row);
    }
  },

  props : {
    headerText: {
      type: String,
      default: 'label'
    },
    items: {
      require: true,
      type: Array
    },
    headers: {
      type: Array
    }
  },

  render:function(createElement){
    let he=createElement('div',{
      domProps: {innerHTML:'this child div'}
    });
    return createElement('div',
      [ he,
        this.getSlotHeader(),
        this.getSlotBody()
      ])
  }
}
