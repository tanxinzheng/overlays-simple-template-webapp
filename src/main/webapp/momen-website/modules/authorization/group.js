/**
 * Created by tanxinzheng on 16/7/3.
 */
define(function(){
    return ["$scope", "$modal", "GroupAPI", "$dialog", function($scope, $modal, GroupAPI, $dialog){
        $scope.pageSetting = {
            checkAll : false
        };
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        // 查询列表
        $scope.getGroupList = function(){
            GroupAPI.query({
                keyword: $scope.queryParam.keyword,
                limit: $scope.pageInfoSetting.pageSize,
                offset: $scope.pageInfoSetting.pageNum
            }, function(data){
                $scope.groupList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getGroupList;
            });
        };
        // 全选
        $scope.checkAll = function(a){
            if(!$scope.groupList){
                return;
            }
            var num = 0;
            for (var i = 0; i < $scope.groupList.length; i++) {
                if($scope.groupList[i].checked){
                    num++;
                }
            }
            if($scope.groupList && $scope.groupList.length > 0 && num == $scope.groupList.length){
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
                controller: ['$scope', '$modalInstance', "$modal", "GroupAPI", "Params", function($scope, $modalInstance, $modal, GroupAPI, Params){
                    //$scope.group = null;
                    $scope.pageSetting = {
                        formDisabled : true
                    };
                    if(Params.action == "UPDATE" || Params.action == "ADD"){
                        $scope.pageSetting.formDisabled = false;
                    }
                    if(Params && Params.id){
                        $scope.group = GroupAPI.get({
                            id: Params.id
                        });
                    }else{
                        $scope.group = new GroupAPI();
                    }
                    $scope.groupDetailForm = {};
                    $scope.saveGroup = function(){
                        if($scope.groupDetailForm.validator.form()){
                            $scope.group.$save(function(){
                                $modalInstance.close();
                            });
                        }
                    };
                    $scope.cancel = function(){
                        $modalInstance.dismiss();
                    };
                }]
            }).result.then(function () {
                $scope.getGroupList();
            }, function () {
                $scope.getGroupList();
            });
        };
        $scope.delete = function(index){
            GroupAPI.delete({id:$scope.groupList[index].id}, function(){
                $scope.getGroupList();
            });
        };
        var init = function(){
            $scope.getGroupList();
        };
        init();
    }]
});