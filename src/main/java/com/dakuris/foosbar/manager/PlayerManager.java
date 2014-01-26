package com.dakuris.foosbar.manager;

import com.dakuris.foosbar.base.Player;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Shashidhar Dakuri
 * Email: shashidhar.dakuri@gmail.com
 * Date: 1/23/14
 * Time: 3:18 PM
 */
public interface PlayerManager {

    public Player createPlayer(String firstName, String lastName);

    public Player getPlayer(long id);

    public boolean deletePlayer(long id);

    List<Player> getPlayers();

    Player getPlayer(String firstPlayerName, String s);
}
