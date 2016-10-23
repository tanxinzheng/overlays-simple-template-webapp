/**
 * Created by tanxinzheng on 16/7/3.
 */
define(function(){
    return ["$scope", "$modal", "SystemNotificationAPI", "$dialog", function($scope, $modal, SystemNotificationAPI, $dialog){
        $scope.pageSetting = {
            checkAll : false,
            queryBtnLoading : false
        };
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        // 重置
        $scope.reset = function(){
            $scope.queryParam={};
            $scope.getSystemNotificationList();
        };
        $scope.queryParam = {};
        // 查询列表
        $scope.getSystemNotificationList = function(){
            $scope.pageSetting.queryBtnLoading = true;
            SystemNotificationAPI.query({
                keyword: $scope.queryParam.keyword,
                limit: $scope.pageInfoSetting.pageSize,
                offset: $scope.pageInfoSetting.pageNum
            }, function(data){
                $scope.systemNotificationList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getSystemNotificationList;
            }).$promise.finally(function(){
                $scope.pageSetting.queryBtnLoading = false;
                });
        };
        // 全选
        $scope.checkAll = function(){
            if(!$scope.systemNotificationList){
                return;
            }
            for (var i = 0; i < $scope.systemNotificationList.length; i++) {
                $scope.systemNotificationList[i].checked = $scope.pageSetting.checkAll;
            }
        };
        // 子集控制全选
        $scope.changeItemChecked = function(){
            if(!$scope.systemNotificationList){
                return;
            }
            var num = 0;
            for (var i = 0; i < $scope.systemNotificationList.length; i++) {
                if($scope.systemNotificationList[i].checked){
                    num++;
                }
            }
            // 子集勾选数量等于集合总数则勾选全选，否则取消全选
            if(num == $scope.systemNotificationList.length){
                $scope.pageSetting.checkAll = true;
            }else{
                $scope.pageSetting.checkAll = false;
            }
        };
        // 新增
        $scope.add = function(index){
            $scope.openModal(index, "ADD");
        };
        // 查看
        $scope.view = function(index){
            $scope.openModal(index, "VIEW");
        };
        // 修改
        $scope.update = function(index){
            $scope.openModal(index, "UPDATE");
        };
        // 弹出
        $scope.openModal = function(index, action){
            $modal.open({
                templateUrl: 'systemNotification_detail.html',
                modal:true,
                resolve: {
                    Params: function () {
                        var params = {
                            action: action
                        };
                        if($scope.systemNotificationList[index] && $scope.systemNotificationList[index].id){
                            params.id = $scope.systemNotificationList[index].id;
                        }
                        return params;
                    }
                },
                controller: ['$scope', '$modalInstance', "$modal", "SystemNotificationAPI", "Params", "$dialog", function($scope, $modalInstance, $modal, SystemNotificationAPI, Params, $dialog){
                    //$scope.systemNotification = null;
                    $scope.pageSetting = {
                        formDisabled : true,
                        saveBtnLoading : false
                    };
                    if(Params.action == "UPDATE" || Params.action == "ADD"){
                        $scope.pageSetting.formDisabled = false;
                    }
                    if(Params && Params.id){
                        $scope.systemNotification = SystemNotificationAPI.get({
                            id: Params.id
                        });
                    }
                    $scope.systemNotificationDetailForm = {};
                    $scope.saveSystemNotification = function(){
                        if($scope.systemNotificationDetailForm.validator.form()){
                            if($scope.systemNotificationDetailForm.validator.form()){
                                $dialog.confirm("是否保存数据？").then(function(){
                                    $scope.pageSetting.saveBtnLoading = true;
                                    if ( !$scope.systemNotification.id ) {
                                        SystemNotificationAPI.create($scope.systemNotification, function(data,headers){
                                            $dialog.success("新增成功");
                                            $modalInstance.close();
                                        }).$promise.finally(function(){
                                            $scope.pageSetting.saveBtnLoading = false;
                                        });
                                    }else {
                                        SystemNotificationAPI.update($scope.systemNotification, function(data,headers){
                                            $dialog.success("更新成功");
                                            $modalInstance.close();
                                        }).$promise.finally(function(){
                                            $scope.pageSetting.saveBtnLoading = false;
                                        });
                                    }
                                });
                            }
                        }
                    };
                    $scope.cancel = function(){
                        $modalInstance.dismiss();
                    };
                }]
            }).result.then(function () {
                $scope.getSystemNotificationList();
            });
        };
        // 删除
        $scope.delete = function(index){
            $dialog.confirm("请确认是否删除").then(function(){
                SystemNotificationAPI.delete({id:$scope.systemNotificationList[index].id}, function(){
                    $scope.getSystemNotificationList();
                });
            });
        };
        // 批量删除
        $scope.batchDelete = function(){
            var choiceItems = [];
            for (var i = 0; i < $scope.systemNotificationList.length; i++) {
                var obj = $scope.systemNotificationList[i];
                if(obj.checked){
                    choiceItems.push(obj.id);
                }
            }
            if(choiceItems && choiceItems.length > 0){
                $dialog.confirm("已勾选记录数：" + choiceItems.length + "，请确认是否删除已勾选数据").then(function(){
                    SystemNotificationAPI.delete({ids:choiceItems}, function(){
                        $scope.getSystemNotificationList();
                    });
                })
            }else{
                $dialog.alert("请勾选需要删除的数据");
            }
        };
        // 导出
        $scope.batchExport = function(){
            SystemNotificationAPI.export({
                data:{keyword: $scope.queryParam.keyword}
            });
        };
        var init = function(){
            $scope.getSystemNotificationList();
        };
        init();
    }]
});