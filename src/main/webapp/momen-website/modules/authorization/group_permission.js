/**
 * Created by tanxinzheng on 16/7/3.
 */
define(function(){
    return ["$scope", "$modal", "GroupPermissionAPI", "$dialog", function($scope, $modal, GroupPermissionAPI, $dialog){
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
            $scope.getGroupPermissionList();
        };
        $scope.queryParam = {};
        // 查询列表
        $scope.getGroupPermissionList = function(){
            $scope.pageSetting.queryBtnLoading = true;
            GroupPermissionAPI.query({
                keyword: $scope.queryParam.keyword,
                limit: $scope.pageInfoSetting.pageSize,
                offset: $scope.pageInfoSetting.pageNum
            }, function(data){
                $scope.groupPermissionList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
            }).$promise.finally(function(){
                $scope.pageSetting.queryBtnLoading = false;
                });
        };
        // 全选
        $scope.checkAll = function(){
            if(!$scope.groupPermissionList){
                return;
            }
            for (var i = 0; i < $scope.groupPermissionList.length; i++) {
                $scope.groupPermissionList[i].checked = $scope.pageSetting.checkAll;
            }
        };
        // 子集控制全选
        $scope.changeItemChecked = function(){
            if(!$scope.groupPermissionList){
                return;
            }
            var num = 0;
            for (var i = 0; i < $scope.groupPermissionList.length; i++) {
                if($scope.groupPermissionList[i].checked){
                    num++;
                }
            }
            // 子集勾选数量等于集合总数则勾选全选，否则取消全选
            if(num == $scope.groupPermissionList.length){
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
                templateUrl: 'groupPermission_detail.html',
                modal:true,
                resolve: {
                    Params: function () {
                        var params = {
                            action: action
                        };
                        if($scope.groupPermissionList[index] && $scope.groupPermissionList[index].id){
                            params.id = $scope.groupPermissionList[index].id;
                        }
                        return params;
                    }
                },
                controller: ['$scope', '$modalInstance', "$modal", "GroupPermissionAPI", "Params", "$dialog", function($scope, $modalInstance, $modal, GroupPermissionAPI, Params, $dialog){
                    //$scope.groupPermission = null;
                    $scope.pageSetting = {
                        formDisabled : true,
                        saveBtnLoading : false
                    };
                    if(Params.action == "UPDATE" || Params.action == "ADD"){
                        $scope.pageSetting.formDisabled = false;
                    }
                    if(Params && Params.id){
                        $scope.groupPermission = GroupPermissionAPI.get({
                            id: Params.id
                        });
                    }
                    $scope.groupPermissionDetailForm = {};
                    $scope.saveGroupPermission = function(){
                        if($scope.groupPermissionDetailForm.validator.form()){
                            if($scope.groupPermissionDetailForm.validator.form()){
                                $dialog.confirm("是否保存数据？").then(function(){
                                    $scope.pageSetting.saveBtnLoading = true;
                                    if ( !$scope.groupPermission.id ) {
                                        GroupPermissionAPI.create($scope.groupPermission, function(data,headers){
                                            $dialog.success("新增成功");
                                            $modalInstance.close();
                                        }).$promise.finally(function(){
                                            $scope.pageSetting.saveBtnLoading = false;
                                        });
                                    }else {
                                        GroupPermissionAPI.update($scope.groupPermission, function(data,headers){
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
                $scope.getGroupPermissionList();
            });
        };
        // 删除
        $scope.delete = function(index){
            $dialog.confirm("请确认是否删除").then(function(){
                GroupPermissionAPI.delete({id:$scope.groupPermissionList[index].id}, function(){
                    $scope.getGroupPermissionList();
                });
            });
        };
        // 批量删除
        $scope.batchDelete = function(){
            var choiceItems = [];
            for (var i = 0; i < $scope.groupPermissionList.length; i++) {
                var obj = $scope.groupPermissionList[i];
                if(obj.checked){
                    choiceItems.push(obj.id);
                }
            }
            if(choiceItems && choiceItems.length > 0){
                $dialog.confirm("已勾选记录数：" + choiceItems.length + "，请确认是否删除已勾选数据").then(function(){
                    GroupPermissionAPI.delete({ids:choiceItems}, function(){
                        $scope.getGroupPermissionList();
                    });
                })
            }else{
                $dialog.alert("请勾选需要删除的数据");
            }
        };
        // 导出
        $scope.batchExport = function(){
            GroupPermissionAPI.export({
                data:{keyword: $scope.queryParam.keyword}
            });
        };
        var init = function(){
            $scope.getGroupPermissionList();
        };
        init();
    }]
});