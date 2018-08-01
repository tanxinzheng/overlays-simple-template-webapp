import XDataTable from './XDataTable'

export { XDataTable }

/* istanbul ignore next */
XDataTable.install = function install (Vue) {
  Vue.component(XDataTable.name, XDataTable)
}

export default XDataTable
