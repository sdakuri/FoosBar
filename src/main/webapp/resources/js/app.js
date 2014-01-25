'use strict';

var FoosBarApp = {};

var App = angular.module('FoosBarApp');

App.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('#register', {
            controller: PlayerController
        });
    $routeProvider.otherwise('/', {
            templateUrl: 'index'
        });
}]);

/**
 * PlayerController
 * @Constructor
 */
var PlayerController = function($scope, $http){
    $scope.registerPlayer = function(player){
        $http.post('player/register', player).success(function() {
            console.log("Yay, created player")
        });
    }
}