/**
 * Created by tanxinzheng on 16/7/3.
 */
app.controller("UserController", ["$scope", "$modal", function($scope, $modal){
    $scope.addUser = function(){
        var modalInstance = $modal.open({
            templateUrl: 'addUser.html',
            backdrop:"static",
            backdropClass:"ng-hide",
            modal:true,
            controller: ['$scope', '$modalInstance', "$modal", function($scope, $modalInstance, $modal){
                $scope.cancel = function(){
                    $modalInstance.close();
                };
                $scope.open2 = function(){
                    var modalInstance = $modal.open({
                        templateUrl: 'addUser.html',
                        backdrop:"static",
                        backdropClass:"ng-hide",
                        modal:true,
                        controller: ['$scope', '$modalInstance', function($scope, $modalInstance){
                            $scope.cancel = function(){
                                $modalInstance.close();
                            }
                        }],
                        resolve: {
                            items: function () {
                                return $scope.items;
                            }
                        }
                    });
                }
            }],
            resolve: {
                items: function () {
                    return $scope.items;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            $scope.selected = selectedItem;
        }, function () {
            //$log.info('Modal dismissed at: ' + new Date());
        });
    }

}]);