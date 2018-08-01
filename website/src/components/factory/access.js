/**
 * Created by tanxinzheng on 17/12/11.
 */
export default {
  loginHandler: function (token) {
    window.localStorage.setItem('token', token)
    this.redirectIndexPage()
  },
  logout: function () {
    localStorage.removeItem('token')
    localStorage.setItem('redirect-url', window.location.href);
    this.redirectLoginPage()
  },
  isAuthenticated: function () {
    const token = localStorage.getItem('token')
    if (!token) {
      localStorage.setItem('redirect-url', window.location.href);
      window.location.href = '/access.html#/login'
      return false
    }
    return true
  },
  redirectLoginPage: function () {
    window.location.href = '/access.html#/login'
  },
  redirectIndexPage: function () {
    let url = localStorage.getItem('redirect-url');
    if (url) {
      localStorage.setItem('redirect-url', null);
      window.location.href = url
    } else {
      window.location.href = '/index.html'
    }
  }
}
