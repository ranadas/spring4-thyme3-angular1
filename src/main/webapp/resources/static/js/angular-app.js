/**
 * Created by x148128 on 02/11/2016.
 */
'use strict';

var skelApp = angular.module("angular-skel", ['ngRoute']);

// Configure routing
skelApp.config(function ($routeProvider) {
    $routeProvider.
    when('/', {
        templateUrl: 'partials/welcome',
        controller: 'WelcomeController'
    }).
    when('/form', {
        templateUrl: 'partials/form-page',
        controller: 'FormDemoController'
    }).
    when('/success', {
        templateUrl: 'partials/success',
        controller: 'SuccessController'
    }).
    otherwise({
        redirectTo: '/'
    });

    $routeProvider.when('/error', {
        templateUrl: 'partials/error',
        controller: 'ErrorController'
    });

});

skelApp.controller('WelcomeController', function ($scope) {
    console.log('WelcomeController');
});

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