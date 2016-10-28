/**
 * Created by tanxinzheng on 16/7/3.
 */
define(function(){
    return ["$scope", "$modal", "GroupAPI", "$dialog", function($scope, $modal, GroupAPI, $dialog){
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
            $scope.getGroupList();
        };
        $scope.queryParam = {};
        // 查询列表
        $scope.getGroupList = function(){
            $scope.pageSetting.queryBtnLoading = true;
            GroupAPI.query({
                keyword: $scope.queryParam.keyword,
                limit: $scope.pageInfoSetting.pageSize,
                offset: $scope.pageInfoSetting.pageNum
            }, function(data){
                $scope.groupList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
            }).$promise.finally(function(){
                $scope.pageSetting.queryBtnLoading = false;
                });
        };
        // 全选
        $scope.checkAll = function(){
            if(!$scope.groupList){
                return;
            }
            for (var i = 0; i < $scope.groupList.length; i++) {
                $scope.groupList[i].checked = $scope.pageSetting.checkAll;
            }
        };
        // 子集控制全选
        $scope.changeItemChecked = function(){
            if(!$scope.groupList){
                return;
            }
            var num = 0;
            for (var i = 0; i < $scope.groupList.length; i++) {
                if($scope.groupList[i].checked){
                    num++;
                }
            }
            // 子集勾选数量等于集合总数则勾选全选，否则取消全选
            if(num == $scope.groupList.length){
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
                templateUrl: 'group_detail.html',
                modal:true,
                resolve: {
                    Params: function () {
                        var params = {
                            action: action
                        };
                        if($scope.groupList[index] && $scope.groupList[index].id){
                            params.id = $scope.groupList[index].id;
                        }
                        return params;
                    }
                },
                controller: ['$scope', '$modalInstance', "$modal", "GroupAPI", "Params", "$dialog", function($scope, $modalInstance, $modal, GroupAPI, Params, $dialog){
                    //$scope.group = null;
                    $scope.pageSetting = {
                        formDisabled : true,
                        saveBtnLoading : false
                    };
                    if(Params.action == "UPDATE" || Params.action == "ADD"){
                        $scope.pageSetting.formDisabled = false;
                    }
                    if(Params && Params.id){
                        $scope.group = GroupAPI.get({
                            id: Params.id
                        });
                    }
                    $scope.groupDetailForm = {};
                    $scope.saveGroup = function(){
                        if($scope.groupDetailForm.validator.form()){
                            if($scope.groupDetailForm.validator.form()){
                                $dialog.confirm("是否保存数据？").then(function(){
                                    $scope.pageSetting.saveBtnLoading = true;
                                    if ( !$scope.group.id ) {
                                        GroupAPI.create($scope.group, function(data,headers){
                                            $dialog.success("新增成功");
                                            $modalInstance.close();
                                        }).$promise.finally(function(){
                                            $scope.pageSetting.saveBtnLoading = false;
                                        });
                                    }else {
                                        GroupAPI.update($scope.group, function(data,headers){
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
                $scope.getGroupList();
            });
        };
        // 删除
        $scope.delete = function(index){
            $dialog.confirm("请确认是否删除").then(function(){
                GroupAPI.delete({id:$scope.groupList[index].id}, function(){
                    $scope.getGroupList();
                });
            });
        };
        // 批量删除
        $scope.batchDelete = function(){
            var choiceItems = [];
            for (var i = 0; i < $scope.groupList.length; i++) {
                var obj = $scope.groupList[i];
                if(obj.checked){
                    choiceItems.push(obj.id);
                }
            }
            if(choiceItems && choiceItems.length > 0){
                $dialog.confirm("已勾选记录数：" + choiceItems.length + "，请确认是否删除已勾选数据").then(function(){
                    GroupAPI.delete({ids:choiceItems}, function(){
                        $scope.getGroupList();
                    });
                })
            }else{
                $dialog.alert("请勾选需要删除的数据");
            }
        };
        // 导出
        $scope.batchExport = function(){
            GroupAPI.export({
                data:{keyword: $scope.queryParam.keyword}
            });
        };
        // 组权限
        $scope.viewGroupPermission = function(index){
            $modal.open({
                templateUrl: 'group_permission.html',
                modal:true,
                size:"lg",
                resolve: {
                    Params: function () {
                        var params = {};
                        if($scope.groupList[index] && $scope.groupList[index].id){
                            params.id = $scope.groupList[index].id;
                            params.name = $scope.groupList[index].groupName;
                        }
                        return params;
                    }
                },
                controller: ['$scope', '$modalInstance', "$modal", "GroupAPI", "GroupPermissionAPI", "Params", function($scope, $modalInstance, $modal, GroupAPI, GroupPermissionAPI, Params){
                    $scope.queryParam = {};
                    // 查询可选资源
                    $scope.getNotHasResource = function(){
                        GroupAPI.getGroupPermission({
                            limit:10000,
                            offset:1,
                            keyword: $scope.queryParam.notHasResourceKeyword,
                            groupId: Params.id,
                            hasPermission: false
                        }, function(data){
                            $scope.notHasResourceList = data.data;
                        });
                    };
                    // 查询已有权限
                    $scope.getHasResource = function(){
                        GroupAPI.getGroupPermission({
                            limit:10000,
                            offset:1,
                            keyword: $scope.queryParam.hasResourceKeyword,
                            groupId: Params.id,
                            hasPermission: true
                        }, function(data){
                            $scope.hasResourceList = data.data;
                        });
                    };
                    // 选择待绑权限
                    $scope.choiceUnbind = function(index){
                        $scope.notHasResourceList[index].selected = !$scope.notHasResourceList[index].selected;
                    };
                    // 解绑权限
                    $scope.unbind = function(index){
                        var resource = $scope.hasResourceList[index];
                        GroupPermissionAPI.delete({
                            groupId: Params.id,
                            permissionIds:[resource.id]
                        }, function(data){
                            resource.selected = null;
                            resource.mouseenter = null;
                            $scope.hasResourceList.splice(index, 1);
                            $scope.notHasResourceList.push(resource);
                        });
                    };
                    $scope.unbindCheckAll = function(){
                        for (var i = 0; i < $scope.notHasResourceList.length; i++) {
                            var obj = $scope.notHasResourceList[i];
                            obj.selected = !obj.selected;
                        }
                    };
                    $scope.bindCheckAll = function(){
                        for (var i = 0; i < $scope.hasResourceList.length; i++) {
                            var obj = $scope.hasResourceList[i];
                            obj.selected = !obj.selected;
                        }
                    };
                    // 添加已选组权限
                    $scope.addChoiceBind = function(){
                        var ids = [];
                        var indexs = [];
                        for (var i = 0; i < $scope.notHasResourceList.length; i++) {
                            var obj = $scope.notHasResourceList[i];
                            if(obj.selected){
                                ids.push(obj.id);
                                indexs.push(i);
                            }
                        }
                        if(!ids || ids.length == 0){
                            $dialog.alert("请勾选需要添加的组权限资源");
                            return;
                        }
                        GroupAPI.createGroupPermission({
                            groupId: Params.id,
                            permissionIds:ids
                        }, function(data){
                            if(data && data.length > 0){
                                for (var i = 0; i < indexs.length; i++) {
                                    var obj1 = $scope.notHasResourceList[indexs[i] - i];
                                    $scope.notHasResourceList.splice(indexs[i] - i, 1);
                                    obj1.selected = false;
                                    obj1.mouseenter = false;
                                    $scope.hasResourceList.push(obj1);
                                }
                            }
                        });
                    };
                    // 删除已选已有权限
                    $scope.removeChoiceBind = function(){
                        var ids = [];
                        var indexs = [];
                        for (var i = 0; i < $scope.hasResourceList.length; i++) {
                            var obj = $scope.hasResourceList[i];
                            if(obj.selected){
                                ids.push(obj.id);
                                indexs.push(i);
                            }
                        }
                        if(!ids || ids.length == 0){
                            $dialog.alert("请勾选需要删除的组权限资源");
                            return;
                        }
                        GroupPermissionAPI.delete({
                            groupId: Params.id,
                            permissionIds: ids
                        }, function(data){
                            for (var i = 0; i < indexs.length; i++) {
                                var obj1 = $scope.hasResourceList[indexs[i] - i];
                                $scope.hasResourceList.splice(indexs[i] - i, 1);
                                obj1.selected = false;
                                obj1.mouseenter = false;
                                $scope.notHasResourceList.push(obj1);
                            }
                        });
                    };
                    $scope.choiceBind = function(index){
                        $scope.hasResourceList[index].selected = !$scope.hasResourceList[index].selected;
                    };
                    $scope.bind = function(index){
                        var resource = $scope.notHasResourceList[index];
                        GroupPermissionAPI.create({
                            groupId: Params.id,
                            permissionId:resource.id
                        }, function(){
                            resource.selected = null;
                            $scope.notHasResourceList.splice(index, 1);
                            $scope.hasResourceList.push(resource);
                        })
                    };
                    $scope.cancel = function(){
                        $modalInstance.dismiss();
                    };
                    var init = function(){
                        if(Params && Params.id){
                            $scope.getHasResource();
                            $scope.getNotHasResource();
                        }
                        if(Params){
                            $scope.name = Params.name;
                        }
                    };
                    init();
                }]
            }).result.then(function () {
                $scope.getGroupList();
            });
        };
        var init = function(){
            $scope.getGroupList();
        };
        init();
    }]
});