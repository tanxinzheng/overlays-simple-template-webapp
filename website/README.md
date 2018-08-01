# xmomen-vue-website

> A Vue.js project

## Build Setup

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report

# run unit tests
npm run unit

# run e2e tests
npm run e2e

# run all tests
npm test
```

For a detailed explanation on how things work, check out the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).

## Issue

##### npm run build 出现: Unexpected token: name (parent) ....
归因分析：因为UglifyJs是不支持打包包含ES6的代码
解决方案：添加npm install babili-webpack-plugin --save-dev
参考引用：https://blog.csdn.net/xuelang532777032/article/details/78278232

