package com.dakuris.foosbar.base;

/**
 * Created with IntelliJ IDEA.
 * Author: Shashi
 * Email: shashidhar.dakuri@gmail.com
 * Date: 1/25/14
 * Time: 12:34 PM
 */
public class GameView  extends Game{

    private String playerOneFullName;
    private String playerTwoFullName;

    public GameView(){}

    public GameView(long playerOne, long playerTwo) {
        super(playerOne,playerTwo);
    }

    public String getPlayerOneFullName() {
        return playerOneFullName;
    }

    public void setPlayerOneFullName(String playerOneFullName) {
        this.playerOneFullName = playerOneFullName;
    }

    public String getPlayerTwoFullName() {
        return playerTwoFullName;
    }

    public void setPlayerTwoFullName(String playerTwoFullName) {
        this.playerTwoFullName = playerTwoFullName;
    }
}
