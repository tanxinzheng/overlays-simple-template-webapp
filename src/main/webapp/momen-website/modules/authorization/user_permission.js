/**
 * Created by tanxinzheng on 16/7/3.
 */
define(function(){
    return ["$scope", "$modal", "UserPermissionAPI", "$dialog", function($scope, $modal, UserPermissionAPI, $dialog){
        $scope.pageSetting = {
            checkAll : false
        };
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        // 查询列表
        $scope.getUserPermissionList = function(){
            UserPermissionAPI.query({
                keyword: $scope.queryParam.keyword,
                limit: $scope.pageInfoSetting.pageSize,
                offset: $scope.pageInfoSetting.pageNum
            }, function(data){
                $scope.userPermissionList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getUserPermissionList;
            });
        };
        // 全选
        $scope.checkAll = function(a){
            if(!$scope.userPermissionList){
                return;
            }
            var num = 0;
            for (var i = 0; i < $scope.userPermissionList.length; i++) {
                if($scope.userPermissionList[i].checked){
                    num++;
                }
            }
            if($scope.userPermissionList && $scope.userPermissionList.length > 0 && num == $scope.userPermissionList.length){
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
                templateUrl: 'userPermission_detail.html',
                modal:true,
                resolve: {
                    Params: function () {
                        var params = {
                            action: action
                        };
                        if($scope.userPermissionList[index] && $scope.userPermissionList[index].id){
                            params.id = $scope.userPermissionList[index].id;
                        }
                        return params;
                    }
                },
                controller: ['$scope', '$modalInstance', "$modal", "UserPermissionAPI", "Params", function($scope, $modalInstance, $modal, UserPermissionAPI, Params){
                    //$scope.userPermission = null;
                    $scope.pageSetting = {
                        formDisabled : true
                    };
                    if(Params.action == "UPDATE" || Params.action == "ADD"){
                        $scope.pageSetting.formDisabled = false;
                    }
                    if(Params && Params.id){
                        $scope.userPermission = UserPermissionAPI.get({
                            id: Params.id
                        });
                    }else{
                        $scope.userPermission = new UserPermissionAPI();
                    }
                    $scope.userPermissionDetailForm = {};
                    $scope.saveUserPermission = function(){
                        if($scope.userPermissionDetailForm.validator.form()){
                            $scope.userPermission.$save(function(){
                                $modalInstance.close();
                            });
                        }
                    };
                    $scope.cancel = function(){
                        $modalInstance.dismiss();
                    };
                }]
            }).result.then(function () {
                $scope.getUserPermissionList();
            }, function () {
                $scope.getUserPermissionList();
            });
        };
        $scope.delete = function(index){
            UserPermissionAPI.delete({id:$scope.userPermissionList[index].id}, function(){
                $scope.getUserPermissionList();
            });
        };
        var init = function(){
            $scope.getUserPermissionList();
        };
        init();
    }]
});