/**
 * Created by tanxinzheng on 16/7/3.
 */
angular.module('loginApp',[

]).controller("signInController", ['$scope', '$http', function($scope, $http) {
    $scope.app = {
        name:"业务系统模板"
    };
    $scope.user = {};
    $scope.authError = null;
    $scope.login = function() {
        $scope.authError = null;
        // Try to login
        $http.post('api/login', {email: $scope.user.email, password: $scope.user.password})
            .then(function(response) {
                if ( !response.data.user ) {
                    $scope.authError = 'Email or Password not right';
                }else{
                    //$state.go('app.dashboard-v1');
                }
            }, function(x) {
                $scope.authError = 'Server Error';
            });
    };
}]).controller('signUpFormController', ['$scope', '$http', function($scope, $http) {
    $scope.user = {};
    $scope.authError = null;
    $scope.signup = function() {
        $scope.authError = null;
        // Try to create
        $http.post('api/signup', {name: $scope.user.name, email: $scope.user.email, password: $scope.user.password})
            .then(function(response) {
                if ( !response.data.user ) {
                    $scope.authError = response;
                }else{
                    //$state.go('app.dashboard-v1');
                }
            }, function(x) {
                $scope.authError = 'Server Error';
            });
    };
}])
;