package com.dakuris.foosbar.manager.impl;

import com.dakuris.foosbar.base.Player;
import com.dakuris.foosbar.dao.PlayerDAO;
import com.dakuris.foosbar.manager.PlayerManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Shashidhar Dakuri
 * Email: shashidhar.dakuri@gmail.com
 * Date: 1/23/14
 * Time: 3:20 PM
 */
public class PlayerManagerImpl implements PlayerManager {

    @Autowired
    private PlayerDAO playerDAO;

    @Override
    public Player createPlayer(String firstName, String lastName) {
        Player player = new Player(firstName, lastName);
        if(playerDAO.createPlayer(player))
            return player;
        else
            return null;
    }

    @Override
    public Player getPlayer(long id) {
        return playerDAO.getPlayer(id);
    }

    @Override
    public List<Player> getPlayers() {
        return playerDAO.getPlayers();
    }

    @Override
    public boolean deletePlayer(long id) {
        return playerDAO.deletePlayer(id);
    }
}
