import Demo from './demo'

export { Demo }

/* istanbul ignore next */
Demo.install = function install (Vue) {
  Vue.component(Demo.name, Demo)
}

export default Demo
