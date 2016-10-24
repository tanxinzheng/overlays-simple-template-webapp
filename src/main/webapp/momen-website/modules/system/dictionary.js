/**
 * Created by tanxinzheng on 16/7/3.
 */
define(function(){
    return ["$scope", "$modal", "DictionaryAPI", "$dialog", function($scope, $modal, DictionaryAPI, $dialog){
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
            $scope.getDictionaryList();
        };
        $scope.queryParam = {};
        // 查询列表
        $scope.getDictionaryList = function(){
            $scope.pageSetting.queryBtnLoading = true;
            DictionaryAPI.query({
                keyword: $scope.queryParam.keyword,
                limit: $scope.pageInfoSetting.pageSize,
                offset: $scope.pageInfoSetting.pageNum
            }, function(data){
                $scope.dictionaryList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
            }).$promise.finally(function(){
                $scope.pageSetting.queryBtnLoading = false;
                });
        };
        // 全选
        $scope.checkAll = function(){
            if(!$scope.dictionaryList){
                return;
            }
            for (var i = 0; i < $scope.dictionaryList.length; i++) {
                $scope.dictionaryList[i].checked = $scope.pageSetting.checkAll;
            }
        };
        // 子集控制全选
        $scope.changeItemChecked = function(){
            if(!$scope.dictionaryList){
                return;
            }
            var num = 0;
            for (var i = 0; i < $scope.dictionaryList.length; i++) {
                if($scope.dictionaryList[i].checked){
                    num++;
                }
            }
            // 子集勾选数量等于集合总数则勾选全选，否则取消全选
            if(num == $scope.dictionaryList.length){
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
                templateUrl: 'dictionary_detail.html',
                modal:true,
                resolve: {
                    Params: function () {
                        var params = {
                            action: action
                        };
                        if($scope.dictionaryList[index] && $scope.dictionaryList[index].id){
                            params.id = $scope.dictionaryList[index].id;
                        }
                        return params;
                    }
                },
                controller: ['$scope', '$modalInstance', "$modal", "DictionaryAPI", "Params", "$dialog", function($scope, $modalInstance, $modal, DictionaryAPI, Params, $dialog){
                    //$scope.dictionary = null;
                    $scope.pageSetting = {
                        formDisabled : true,
                        saveBtnLoading : false
                    };
                    if(Params.action == "UPDATE" || Params.action == "ADD"){
                        $scope.pageSetting.formDisabled = false;
                    }
                    if(Params && Params.id){
                        $scope.dictionary = DictionaryAPI.get({
                            id: Params.id
                        });
                    }
                    $scope.dictionaryDetailForm = {};
                    $scope.saveDictionary = function(){
                        if($scope.dictionaryDetailForm.validator.form()){
                            if($scope.dictionaryDetailForm.validator.form()){
                                $dialog.confirm("是否保存数据？").then(function(){
                                    $scope.pageSetting.saveBtnLoading = true;
                                    if ( !$scope.dictionary.id ) {
                                        DictionaryAPI.create($scope.dictionary, function(data,headers){
                                            $dialog.success("新增成功");
                                            $modalInstance.close();
                                        }).$promise.finally(function(){
                                            $scope.pageSetting.saveBtnLoading = false;
                                        });
                                    }else {
                                        DictionaryAPI.update($scope.dictionary, function(data,headers){
                                            $dialog.success("更新成功");
                                            $modalInstance.close();
                                        }).$promise.finally(function(){
                                            $scope.pageSetting.saveBtnLoading = false;
                                        });
                                    }
                                });
                            }
                        }
                    };
                    $scope.cancel = function(){
                        $modalInstance.dismiss();
                    };
                }]
            }).result.then(function () {
                $scope.getDictionaryList();
            });
        };
        // 删除
        $scope.delete = function(index){
            $dialog.confirm("请确认是否删除").then(function(){
                DictionaryAPI.delete({id:$scope.dictionaryList[index].id}, function(){
                    $scope.getDictionaryList();
                });
            });
        };
        // 批量删除
        $scope.batchDelete = function(){
            var choiceItems = [];
            for (var i = 0; i < $scope.dictionaryList.length; i++) {
                var obj = $scope.dictionaryList[i];
                if(obj.checked){
                    choiceItems.push(obj.id);
                }
            }
            if(choiceItems && choiceItems.length > 0){
                $dialog.confirm("已勾选记录数：" + choiceItems.length + "，请确认是否删除已勾选数据").then(function(){
                    DictionaryAPI.delete({ids:choiceItems}, function(){
                        $scope.getDictionaryList();
                    });
                })
            }else{
                $dialog.alert("请勾选需要删除的数据");
            }
        };
        // 导出
        $scope.batchExport = function(){
            DictionaryAPI.export({
                data:{keyword: $scope.queryParam.keyword}
            });
        };
        var init = function(){
            $scope.getDictionaryList();
        };
        init();
    }]
});