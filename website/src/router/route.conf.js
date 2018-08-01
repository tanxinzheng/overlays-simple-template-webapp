/**
 * Created by tanxinzheng on 17/12/7.
 */

const Home = () => import('@/modules/home.vue')
const Demo = () => import('@/modules/demo.vue')
// const User = () => import('@/modules/admin/user.vue')
// const ActionLog = () => import('@/modules/admin/actionLog.vue')
// const Dictionary = () => import('@/modules/admin/dictionary.vue')
// const UserGroup = () => import('@/modules/admin/userGroup.vue')
// const Job = () => import('@/modules/admin/job.vue')
// const Permission = () => import('@/modules/admin/permission.vue')

export default [
  {
    path: '/',
    name: 'Home',
    text: '个人中心',
    component: Home
  },
  {
    path: '/demo',
    name: 'Demo',
    text: '演示',
    component: Demo
  }
]
