'use strict';

var FoosBarApp = {};

var App = angular.module('FoosBar',['ui.bootstrap']);

function registration($scope, $http){
    $scope.registerPlayer = function(player){
        $http.post('player/register', player).success(function() {
            $scope.player.firstName = '';
            $scope.player.lastName = '';
        });
    }
}

function GameControl($scope,$http){
    $scope.selectedPlayer1 = null;
    $scope.selectedPlayer2 = null;
    var playGame = $('#playgame')
    var startGame = $('#startgame')
    var firstPlayer = $('#player1')
    var secondPlayer = $('#player2')
    playGame.click(function(){
        $http.get('player/all').success(function(players){
            $scope.players = players;
        });
    });

    firstPlayer.change(function(){
        $scope.selectedPlayer1 = $('#player1').val();
    })

    secondPlayer.change(function(){
        $scope.selectedPlayer2 = $('#player2').val();
    });

    startGame.click(function(){

        var players = {"firstplayer":$scope.selectedPlayer1,"secondplayer":$scope.selectedPlayer2}
        $http.post('game/start',players).success(function(game){
        })
    });
}

