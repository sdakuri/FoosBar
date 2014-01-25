package com.dakuris.foosbar.manager.impl;

import com.dakuris.foosbar.base.Game;
import com.dakuris.foosbar.base.Player;
import com.dakuris.foosbar.dao.GameDAO;
import com.dakuris.foosbar.manager.GameManager;
import com.dakuris.foosbar.manager.PlayerManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: Shashidhar Dakuri
 * Email: shashidhar.dakuri@gmail.com
 * Date: 1/23/14
 * Time: 3:36 PM
 */
public class GameManagerImpl implements GameManager {

    @Autowired
    private PlayerManager playerManager;

    @Autowired
    private GameDAO gameDao;

    @Override
    public Game createGame(int playerOne, int playerTwo) {
        Player firstPlayer = playerManager.getPlayer(playerOne);
        Player secondPlayer = playerManager.getPlayer(playerTwo);

        if(firstPlayer==null|| secondPlayer==null)
            return null;
        if(firstPlayer==secondPlayer)
            return null;

        Game game = new Game(playerOne,playerTwo);

        return gameDao.createGame(game);
    }

    @Override
    public Game getGame(long id) {
        return gameDao.getGame(id);
    }

    @Override
    public Game deleteGame(long id) {
        return null;
    }
}
