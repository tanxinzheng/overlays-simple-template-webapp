/**
 * Created by tanxinzheng on 16/7/3.
 */
define(function(){
    return ["$scope", "$modal", "DictionaryGroupAPI", "$dialog", function($scope, $modal, DictionaryGroupAPI, $dialog){
        $scope.pageSetting = {
            checkAll : false
        };
        $scope.pageInfoSetting = {
            pageSize:10,
            pageNum:1
        };
        $scope.queryParam = {};
        // 查询列表
        $scope.getDictionaryGroupList = function(){
            DictionaryGroupAPI.query({
                keyword: $scope.queryParam.keyword,
                limit: $scope.pageInfoSetting.pageSize,
                offset: $scope.pageInfoSetting.pageNum
            }, function(data){
                $scope.dictionaryGroupList = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getDictionaryGroupList;
            });
        };
        // 全选
        $scope.checkAll = function(a){
            if(!$scope.dictionaryGroupList){
                return;
            }
            var num = 0;
            for (var i = 0; i < $scope.dictionaryGroupList.length; i++) {
                if($scope.dictionaryGroupList[i].checked){
                    num++;
                }
            }
            if($scope.dictionaryGroupList && $scope.dictionaryGroupList.length > 0 && num == $scope.dictionaryGroupList.length){
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
                templateUrl: 'dictionaryGroup_detail.html',
                modal:true,
                resolve: {
                    Params: function () {
                        var params = {
                            action: action
                        };
                        if($scope.dictionaryGroupList[index] && $scope.dictionaryGroupList[index].id){
                            params.id = $scope.dictionaryGroupList[index].id;
                        }
                        return params;
                    }
                },
                controller: ['$scope', '$modalInstance', "$modal", "DictionaryGroupAPI", "Params", function($scope, $modalInstance, $modal, DictionaryGroupAPI, Params){
                    //$scope.dictionaryGroup = null;
                    $scope.pageSetting = {
                        formDisabled : true
                    };
                    if(Params.action == "UPDATE" || Params.action == "ADD"){
                        $scope.pageSetting.formDisabled = false;
                    }
                    if(Params && Params.id){
                        $scope.dictionaryGroup = DictionaryGroupAPI.get({
                            id: Params.id
                        });
                    }else{
                        $scope.dictionaryGroup = new DictionaryGroupAPI();
                    }
                    $scope.dictionaryGroupDetailForm = {};
                    $scope.saveDictionaryGroup = function(){
                        if($scope.dictionaryGroupDetailForm.validator.form()){
                            $scope.dictionaryGroup.$save(function(){
                                $modalInstance.close();
                            });
                        }
                    };
                    $scope.cancel = function(){
                        $modalInstance.dismiss();
                    };
                }]
            }).result.then(function () {
                $scope.getDictionaryGroupList();
            }, function () {
                $scope.getDictionaryGroupList();
            });
        };
        $scope.delete = function(index){
            DictionaryGroupAPI.delete({id:$scope.dictionaryGroupList[index].id}, function(){
                $scope.getDictionaryGroupList();
            });
        };
        var init = function(){
            $scope.getDictionaryGroupList();
        };
        init();
    }]
});