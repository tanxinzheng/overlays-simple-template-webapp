/**
 * Created by tanxinzheng on 2018/5/26.
 */
import head from './mixins/thead'
import body from './mixins/tbody'

export default {
  name: 'x-data-table',

  mixins: [
    head,
    body
  ],

  data () {
    return {
      actionsClasses: 'datatable__actions',
      actionsRangeControlsClasses: 'datatable__actions__range-controls',
      actionsSelectClasses: 'datatable__actions__select',
      actionsPaginationClasses: 'datatable__actions__pagination',
    }
  },

  methods : {

  },

  props : {
    /**
     * header text属性名，默认：label
     */
    headerText: {
      type: String,
      default: 'label'
    },
    /**
     * 列表数据
     */
    items: {
      require: true,
      type: Array
    },
    /**
     * 表头配置
     */
    headers: {
      require: true,
      type: Array
    }
  },
  render: function(createElement){
    const data = {};
    data.class = ['table__overflow'];
    const tableEle = createElement('table',
      {
        'class': [
          'datatable',
          'table'
        ]
      },
      [ this.getHeader(),
        this.getBody()
      ])
    const tableOverflow = createElement('div', data, [tableEle]);
    return tableOverflow;
  }
}
