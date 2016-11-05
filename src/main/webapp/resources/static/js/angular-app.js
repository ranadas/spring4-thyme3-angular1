/**
 * Created by x148128 on 02/11/2016.
 */
'use strict';
var skelApp = angular.module("angular-skel", ['ngRoute']);

// Configure routing
skelApp.config(function ($routeProvider, $locationProvider) {
    $routeProvider.when('/', {
        templateUrl: 'partials/welcome',
        controller: 'WelcomeController'
    });

    $routeProvider.when('/form', {
        templateUrl: 'partials/form-page',
        controller: 'FormDemoController'
    });

    $routeProvider.when('/success', {
        templateUrl: 'partials/success',
        controller: 'SuccessController'
    }).otherwise({
        redirectTo: '/'
    });
    $routeProvider.when('/error', {
        templateUrl: 'partials/error',
        controller: 'ErrorController'
    });


    // use the HTML5 History API
    $locationProvider.html5Mode(true);
});

skelApp.controller('WelcomeController', ['$scope', '$location', function ($scope, $location) {
    console.log('WelcomeController');
    $scope.Message = "click something.";

    $scope.go = function (path) {
        console.log('WelcomeController:g0' + path);
        $location.url(path);
    };
    //$scope.protocol = $location.protocol();

    //$scope.setpath = function (path) {
    //    $location.path(path);
    //}
}]);


skelApp.controller("FormDemoController", ['$scope', function ($scope) {

    $scope.countries = ['Ireland', 'France', 'UK'];
    $scope.master = {};

    $scope.update = function (user) {
        console.log('update' + user);
        $scope.master = angular.copy(user);
    };

    $scope.reset = function () {
        console.log('reset');
        $scope.user = {};
    };
    $scope.reset();
}]);


skelApp.controller('SuccessController', function ($scope) {
    console.log('SuccessController');
});

skelApp.controller('ErrorController', function ($scope) {
    console.log('ErrorController');
});

skelApp.controller("DemoController", ['$scope', function ($scope) {
    console.log('DemoController');
    $scope.firstName = "Rana";
    $scope.lastName = "Das";
}]);