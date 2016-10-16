/**
 * Created by tanxinzheng on 16/7/3.
 */
define(function(){
    return ["$scope", "$modal", "DictionaryAPI", "$dialog", function($scope, $modal, DictionaryAPI, $dialog){
        $scope.pageSetting = {
            checkAll : false
        };
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        // 查询列表
        $scope.getDictionaryList = function(){
            DictionaryAPI.query({
                keyword: $scope.queryParam.keyword,
                limit: $scope.pageInfoSetting.pageSize,
                offset: $scope.pageInfoSetting.pageNum
            }, function(data){
                $scope.dictionaryList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getDictionaryList;
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
                controller: ['$scope', '$modalInstance', "$modal", "DictionaryAPI", "Params", function($scope, $modalInstance, $modal, DictionaryAPI, Params){
                    //$scope.dictionary = null;
                    $scope.pageSetting = {
                        formDisabled : true
                    };
                    if(Params.action == "UPDATE" || Params.action == "ADD"){
                        $scope.pageSetting.formDisabled = false;
                    }
                    if(Params && Params.id){
                        $scope.dictionary = DictionaryAPI.get({
                            id: Params.id
                        });
                    }else{
                        $scope.dictionary = new DictionaryAPI();
                    }
                    $scope.dictionaryDetailForm = {};
                    $scope.saveDictionary = function(){
                        if($scope.dictionaryDetailForm.validator.form()){
                            $scope.dictionary.$save(function(){
                                $modalInstance.close();
                            });
                        }
                    };
                    $scope.cancel = function(){
                        $modalInstance.dismiss();
                    };
                }]
            }).result.then(function () {
                $scope.getDictionaryList();
            }, function () {
                $scope.getDictionaryList();
            });
        };
        // 删除
        $scope.delete = function(index){
            DictionaryAPI.delete({id:$scope.dictionaryList[index].id}, function(){
                $scope.getDictionaryList();
            });
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