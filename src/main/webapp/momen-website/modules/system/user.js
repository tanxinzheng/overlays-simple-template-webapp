/**
 * Created by tanxinzheng on 16/7/3.
 */
define(function(){
    return ["$scope", "$modal", "UserAPI", "$dialog", function($scope, $modal, UserAPI, $dialog){
        $scope.pageSetting = {
            checkAll : false
        };
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        // 查询列表
        $scope.getUserList = function(){
            UserAPI.query({
                keyword: $scope.queryParam.keyword,
                limit: 10,
                offset: 1
            }, function(data){
                $scope.userList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getUserList;
            });
        };
        // 全选
        $scope.checkAll = function(){
            if(!$scope.userList){
                return;
            }
            var num = 0;
            for (var i = 0; i < $scope.userList.length; i++) {
                if($scope.userList[i].checked){
                    num++;
                }
            }
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
            var modalInstance = $modal.open({
                templateUrl: 'userDetail.html',
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
                controller: ['$scope', '$modalInstance', "$modal", "UserAPI", "Params", function($scope, $modalInstance, $modal, UserAPI, Params){
                    //$scope.user = null;
                    $scope.pageSetting = {
                        formDisabled : true
                    };
                    if(Params.action == "UPDATE" || Params.action == "ADD"){
                        $scope.pageSetting.formDisabled = false;
                    }
                    if(Params && Params.id){
                        $scope.user = UserAPI.get({
                            id: Params.id
                        });
                    }else{
                        $scope.user = new UserAPI();
                    }
                    $scope.userDetailForm = {};
                    $scope.saveUser = function(){
                        if($scope.userDetailForm.validator.form()){
                            $scope.user.$save(function(){
                                $modalInstance.close();
                            });
                        }
                    };
                    $scope.cancel = function(){
                        $modalInstance.dismiss();
                    };
                }]
            }).result.then(function () {
                $scope.getUserList();
            }, function () {
                $scope.getUserList();
            });
        };
        $scope.delete = function(index){
            UserAPI.delete({id:$scope.userList[index].id}, function(){
                $scope.getUserList();
            });
        };
        var init = function(){
            $scope.getUserList();
        };
        init();
    }]
});