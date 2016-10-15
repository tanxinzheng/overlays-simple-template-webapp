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
                limit: 10,
                offset: 1
            }, function(data){
                $scope.dictionaryList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getDictionaryList;
            });
        };
        // 全选
        $scope.checkAll = function(a){
            if(!$scope.dictionaryList){
                return;
            }
            var num = 0;
            for (var i = 0; i < $scope.dictionaryList.length; i++) {
                if($scope.dictionaryList[i].checked){
                    num++;
                }
            }
            if($scope.dictionaryList && $scope.dictionaryList.length > 0 && num == $scope.dictionaryList.length){
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
        $scope.delete = function(index){
            DictionaryAPI.delete({id:$scope.dictionaryList[index].id}, function(){
                $scope.getDictionaryList();
            });
        };
        var init = function(){
            $scope.getDictionaryList();
        };
        init();
    }]
});