/**
 * Created by tanxinzheng on 16/7/3.
 */
define(function(){
    return ["$scope", "$modal", "DictionaryGroupAPI", "$dialog", function($scope, $modal, DictionaryGroupAPI, $dialog){
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
            $scope.getDictionaryGroupList();
        };
        $scope.queryParam = {};
        // 查询列表
        $scope.getDictionaryGroupList = function(){
            $scope.pageSetting.queryBtnLoading = true;
            DictionaryGroupAPI.query({
                keyword: $scope.queryParam.keyword,
                limit: $scope.pageInfoSetting.pageSize,
                offset: $scope.pageInfoSetting.pageNum
            }, function(data){
                $scope.dictionaryGroupList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
            }).$promise.finally(function(){
                $scope.pageSetting.queryBtnLoading = false;
                });
        };
        // 全选
        $scope.checkAll = function(){
            if(!$scope.dictionaryGroupList){
                return;
            }
            for (var i = 0; i < $scope.dictionaryGroupList.length; i++) {
                $scope.dictionaryGroupList[i].checked = $scope.pageSetting.checkAll;
            }
        };
        // 子集控制全选
        $scope.changeItemChecked = function(){
            if(!$scope.dictionaryGroupList){
                return;
            }
            var num = 0;
            for (var i = 0; i < $scope.dictionaryGroupList.length; i++) {
                if($scope.dictionaryGroupList[i].checked){
                    num++;
                }
            }
            // 子集勾选数量等于集合总数则勾选全选，否则取消全选
            if(num == $scope.dictionaryGroupList.length){
                $scope.pageSetting.checkAll = true;
            }else{
                $scope.pageSetting.checkAll = false;
            }
        };
        // 新增
        $scope.add = function(index){
            $scope.openModal("ADD", index);
        };
        // 查看
        $scope.view = function(index){
            $scope.openModal("VIEW", index);
        };
        // 修改
        $scope.update = function(index){
            $scope.openModal("UPDATE", index);
        };
        // 弹出
        $scope.openModal = function(action, index){
            $modal.open({
                templateUrl: 'dictionaryGroup_detail.html',
                modal:true,
                resolve: {
                    Params: function () {
                        var params = {
                            action: action
                        };
                        if(index >= 0 && $scope.dictionaryGroupList[index] && $scope.dictionaryGroupList[index].id){
                            params.id = $scope.dictionaryGroupList[index].id;
                        }
                        return params;
                    }
                },
                controller: ['$scope', '$modalInstance', "$modal", "DictionaryGroupAPI", "Params", "$dialog", function($scope, $modalInstance, $modal, DictionaryGroupAPI, Params, $dialog){
                    //$scope.dictionaryGroup = null;
                    $scope.pageSetting = {
                        formDisabled : true,
                        saveBtnLoading : false
                    };
                    if(Params.action == "UPDATE" || Params.action == "ADD"){
                        $scope.pageSetting.formDisabled = false;
                    }
                    if(Params && Params.id){
                        $scope.dictionaryGroup = DictionaryGroupAPI.get({
                            id: Params.id
                        });
                    }
                    $scope.dictionaryGroupDetailForm = {};
                    $scope.saveDictionaryGroup = function(){
                        if($scope.dictionaryGroupDetailForm.validator.form()){
                            $dialog.confirm("是否保存数据？").then(function(){
                                $scope.pageSetting.saveBtnLoading = true;
                                if ( !$scope.dictionaryGroup.id ) {
                                    DictionaryGroupAPI.create($scope.dictionaryGroup, function(data,headers){
                                        $dialog.success("新增成功");
                                        $modalInstance.close();
                                    }).$promise.finally(function(){
                                        $scope.pageSetting.saveBtnLoading = false;
                                    });
                                }else {
                                    DictionaryGroupAPI.update($scope.dictionaryGroup, function(data,headers){
                                        $dialog.success("更新成功");
                                        $modalInstance.close();
                                    }).$promise.finally(function(){
                                        $scope.pageSetting.saveBtnLoading = false;
                                    });
                                }
                            });
                        }
                    };
                    $scope.cancel = function(){
                        $modalInstance.dismiss();
                    };
                }]
            }).result.then(function () {
                $scope.getDictionaryGroupList();
            });
        };
        // 删除
        $scope.delete = function(index){
            $dialog.confirm("请确认是否删除").then(function(){
                DictionaryGroupAPI.delete({id:$scope.dictionaryGroupList[index].id}, function(){
                    $scope.getDictionaryGroupList();
                });
            });
        };
        // 批量删除
        $scope.batchDelete = function(){
            var choiceItems = [];
            for (var i = 0; i < $scope.dictionaryGroupList.length; i++) {
                var obj = $scope.dictionaryGroupList[i];
                if(obj.checked){
                    choiceItems.push(obj.id);
                }
            }
            if(choiceItems && choiceItems.length > 0){
                $dialog.confirm("已勾选记录数：" + choiceItems.length + "，请确认是否删除已勾选数据").then(function(){
                    DictionaryGroupAPI.delete({ids:choiceItems}, function(){
                        $scope.getDictionaryGroupList();
                    });
                })
            }else{
                $dialog.alert("请勾选需要删除的数据");
            }
        };
        // 导出
        $scope.batchExport = function(){
            DictionaryGroupAPI.export({
                data:{keyword: $scope.queryParam.keyword}
            });
        };
        var init = function(){
            $scope.getDictionaryGroupList();
        };
        init();
    }]
});