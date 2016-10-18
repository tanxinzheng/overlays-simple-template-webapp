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
        $scope.checkAll = function(){
            if(!$scope.userPermissionList){
                return;
            }
            for (var i = 0; i < $scope.userPermissionList.length; i++) {
                $scope.userPermissionList[i].checked = $scope.pageSetting.checkAll;
            }
        };
        // 子集控制全选
        $scope.changeItemChecked = function(){
            if(!$scope.userPermissionList){
                return;
            }
            var num = 0;
            for (var i = 0; i < $scope.userPermissionList.length; i++) {
                if($scope.userPermissionList[i].checked){
                    num++;
                }
            }
            // 子集勾选数量等于集合总数则勾选全选，否则取消全选
            if(num == $scope.userPermissionList.length){
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
        // 删除
        $scope.delete = function(index){
            UserPermissionAPI.delete({id:$scope.userPermissionList[index].id}, function(){
                $scope.getUserPermissionList();
            });
        };
        // 批量删除
        $scope.batchDelete = function(){
            var choiceItems = [];
            for (var i = 0; i < $scope.userPermissionList.length; i++) {
                var obj = $scope.userPermissionList[i];
                if(obj.checked){
                    choiceItems.push(obj.id);
                }
            }
            if(choiceItems && choiceItems.length > 0){
                $dialog.confirm("已勾选记录数：" + choiceItems.length + "，请确认是否删除已勾选数据").then(function(){
                    UserPermissionAPI.delete({ids:choiceItems}, function(){
                        $scope.getUserPermissionList();
                    });
                })
            }else{
                $dialog.alert("请勾选需要删除的数据");
            }
        };
        // 导出
        $scope.batchExport = function(){
            UserPermissionAPI.export({
                data:{keyword: $scope.queryParam.keyword}
            });
        };
        var init = function(){
            $scope.getUserPermissionList();
        };
        init();
    }]
});