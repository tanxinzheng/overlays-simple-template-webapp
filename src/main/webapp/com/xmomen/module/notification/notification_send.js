define(function(){
    return ["$scope",  "NotificationSendAPI", "uiaDialog", "$injector", "$uibModal", function($scope, NotificationSendAPI, uiaDialog, $injector, $uibModal){
        $scope.gridOption = {
            id:"user",
            title:'通知发送人',
            loadEvent: NotificationSendAPI.query,
            ApiService: NotificationSendAPI,
            // 过滤条件列配置
            filters:[
                { name:'keyword', title:'关键字', placeholder:'请输入关键字' }
            ],
            columns:[
                { name:'id', title:'主键' },
                { name:'notificationId', title:'通知内容表主键' },
                { name:'sender', title:'发送人' },
                { name:'sendTime', title:'发送时间', type:'date' },
                { name:'dataState', title:'状态' },
            ],
            boxOption : {
                ApiService: NotificationSendAPI,
                columns:[
                    { name:'notificationId', title:'通知内容表主键', rules:{ required: true} },
                    { name:'sender', title:'发送人', rules:{ required: true} },
                    { name:'sendTime', title:'发送时间', type:'date', rules:{ required: true} },
                    { name:'dataState', title:'状态', rules:{ required: true} },
                ]
            }
        };
    }]
});