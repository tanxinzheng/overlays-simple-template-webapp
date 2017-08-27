define(function(){
    return ["$scope",  "NotificationTemplateAPI", "uiaDialog", "$injector", "$uibModal", function($scope, NotificationTemplateAPI, uiaDialog, $injector, $uibModal){
        $scope.gridOption = {
            id:"user",
            title:'通知模板',
            loadEvent: NotificationTemplateAPI.query,
            ApiService: NotificationTemplateAPI,
            // 过滤条件列配置
            filters:[
                { name:'keyword', title:'关键字', placeholder:'请输入关键字' }
            ],
            columns:[
                { name:'templateName', title:'模板名称' },
                { name:'templateTitle', title:'模板标题' },
                { name:'templateBody', title:'模板内容' },
                { name:'templateCode', title:'模板代码' },
                { name:'active', title:'是否启用', type:'checkbox' },
            ],
            boxOption : {
                ApiService: NotificationTemplateAPI,
                columns:[
                    { name:'templateName', title:'模板名称', rules:{ required: true} },
                    { name:'templateTitle', title:'模板标题', rules:{ required: true} },
                    { name:'templateBody', title:'模板内容', rules:{ required: true} },
                    { name:'templateCode', title:'模板代码', rules:{ required: true} },
                    { name:'active', title:'是否启用', type:'checkbox' },
                ]
            }
        };
    }]
});