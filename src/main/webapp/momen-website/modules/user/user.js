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
                limit: $scope.pageInfoSetting.pageSize,
                offset: $scope.pageInfoSetting.pageNum
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
        // 删除
        $scope.delete = function(index){
            UserAPI.delete({id:$scope.userList[index].id}, function(){
                $scope.getUserList();
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
        var init = function(){
            $scope.getUserList();
        };
        init();
    }]
});