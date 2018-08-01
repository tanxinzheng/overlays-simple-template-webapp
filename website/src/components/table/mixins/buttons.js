/**
 * Created by tanxinzheng on 2018/6/15.
 */
const defaultEnableBtn = false
let vm
export default {
  created () {
    vm = this
  },
  props: {
    showCheckboxAll: {
      require: false,
      default: defaultEnableBtn
    },
    showAttachButton: {
      require: false,
      default: defaultEnableBtn
    },
    showCreateButton: {
      require: false,
      default: defaultEnableBtn
    },
    showDeleteButton: {
      require: false,
      default: defaultEnableBtn
    },
    showViewButton: {
      require: false,
      default: defaultEnableBtn
    },
    actionButtons: {
      require: false
    },
    tableButtons: {
      require: false,
      default: function () {
        return [
          {
            label: '删除',
            icon: 'delete',
            click (item) {
              if(!item.id){
                return
              }
              vm.$confirm('是否删除所选数据？').then((data) => {
                vm.axios.delete(vm.url + '/' + item.id, {
                  disableGetBracketNotation: false
                }).then(() => {
                  vm.selected = []
                  vm.$alert('删除成功')
                  vm.loadData()
                })
              })
            }
          }
        ]
      }
    }
  },
  data () {
    return {
      uploadOption: {
        target: '/api' + this.url + "/import",
        testChunks: false,
        headers: {
          Authorization : localStorage.getItem('token')
        },
      }
    }
  },
  methods : {
    /**
     * 下载模板
     */
    downloadTemplate () {
      this.axios({
        method: 'get',
        url: this.url + '/template',
        data: {},
        responseType: 'blob'
      }).then(response => {
        if (!response.data) {
          return
        }
        let url = window.URL.createObjectURL(response.data)
        let link = document.createElement('a')
        link.id = 'download_' + new Date().getTime()
        link.style.display = 'none'
        link.href = url
        link.setAttribute('download', decodeURI(response.headers.filename))
        document.body.appendChild(link)
        link.click()
      })
    },
    /**
     * 导出文件
     */
    exportFile () {
      this.axios({
        method: 'get',
        url: this.url + '/export',
        data: {},
        responseType: 'blob'
      }).then(response => {
        if (!response.data) {
          return
        }
        let url = window.URL.createObjectURL(response.data)
        let link = document.createElement('a')
        link.id = 'export_' + new Date().getTime()
        link.style.display = 'none'
        link.href = url
        link.setAttribute('download', decodeURI(response.headers.filename))
        document.body.appendChild(link)
        link.click()
      })
    }
  }
}
