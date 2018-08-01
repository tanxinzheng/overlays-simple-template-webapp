/**
 * Created by tanxinzheng on 18/5/13.
 */
const Admin = () => import('@/modules/admin/admin.vue')
const User = () => import('@/modules/admin/user.vue')
const ActionLog = () => import('@/modules/admin/actionLog.vue')
const Dictionary = () => import('@/modules/admin/dictionary.vue')
const UserGroup = () => import('@/modules/admin/userGroup.vue')
const Job = () => import('@/modules/admin/job.vue')
const Permission = () => import('@/modules/admin/permission.vue')
const Settings = () => import('@/modules/admin/settings.vue')
const Attachment = () => import('@/modules/admin/attachment.vue')
const Notification = () => import('@/modules/admin/notification.vue')

const admin = {
  name: 'admin',
  path: '/management',
  text: '管理中心',
  component: Admin,
  children: [
    {
      icon: 'fa-user',
      path: '/management/user',
      name: 'user',
      text: '用户',
      component: User
    },
    {
      icon: 'fa-users',
      path: '/management/group',
      name: 'group',
      text: '用户组',
      component: UserGroup
    },
    {
      icon: 'fa-clock-o',
      path: '/management/job',
      name: 'job',
      text: '定时任务',
      component: Job
    },
    {
      icon: 'fa-book',
      path: '/management/dictionary',
      name: 'dictionary',
      text: '数据字典',
      component: Dictionary
    },
    {
      icon: 'fa-pencil-square-o',
      path: '/management/action_log',
      name: 'action_log',
      text: '操作日志',
      component: ActionLog
    },
    {
      icon: 'fa-key',
      path: '/management/permission',
      name: 'permission',
      text: '权限',
      component: Permission
    },
    {
      icon: 'message',
      path: '/management/notification',
      name: 'notification',
      text: '消息',
      component: Notification
    },
    {
      icon: 'attach_file',
      path: '/management/attachment',
      name: 'attachment',
      text: '文件库',
      component: Attachment
    },
    {
      icon: 'fa-list',
      path: '/management/settings',
      name: 'settings',
      text: '系统配置',
      component: Settings
    }
  ]
}

admin.children.forEach(obj => {
  obj.name = admin.name + '.' + obj.name;
})

export default admin
