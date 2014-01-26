package com.dakuris.foosbar.base;

/**
 * Created by IntelliJ IDEA.
 * User: Shashidhar Dakuri
 * Email: shashi.dakuri@solutionset.com
 * Date: 1/25/14
 * Time: 11:22 PM
 */
public class GameCSVBean {

    private String firstPlayerName;
    private int firstPlayerScore;
    private String secondPlayerName;
    private int secondPlayerScore;

    public GameCSVBean(){}

    public GameCSVBean(String firstPlayerName, int firstPlayerScore, String secondPlayerName, int secondPlayerScore){
        this.firstPlayerName = firstPlayerName;
        this.firstPlayerScore = firstPlayerScore;
        this.secondPlayerName = secondPlayerName;
        this.secondPlayerScore = secondPlayerScore;
    }

    public String getFirstPlayerName() {
        return firstPlayerName;
    }

    public void setFirstPlayerName(String firstPlayerName) {
        this.firstPlayerName = firstPlayerName;
    }

    public String getSecondPlayerName() {
        return secondPlayerName;
    }

    public void setSecondPlayerName(String secondPlayerName) {
        this.secondPlayerName = secondPlayerName;
    }

    public int getFirstPlayerScore() {
        return firstPlayerScore;
    }

    public void setFirstPlayerScore(int firstPlayerScore) {
        this.firstPlayerScore = firstPlayerScore;
    }

    public int getSecondPlayerScore() {
        return secondPlayerScore;
    }

    public void setSecondPlayerScore(int secondPlayerScore) {
        this.secondPlayerScore = secondPlayerScore;
    }
}
