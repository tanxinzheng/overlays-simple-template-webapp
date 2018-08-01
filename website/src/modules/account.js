/**
 * Created by tanxinzheng on 18/5/13.
 */
const account = () => import('@/modules/account/account.vue')
const profile = () => import('@/modules/account/profile.vue')
const permission = () => import('@/modules/account/permission.vue')
const notification = () => import('@/modules/account/notification.vue')
const logs = () => import('@/modules/account/logs.vue')
let route = {
  name: 'account',
  path: '/account',
  text: '个人中心',
  component: account,
  children: [
    {
      id: 'profile',
      path: '/account/profile',
      name: 'profile',
      text: '我的简介',
      component: profile
    },
    {
      id: 'permission',
      path: '/permission',
      name: 'permission',
      text: '权限',
      component: permission
    },
    {
      id: 'log',
      path: '/logs',
      name: 'logs',
      text: '操作记录',
      component: logs
    },
    {
      id: 'notification',
      path: '/account/notification/:id',
      name: 'notification',
      text: '消息',
      component: notification
    }
  ]
}
route.children.forEach(obj=>{
  obj.name = route.name + '.' + obj.name;
})

export default route
