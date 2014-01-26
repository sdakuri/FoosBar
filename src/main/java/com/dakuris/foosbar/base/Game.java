package com.dakuris.foosbar.base;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Shashidhar Dakuri
 * Email: shashidhar.dakuri@gmail.com
 * Date: 1/23/14
 * Time: 3:04 PM
 */
public class Game implements Serializable {

    private long id;
    private long playerOne;
    private long playerTwo;
    private int playerOneScore;
    private int playerTwoScore;

    public Game(){};

    public Game(long playerOne, long playerTwo){
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(long playerOne) {
        this.playerOne = playerOne;
    }

    public long getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(long playerTwo) {
        this.playerTwo = playerTwo;
    }

    public int getPlayerOneScore() {
        return playerOneScore;
    }

    public void setPlayerOneScore(int playerOneScore) {
        this.playerOneScore = playerOneScore;
    }

    public int getPlayerTwoScore() {
        return playerTwoScore;
    }

    public void setPlayerTwoScore(int playerTwoScore) {
        this.playerTwoScore = playerTwoScore;
    }
}
