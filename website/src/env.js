/**
 * Created by tanxinzheng on 2018/6/14.
 */
const env = process.env.NODE_ENV === 'production'
  ? require('../config/prod.env') : require('../config/dev.env')

export default env
