/**
 * Created by tanxinzheng on 16/7/3.
 */
define(function(){
    return ["$scope", "$modal", "UserAPI", "$dialog", function($scope, $modal, UserAPI, $dialog){
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
            $scope.getUserList();
        };
        $scope.queryParam = {};
        // 查询列表
        $scope.getUserList = function(){
            $scope.pageSetting.queryBtnLoading = true;
            UserAPI.query({
                keyword: $scope.queryParam.keyword,
                limit: $scope.pageInfoSetting.pageSize,
                offset: $scope.pageInfoSetting.pageNum
            }, function(data){
                $scope.userList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
            }).$promise.finally(function(){
                $scope.pageSetting.queryBtnLoading = false;
                });
        };
        // 全选
        $scope.checkAll = function(){
            if(!$scope.userList){
                return;
            }
            for (var i = 0; i < $scope.userList.length; i++) {
                $scope.userList[i].checked = $scope.pageSetting.checkAll;
            }
        };
        // 子集控制全选
        $scope.changeItemChecked = function(){
            if(!$scope.userList){
                return;
            }
            var num = 0;
            for (var i = 0; i < $scope.userList.length; i++) {
                if($scope.userList[i].checked){
                    num++;
                }
            }
            // 子集勾选数量等于集合总数则勾选全选，否则取消全选
            if(num == $scope.userList.length){
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
                templateUrl: 'user_detail.html',
                modal:true,
                resolve: {
                    Params: function () {
                        var params = {
                            action: action
                        };
                        if($scope.userList[index] && $scope.userList[index].id){
                            params.id = $scope.userList[index].id;
                        }
                        return params;
                    }
                },
                controller: ['$scope', '$modalInstance', "$modal", "UserAPI", "Params", "$dialog", function($scope, $modalInstance, $modal, UserAPI, Params, $dialog){
                    //$scope.user = null;
                    $scope.pageSetting = {
                        formDisabled : true,
                        saveBtnLoading : false,
                        editing:false
                    };
                    if(Params.action == "UPDATE" || Params.action == "ADD"){
                        $scope.pageSetting.formDisabled = false;
                        $scope.pageSetting.editing = false;
                    }
                    if(Params && Params.id){
                        $scope.user = UserAPI.get({
                            id: Params.id
                        });
                    }
                    $scope.userDetailForm = {};
                    $scope.saveUser = function(){
                        if($scope.userDetailForm.validator.form()){
                            if($scope.userDetailForm.validator.form()){
                                $dialog.confirm("是否保存数据？").then(function(){
                                    $scope.pageSetting.saveBtnLoading = true;
                                    if ( !$scope.user.id ) {
                                        UserAPI.create($scope.user, function(data,headers){
                                            $dialog.success("新增成功");
                                            $modalInstance.close();
                                        }).$promise.finally(function(){
                                            $scope.pageSetting.saveBtnLoading = false;
                                        });
                                    }else {
                                        UserAPI.update($scope.user, function(data,headers){
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
                $scope.getUserList();
            });
        };
        // 删除
        $scope.delete = function(index){
            $dialog.confirm("请确认是否删除").then(function(){
                UserAPI.delete({id:$scope.userList[index].id}, function(){
                    $scope.getUserList();
                });
            });
        };
        // 批量删除
        $scope.batchDelete = function(){
            var choiceItems = [];
            for (var i = 0; i < $scope.userList.length; i++) {
                var obj = $scope.userList[i];
                if(obj.checked){
                    choiceItems.push(obj.id);
                }
            }
            if(choiceItems && choiceItems.length > 0){
                $dialog.confirm("已勾选记录数：" + choiceItems.length + "，请确认是否删除已勾选数据").then(function(){
                    UserAPI.delete({ids:choiceItems}, function(){
                        $scope.getUserList();
                    });
                })
            }else{
                $dialog.alert("请勾选需要删除的数据");
            }
        };
        // 导出
        $scope.batchExport = function(){
            UserAPI.export({
                data:{keyword: $scope.queryParam.keyword}
            });
        };
        // 用户权限
        $scope.viewUserPermission = function(index){
            $modal.open({
                templateUrl: 'user_permission.html',
                modal:true,
                size:"lg",
                resolve: {
                    Params: function () {
                        var params = {};
                        if($scope.userList[index] && $scope.userList[index].id){
                            params.id = $scope.userList[index].id;
                            params.name = $scope.userList[index].nickname;
                        }
                        return params;
                    }
                },
                controller: ['$scope', '$modalInstance', "$modal", "UserAPI", "UserPermissionAPI", "Params", function($scope, $modalInstance, $modal, UserAPI, UserPermissionAPI, Params){
                    $scope.queryParam = {};
                    // 查询可选资源
                    $scope.getNotHasResource = function(){
                        UserAPI.getUserPermission({
                            limit:10000,
                            offset:1,
                            keyword: $scope.queryParam.notHasResourceKeyword,
                            userId: Params.id,
                            hasPermission: false
                        }, function(data){
                            $scope.notHasResourceList = data.data;
                        });
                    };
                    // 查询已有权限
                    $scope.getHasResource = function(){
                        UserAPI.getUserPermission({
                            limit:10000,
                            offset:1,
                            keyword: $scope.queryParam.hasResourceKeyword,
                            userId: Params.id,
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
                        UserPermissionAPI.delete({
                            userId: Params.id,
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
                        UserAPI.createUserPermission({
                            userId: Params.id,
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
                        UserPermissionAPI.delete({
                            userId: Params.id,
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
                        UserPermissionAPI.create({
                            userId: Params.id,
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
                $scope.getUserList();
            });
        };
        // 组权限
        $scope.viewUserGroup = function(index){
            $modal.open({
                templateUrl: 'user_group.html',
                modal:true,
                size:"lg",
                resolve: {
                    Params: function () {
                        var params = {};
                        if($scope.userList[index] && $scope.userList[index].id){
                            params.id = $scope.userList[index].id;
                            params.name = $scope.userList[index].nickname;
                        }
                        return params;
                    }
                },
                controller: ['$scope', '$modalInstance', "$modal", "UserAPI", "UserGroupAPI", "Params", function($scope, $modalInstance, $modal, UserAPI, UserGroupAPI, Params){
                    $scope.queryParam = {};
                    // 查询可选资源
                    $scope.getNotHasResource = function(){
                        UserAPI.getUserGroup({
                            limit:10000,
                            offset:1,
                            keyword: $scope.queryParam.notHasResourceKeyword,
                            userId: Params.id,
                            hasGroup: false
                        }, function(data){
                            $scope.notHasResourceList = data.data;
                        });
                    };
                    // 查询已有权限
                    $scope.getHasResource = function(){
                        UserAPI.getUserGroup({
                            limit:10000,
                            offset:1,
                            keyword: $scope.queryParam.hasResourceKeyword,
                            userId: Params.id,
                            hasGroup: true
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
                        UserGroupAPI.delete({
                            userId: Params.id,
                            groupIds:[resource.id]
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
                        UserAPI.createUserGroup({
                            userId: Params.id,
                            groupIds:ids
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
                        UserGroupAPI.delete({
                            userId: Params.id,
                            groupIds: ids
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
                        UserGroupAPI.create({
                            userId: Params.id,
                            groupId:resource.id
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
                $scope.getUserList();
            });
        };
        var init = function(){
            $scope.getUserList();
        };
        init();
    }]
});