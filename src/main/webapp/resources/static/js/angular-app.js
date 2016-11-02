/**
 * Created by x148128 on 02/11/2016.
 */

var skelApp = angular.module("angular-skel", []);


skelApp.controller("FormDemoController", ['$scope', function ($scope) {

    $scope.countries = ['Ireland', 'France', 'UK'];
    $scope.master = {};

    $scope.update = function (user) {
        console.log('update'+user);
        $scope.master = angular.copy(user);
    };

    $scope.reset = function () {
        console.log('reset');
        $scope.user = {};
    };
    $scope.reset();
}]);

skelApp.controller("DemoController", ['$scope', function ($scope) {
    $scope.firstName = "Rana";
    $scope.lastName = "Das";
}]);