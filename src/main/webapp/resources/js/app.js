'use strict';

var FoosBarApp = {};

var App = angular.module('FoosBar',['ui.bootstrap','angularFileUpload']);

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
        var gameid = {"gameid":game.id};
        $http.post('game/end',gameid).success(function(){
            $modalInstance.close();
        })
    };

    $scope.playerOnePoint = function(){
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

function MonthlyLeaderBoardControl($scope,$http){
    $http.get('leaders/monthly').success(function(leaders){
        $scope.monthlyleaders = leaders;
        $scope.loading = false;
    });
}

function QuarterlyLeaderBoardControl($scope,$http){
    $http.get('leaders/quarterly').success(function(leaders){
        $scope.quarterlyleaders = leaders
    });
}

function YearlyLeaderBoardControl($scope,$http){
    $http.get('leaders/yearly').success(function(leaders){
        $scope.yearlyleaders = leaders
    });
}

function AllTimeLeaderBoardControl($scope,$http){
    $http.get('leaders/alltime').success(function(leaders){
        $scope.alltimeleaders = leaders
    });
    $http.get('leaders/allplayers').success(function(leaders){
       $scope.allplayers = leaders;
    });
}

var FileUploader = function($scope, $upload){

    $scope.onFileSelect = function($files){
        $scope.upload = $upload.upload({
            url: '/upload',
            file: $files[0]
          }).progress(function(evt) {
            console.log('percent: ' + parseInt(100.0 * evt.loaded / evt.total));
          }).success(function(data, status, headers, config) {
            console.log(data);
          });
    }

};