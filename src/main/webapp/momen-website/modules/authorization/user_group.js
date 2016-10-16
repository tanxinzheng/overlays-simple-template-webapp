/**
 * Created by tanxinzheng on 16/7/3.
 */
define(function(){
    return ["$scope", "$modal", "UserGroupAPI", "$dialog", function($scope, $modal, UserGroupAPI, $dialog){
        $scope.pageSetting = {
            checkAll : false
        };
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        // 查询列表
        $scope.getUserGroupList = function(){
            UserGroupAPI.query({
                keyword: $scope.queryParam.keyword,
                limit: $scope.pageInfoSetting.pageSize,
                offset: $scope.pageInfoSetting.pageNum
            }, function(data){
                $scope.userGroupList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getUserGroupList;
            });
        };
        // 全选
        $scope.checkAll = function(){
            if(!$scope.userGroupList){
                return;
            }
            for (var i = 0; i < $scope.userGroupList.length; i++) {
                $scope.userGroupList[i].checked = $scope.pageSetting.checkAll;
            }
        };
        // 子集控制全选
        $scope.changeItemChecked = function(){
            if(!$scope.userGroupList){
                return;
            }
            var num = 0;
            for (var i = 0; i < $scope.userGroupList.length; i++) {
                if($scope.userGroupList[i].checked){
                    num++;
                }
            }
            // 子集勾选数量等于集合总数则勾选全选，否则取消全选
            if(num == $scope.userGroupList.length){
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
                templateUrl: 'userGroup_detail.html',
                modal:true,
                resolve: {
                    Params: function () {
                        var params = {
                            action: action
                        };
                        if($scope.userGroupList[index] && $scope.userGroupList[index].id){
                            params.id = $scope.userGroupList[index].id;
                        }
                        return params;
                    }
                },
                controller: ['$scope', '$modalInstance', "$modal", "UserGroupAPI", "Params", function($scope, $modalInstance, $modal, UserGroupAPI, Params){
                    //$scope.userGroup = null;
                    $scope.pageSetting = {
                        formDisabled : true
                    };
                    if(Params.action == "UPDATE" || Params.action == "ADD"){
                        $scope.pageSetting.formDisabled = false;
                    }
                    if(Params && Params.id){
                        $scope.userGroup = UserGroupAPI.get({
                            id: Params.id
                        });
                    }else{
                        $scope.userGroup = new UserGroupAPI();
                    }
                    $scope.userGroupDetailForm = {};
                    $scope.saveUserGroup = function(){
                        if($scope.userGroupDetailForm.validator.form()){
                            $scope.userGroup.$save(function(){
                                $modalInstance.close();
                            });
                        }
                    };
                    $scope.cancel = function(){
                        $modalInstance.dismiss();
                    };
                }]
            }).result.then(function () {
                $scope.getUserGroupList();
            }, function () {
                $scope.getUserGroupList();
            });
        };
        // 删除
        $scope.delete = function(index){
            UserGroupAPI.delete({id:$scope.userGroupList[index].id}, function(){
                $scope.getUserGroupList();
            });
        };
        // 导出
        $scope.batchExport = function(){
            UserGroupAPI.export({
                data:{keyword: $scope.queryParam.keyword}
            });
        };
        var init = function(){
            $scope.getUserGroupList();
        };
        init();
    }]
});