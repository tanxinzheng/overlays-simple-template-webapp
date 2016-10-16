/**
 * Created by tanxinzheng on 16/7/3.
 */
define(function(){
    return ["$scope", "$modal", "GroupPermissionAPI", "$dialog", function($scope, $modal, GroupPermissionAPI, $dialog){
        $scope.pageSetting = {
            checkAll : false
        };
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        // 查询列表
        $scope.getGroupPermissionList = function(){
            GroupPermissionAPI.query({
                keyword: $scope.queryParam.keyword,
                limit: $scope.pageInfoSetting.pageSize,
                offset: $scope.pageInfoSetting.pageNum
            }, function(data){
                $scope.groupPermissionList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getGroupPermissionList;
            });
        };
        // 全选
        $scope.checkAll = function(a){
            if(!$scope.groupPermissionList){
                return;
            }
            var num = 0;
            for (var i = 0; i < $scope.groupPermissionList.length; i++) {
                if($scope.groupPermissionList[i].checked){
                    num++;
                }
            }
            if($scope.groupPermissionList && $scope.groupPermissionList.length > 0 && num == $scope.groupPermissionList.length){
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
                controller: ['$scope', '$modalInstance', "$modal", "GroupPermissionAPI", "Params", function($scope, $modalInstance, $modal, GroupPermissionAPI, Params){
                    //$scope.groupPermission = null;
                    $scope.pageSetting = {
                        formDisabled : true
                    };
                    if(Params.action == "UPDATE" || Params.action == "ADD"){
                        $scope.pageSetting.formDisabled = false;
                    }
                    if(Params && Params.id){
                        $scope.groupPermission = GroupPermissionAPI.get({
                            id: Params.id
                        });
                    }else{
                        $scope.groupPermission = new GroupPermissionAPI();
                    }
                    $scope.groupPermissionDetailForm = {};
                    $scope.saveGroupPermission = function(){
                        if($scope.groupPermissionDetailForm.validator.form()){
                            $scope.groupPermission.$save(function(){
                                $modalInstance.close();
                            });
                        }
                    };
                    $scope.cancel = function(){
                        $modalInstance.dismiss();
                    };
                }]
            }).result.then(function () {
                $scope.getGroupPermissionList();
            }, function () {
                $scope.getGroupPermissionList();
            });
        };
        $scope.delete = function(index){
            GroupPermissionAPI.delete({id:$scope.groupPermissionList[index].id}, function(){
                $scope.getGroupPermissionList();
            });
        };
        var init = function(){
            $scope.getGroupPermissionList();
        };
        init();
    }]
});