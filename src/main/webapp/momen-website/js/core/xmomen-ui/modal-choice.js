/**
 * Created by tanxinzheng on 16/7/3.
 */
define(function(){
    return angular.module("xmomen.modal-choice",[

    ]).directive('modalChoice', ['$modal', function($modal) {
        return {
            restrict: 'E',
            replace: true ,
            transclude : true, //嵌入
            scope:{

            },
            templateUrl : 'js/core/xmomen-ui/template/modal-choice-tpl.html',//模板url
            controller : ['$scope', '$modal',"UserAPI", function($scope, $modal, UserAPI){
                //$scope.modalChoiceOption = {
                //    open: $scope.open,
                //    getData: UserAPI.getUserPermission,
                //    bind:bind,
                //    unbind:unbind,
                //    create:create,
                //};
                //$scope.open = function(){
                //    // 组权限
                //    $scope.groupPermission = function(index){
                //        $modal.open({
                //            templateUrl: 'group_permission.html',
                //            modal:true,
                //            size:"lg",
                //            resolve: {
                //                Params: function () {
                //                    var params = {};
                //                    if($scope.groupList[index] && $scope.groupList[index].id){
                //                        params.id = $scope.groupList[index].id;
                //                        params.name = $scope.groupList[index].groupName;
                //                    }
                //                    return params;
                //                }
                //            },
                //            controller: ['$scope', '$modalInstance', "$modal", "GroupAPI", "GroupPermissionAPI", "Params", function($scope, $modalInstance, $modal, GroupAPI, GroupPermissionAPI, Params){
                //                $scope.queryParam = {};
                //                // 查询可选权限
                //                $scope.getNotHasGroupPermission = function(){
                //                    GroupAPI.getGroupPermission({
                //                        limit:10000,
                //                        offset:1,
                //                        keyword: $scope.queryParam.notHasPermissionKeyword,
                //                        groupId: Params.id,
                //                        hasPermission: false
                //                    }, function(data){
                //                        $scope.groupNotHasPermissionList = data.data;
                //                    });
                //                };
                //                // 查询已有权限
                //                $scope.getHasGroupPermission = function(){
                //                    GroupAPI.getGroupPermission({
                //                        limit:10000,
                //                        offset:1,
                //                        keyword: $scope.queryParam.hasPermissionKeyword,
                //                        groupId: Params.id,
                //                        hasPermission: true
                //                    }, function(data){
                //                        $scope.groupHasPermissionList = data.data;
                //                    });
                //                };
                //                // 选择待绑权限
                //                $scope.choiceUnbind = function(index){
                //                    $scope.groupNotHasPermissionList[index].selected = !$scope.groupNotHasPermissionList[index].selected;
                //                };
                //                // 解绑权限
                //                $scope.unbind = function(index){
                //                    var permission = $scope.groupHasPermissionList[index];
                //                    GroupPermissionAPI.delete({
                //                        groupId: Params.id,
                //                        permissionIds:[permission.id]
                //                    }, function(data){
                //                        permission.selected = null;
                //                        permission.mouseenter = null;
                //                        $scope.groupHasPermissionList.splice(index, 1);
                //                        $scope.groupNotHasPermissionList.push(permission);
                //                    });
                //                };
                //                $scope.unbindCheckAll = function(){
                //                    for (var i = 0; i < $scope.groupNotHasPermissionList.length; i++) {
                //                        var obj = $scope.groupNotHasPermissionList[i];
                //                        obj.selected = !obj.selected;
                //                    }
                //                };
                //                $scope.bindCheckAll = function(){
                //                    for (var i = 0; i < $scope.groupHasPermissionList.length; i++) {
                //                        var obj = $scope.groupHasPermissionList[i];
                //                        obj.selected = !obj.selected;
                //                    }
                //                };
                //                // 添加已选组权限
                //                $scope.addChoiceBind = function(){
                //                    var ids = [];
                //                    var indexs = [];
                //                    for (var i = 0; i < $scope.groupNotHasPermissionList.length; i++) {
                //                        var obj = $scope.groupNotHasPermissionList[i];
                //                        if(obj.selected){
                //                            ids.push(obj.id);
                //                            indexs.push(i);
                //                        }
                //                    }
                //                    if(!ids || ids.length == 0){
                //                        $dialog.alert("请勾选需要添加的组权限资源");
                //                        return;
                //                    }
                //                    GroupAPI.createGroupPermission({
                //                        groupId: Params.id,
                //                        permissionIds:ids
                //                    }, function(data){
                //                        if(data && data.length > 0){
                //                            for (var i = 0; i < indexs.length; i++) {
                //                                var obj1 = $scope.groupNotHasPermissionList[indexs[i] - i];
                //                                $scope.groupNotHasPermissionList.splice(indexs[i] - i, 1);
                //                                obj1.selected = false;
                //                                obj1.mouseenter = false;
                //                                $scope.groupHasPermissionList.push(obj1);
                //                            }
                //                        }
                //                    });
                //                };
                //                // 删除已选已有权限
                //                $scope.removeChoiceBind = function(){
                //                    var ids = [];
                //                    var indexs = [];
                //                    for (var i = 0; i < $scope.groupHasPermissionList.length; i++) {
                //                        var obj = $scope.groupHasPermissionList[i];
                //                        if(obj.selected){
                //                            ids.push(obj.id);
                //                            indexs.push(i);
                //                        }
                //                    }
                //                    if(!ids || ids.length == 0){
                //                        $dialog.alert("请勾选需要删除的组权限资源");
                //                        return;
                //                    }
                //                    GroupPermissionAPI.delete({
                //                        groupId: Params.id,
                //                        permissionIds: ids
                //                    }, function(data){
                //                        for (var i = 0; i < indexs.length; i++) {
                //                            var obj1 = $scope.groupHasPermissionList[indexs[i] - i];
                //                            $scope.groupHasPermissionList.splice(indexs[i] - i, 1);
                //                            obj1.selected = false;
                //                            obj1.mouseenter = false;
                //                            $scope.groupNotHasPermissionList.push(obj1);
                //                        }
                //                    });
                //                };
                //                $scope.choiceBind = function(index){
                //                    $scope.groupHasPermissionList[index].selected = !$scope.groupHasPermissionList[index].selected;
                //                };
                //                $scope.bind = function(index){
                //                    var permission = $scope.groupNotHasPermissionList[index];
                //                    GroupPermissionAPI.create({
                //                        groupId: Params.id,
                //                        permissionId:permission.id
                //                    }, function(){
                //                        permission.selected = null;
                //                        $scope.groupNotHasPermissionList.splice(index, 1);
                //                        $scope.groupHasPermissionList.push(permission);
                //                    })
                //                };
                //                $scope.cancel = function(){
                //                    $modalInstance.dismiss();
                //                };
                //                var init = function(){
                //                    if(Params && Params.id){
                //                        $scope.getHasGroupPermission();
                //                        $scope.getNotHasGroupPermission();
                //                    }
                //                    if(Params){
                //                        $scope.name = Params.name;
                //                    }
                //                };
                //                init();
                //            }]
                //        }).result.then(function () {
                //            $scope.getGroupList();
                //        });
                //    };
                //}
            }]
        }
    }]);
});