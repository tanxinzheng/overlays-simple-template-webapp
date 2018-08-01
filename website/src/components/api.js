/**
 * Created by tanxinzheng on 17/9/21.
 */
import axios from 'axios'
import promise from 'promise.prototype.finally'
import Bus from './factory/bus'
import Access from './factory/access'
import LoadingService from './loading/LoadingService'
import Env from '../env'

promise.shim()

const Axios = axios.create({
  baseURL: Env.endPoint, // 因为我本地做了反向代理
  timeout: 10000,
  responseType: 'json',
  withCredentials: true // 是否允许带cookie这些
  // headers: {
  //   'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
  // }
})


// token拦截器
Axios.interceptors.request.use(
  config => {
    // 若是有做鉴权token , 就给头部带上token
    let token = localStorage.getItem('token')
    if (token) {
      config.headers.Authorization = token
    }
    LoadingService.open()
    return config
  }
)

// 返回状态判断(添加响应拦截器)
Axios.interceptors.response.use(
  res => {
    LoadingService.close()
    // 对响应数据做些事
    if (res.status !== 200) {
      if (res.data && res.data.message) {
        Bus.$emit('globalAlert', {
          text: res.data.message
        })
      }
      return Promise.reject(res.data)
    }
    return res
  },
  error => {
    LoadingService.close()
    if ((error.response
      && error.response.status === 401
      && error.config.url !== '/login')
      || error.response.status === 504) {
      Access.logout()
    } else {
      if (!error.config.ignoreTip && error.response && error.response.data) {
        Bus.$emit('globalAlert', {
          text: error.response.data.message
        })
      }
    }
    return Promise.reject(error.response)
  }
)

export default Axios
