package com.dakuris.foosbar.base;

/**
 * Created with IntelliJ IDEA.
 * Author: Shashi
 * Email: shashidhar.dakuri@gmail.com
 * Date: 1/25/14
 * Time: 12:34 PM
 */
public class GameView  extends Game{

    private String playerOneFullname;
    private String playerTwoFullName;

    public GameView(){}

    public GameView(int playerOne, int playerTwo) {
        super(playerOne,playerTwo);
    }

    public String getPlayerOneFullname() {
        return playerOneFullname;
    }

    public void setPlayerOneFullname(String playerOneFullname) {
        this.playerOneFullname = playerOneFullname;
    }

    public String getPlayerTwoFullName() {
        return playerTwoFullName;
    }

    public void setPlayerTwoFullName(String playerTwoFullName) {
        this.playerTwoFullName = playerTwoFullName;
    }
}
