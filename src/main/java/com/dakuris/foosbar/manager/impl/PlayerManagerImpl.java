package com.dakuris.foosbar.manager.impl;

import com.dakuris.foosbar.base.Player;
import com.dakuris.foosbar.dao.PlayerDAO;
import com.dakuris.foosbar.manager.PlayerManager;

/**
 * Created by IntelliJ IDEA.
 * User: Shashidhar Dakuri
 * Email: shashidhar.dakuri@gmail.com
 * Date: 1/23/14
 * Time: 3:20 PM
 */
public class PlayerManagerImpl implements PlayerManager {

    private PlayerDAO playerDAO;

    @Override
    public Player createPlayer(String firstName, String lastName) {
        Player player = new Player(firstName, lastName);

        return null;
    }

    @Override
    public Player getPlayer(long id) {
        return null;
    }

    @Override
    public boolean deletePlayer(long id) {
        return false;
    }
}
