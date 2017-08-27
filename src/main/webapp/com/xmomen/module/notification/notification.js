define(function(){
    return ["$scope",  "NotificationAPI", "uiaDialog", "$injector", "$uibModal", function($scope, NotificationAPI, uiaDialog, $injector, $uibModal){
        $scope.gridOption = {
            id:"user",
            title:'通知',
            loadEvent: NotificationAPI.query,
            ApiService: NotificationAPI,
            // 过滤条件列配置
            filters:[
                { name:'keyword', title:'关键字', placeholder:'请输入关键字' }
            ],
            columns:[
                { name:'id', title:'主键' },
                { name:'templateId', title:'模板主键' },
                { name:'title', title:'标题' },
                { name:'body', title:'内容' },
                { name:'expireTime', title:'失效时间', type:'date' },
                { name:'notificationType', title:'通知类型' },
            ],
            boxOption : {
                ApiService: NotificationAPI,
                columns:[
                    { name:'templateId', title:'模板主键', rules:{ required: true} },
                    { name:'title', title:'标题', rules:{ required: true} },
                    { name:'body', title:'内容', rules:{ required: true} },
                        { name:'expireTime', title:'失效时间', type:'date' },
                    { name:'notificationType', title:'通知类型', rules:{ required: true} },
                ]
            }
        };
    }]
});