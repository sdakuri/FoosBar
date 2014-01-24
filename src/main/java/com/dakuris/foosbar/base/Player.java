package com.dakuris.foosbar.base;

/**
 * Created by IntelliJ IDEA.
 * User: Shashidhar Dakuri
 * Email: shashidhar.dakuri@gmail.com
 * Date: 1/23/14
 * Time: 3:04 PM
 */
public class Player {

    private long id;
    private String firstName;
    private String lastName;
    private int numberOfGamesWon;

    public Player(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNumberOfGamesWon() {
        return numberOfGamesWon;
    }

    public void setNumberOfGamesWon(int numberOfGamesWon) {
        this.numberOfGamesWon = numberOfGamesWon;
    }
}
