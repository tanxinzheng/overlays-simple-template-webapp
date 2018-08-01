/**
 * Created by tanxinzheng on 2018/5/27.
 */
export default {
  methods: {
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
    genDefaultHeader (header) {
      const array = [header[this.headerText]]
      return this.$createElement('th', ...this.genHeaderData(header, array))
    },
    getHeader () {
      let children = []
      const row = this.headers.map(o => this.genDefaultHeader(o))
      children = [this.$createElement('tr', row, children)]
      return this.$createElement('thead', [children])
    }
  }
}
