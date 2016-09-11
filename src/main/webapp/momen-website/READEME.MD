# 结构划分
```

|--vendor               //可懒加载文件目录（主要为各页面中所用到的控件js及css文件）
|--core                 //必须加载js文件
|--css                  //必须加载css文件
|--js                   //应用相关配置（主要修改此目录）
    |--config           //应用个性化配置，如，页头标题，页尾标题，网站版权信息，国际化等
    |--config.router    //路由配置
    |--config.lazyload  //懒加载配置
|--modules              //页面功能模块（主要修改此目录）
    |--template         //公用模板目录（导航，头部，底部）
        |--header.html  //头部
        |--aside.html   //边栏
        |--nav.html     //导航
        |--footer.html  //底部
    |--i10n             //国际化文件目录
        |--cn.json      //中文
        |--en.json      //英文
        |--...
    |--tools            //各类工具文件（根据业务自定义的指令过滤器等）
        |--directives   //业务性指令
        |--filters      //业务性过滤性
        |--...
    |--user             //功能模块－用户模块
        |--user.js      //用户列表js
        |--user.html    //用户列表html
    |--system
        |--permission.js
        |--permission.html
    |--...
|--index.html           //首页html
|--app.js               //require依赖配置文件
|--
```

## 架构设计
前端架构分为核心模块，配置模块，插件模块，业务模块。

### 核心模块
目录