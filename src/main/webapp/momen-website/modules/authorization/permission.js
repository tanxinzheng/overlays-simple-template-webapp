/**
 * Created by tanxinzheng on 16/7/3.
 */
define(function(){
    return ["$scope", "$modal", "PermissionAPI", "$dialog", function($scope, $modal, PermissionAPI, $dialog){
        $scope.pageSetting = {
            checkAll : false
        };
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        // 查询列表
        $scope.getPermissionList = function(){
            PermissionAPI.query({
                keyword: $scope.queryParam.keyword,
                limit: $scope.pageInfoSetting.pageSize,
                offset: $scope.pageInfoSetting.pageNum
            }, function(data){
                $scope.permissionList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getPermissionList;
            });
        };
        // 全选
        $scope.checkAll = function(a){
            if(!$scope.permissionList){
                return;
            }
            var num = 0;
            for (var i = 0; i < $scope.permissionList.length; i++) {
                if($scope.permissionList[i].checked){
                    num++;
                }
            }
            if($scope.permissionList && $scope.permissionList.length > 0 && num == $scope.permissionList.length){
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
                templateUrl: 'permission_detail.html',
                modal:true,
                resolve: {
                    Params: function () {
                        var params = {
                            action: action
                        };
                        if($scope.permissionList[index] && $scope.permissionList[index].id){
                            params.id = $scope.permissionList[index].id;
                        }
                        return params;
                    }
                },
                controller: ['$scope', '$modalInstance', "$modal", "PermissionAPI", "Params", function($scope, $modalInstance, $modal, PermissionAPI, Params){
                    //$scope.permission = null;
                    $scope.pageSetting = {
                        formDisabled : true
                    };
                    if(Params.action == "UPDATE" || Params.action == "ADD"){
                        $scope.pageSetting.formDisabled = false;
                    }
                    if(Params && Params.id){
                        $scope.permission = PermissionAPI.get({
                            id: Params.id
                        });
                    }else{
                        $scope.permission = new PermissionAPI();
                    }
                    $scope.permissionDetailForm = {};
                    $scope.savePermission = function(){
                        if($scope.permissionDetailForm.validator.form()){
                            $scope.permission.$save(function(){
                                $modalInstance.close();
                            });
                        }
                    };
                    $scope.cancel = function(){
                        $modalInstance.dismiss();
                    };
                }]
            }).result.then(function () {
                $scope.getPermissionList();
            }, function () {
                $scope.getPermissionList();
            });
        };
        $scope.delete = function(index){
            PermissionAPI.delete({id:$scope.permissionList[index].id}, function(){
                $scope.getPermissionList();
            });
        };
        var init = function(){
            $scope.getPermissionList();
        };
        init();
    }]
});