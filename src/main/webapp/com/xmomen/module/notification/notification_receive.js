define(function(){
    return ["$scope",  "NotificationReceiveAPI", "uiaDialog", "$injector", "$uibModal", function($scope, NotificationReceiveAPI, uiaDialog, $injector, $uibModal){
        $scope.gridOption = {
            id:"user",
            title:'通知接收人',
            loadEvent: NotificationReceiveAPI.query,
            ApiService: NotificationReceiveAPI,
            // 过滤条件列配置
            filters:[
                { name:'keyword', title:'关键字', placeholder:'请输入关键字' }
            ],
            columns:[
                { name:'id', title:'主键' },
                { name:'notificationId', title:'关联通知主键' },
                { name:'receiveTime', title:'接收时间', type:'date' },
                { name:'receiver', title:'接收人' },
                { name:'dataState', title:'状态' },
            ],
            boxOption : {
                ApiService: NotificationReceiveAPI,
                columns:[
                    { name:'notificationId', title:'关联通知主键', rules:{ required: true} },
                    { name:'receiveTime', title:'接收时间', type:'date', rules:{ required: true} },
                    { name:'receiver', title:'接收人', rules:{ required: true} },
                    { name:'dataState', title:'状态', rules:{ required: true} },
                ]
            }
        };
    }]
});