/**
 * Created by x148128 on 02/11/2016.
 */
'use strict';
var skelApp = angular.module("angular-skel", ['ngRoute', 'ngSanitize', 'ngResource']);

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


//// SERVICES
//http://www.java-allandsundry.com/2014/05/spring-rest-controller-with-angularjs.html
skelApp.factory("UserService", function ($resource) {
    return $resource("/saveuser", {id: "@id"}, {
        update: {
            method: 'PUT'
        }
    });
});

////
skelApp.controller('WelcomeController', ['$scope', '$location', function ($scope, $location) {
    console.log('WelcomeController');
    $scope.Message = "click something.";

    $scope.go = function (path) {
        console.log('WelcomeController:g0' + path);
        $location.url(path);
    };
}]);


skelApp.controller("FormDemoController", ['$scope', '$location', '$resource', 'UserService', function ($scope, $location, $resource, UserService) {

    $scope.countries = ['Ireland', 'France', 'UK', 'India'];
    $scope.master = {};

    $scope.update = function (user) {
        console.log('update' + user);
        $scope.master = angular.copy(user);

        var resp = UserService.save({}, {
                name: user.name,
                email: user.email,
                gender: user.gender,
                agree: user.agree,
                country: user.country
            },
            function (response) {
                console.log(response);
                $location.url('/success');
            },
            function (failedResponse) {
                console.log(failedResponse);
                $location.url('/error');
            });

        console.log(resp);

        //var userRes = $resource('/saveuser');
        //userRes.save({
        //        name: user.name,
        //        email: user.email,
        //        gender: user.gender,
        //        agree: user.agree,
        //        country: user.country
        //    },
        //    function (response) {
        //        console.log(response);
        //        $scope.message = response.message;
        //    },
        //    function(failedResponse){
        //        console.log(failedResponse);
        //    });
    };

    $scope.reset = function () {
        console.log('reset');
        $scope.user = {};
    };

    $scope.go = function (path) {
        console.log('FormDemoController:g0' + path);
        $location.url(path);
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
    $scope.lastName = "<span><b>Das</b></span>";
}]);