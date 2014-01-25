'use strict';

var FoosBarApp = {};

var App = angular.module('FoosBar',[]);



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

function registration($scope, $http){
    $scope.registerPlayer = function(player){
        $http.post('player/register', player).success(function() {
            console.log("Yay, created player")
        });
    }
}