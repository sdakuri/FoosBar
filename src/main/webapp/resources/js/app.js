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

function GameControl($scope,$http,$modal){
    $scope.selectedPlayer1 = null;
    $scope.selectedPlayer2 = null;
    var playGame = $('#playgame')
    var startGame = $('#startgame')
    var firstPlayer = $('#player1')
    var secondPlayer = $('#player2')

    $scope.game = null;

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
            $scope.game = game;
            var modalInstance = $modal.open({
                templateUrl: 'modal.html',
                controller: ModalInstanceCtrl,
                resolve:{
                    game: function(){
                        return $scope.game;
                    }
                }
            });
        })
    });
}


var ModalInstanceCtrl = function($modal, $scope, $modalInstance, game, $http){

    $scope.game = game;
    $scope.ok = function(){
        $modalInstance.close();
    };

    $scope.playerOnePoint = function(){
        console.log("Player 1 point")
        var playerOnePoint = {"gameid":game.id, "playerid":game.playerOne}
        $http.post('game/point', playerOnePoint).success(function(game){
            $scope.game = game;
            if(game.playerOneScore===5){
                $modal.open({
                    templateUrl: "winner.html",
                    controller: ModalWinnerInstanceCtrl,
                    resolve:{
                        winner: function(){
                            return game.playerOneFullName;
                        }
                    }
                });
                $modalInstance.close();
            }

        });
    }

    $scope.playerTwoPoint = function(){
        console.log("Player 2 point")
        var playerTwoPoint = {"gameid":game.id, "playerid":game.playerTwo}
        $http.post('game/point', playerTwoPoint).success(function(game){
            $scope.game = game;
            if(game.playerTwoScore===5){
                $modal.open({
                        templateUrl: "winner.html",
                        controller: ModalWinnerInstanceCtrl,
                        resolve:{
                            winner: function(){
                                return game.playerTwoFullName;
                            }
                        }
                    });
                $modalInstance.close();
            }
        });
    }
}

var ModalWinnerInstanceCtrl = function($scope, $modalInstance, winner){
    $scope.winner = winner;
    setTimeout(function(){
        $modalInstance.close();
    },2000);
}
