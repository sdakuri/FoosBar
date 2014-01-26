package com.dakuris.foosbar.manager.impl;

import com.dakuris.foosbar.base.Game;
import com.dakuris.foosbar.base.GameView;
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
    public GameView createGame(int playerOne, int playerTwo) {
        Player firstPlayer = playerManager.getPlayer(playerOne);
        Player secondPlayer = playerManager.getPlayer(playerTwo);

        if((firstPlayer==null|| secondPlayer==null)&&firstPlayer==secondPlayer)
            return null;

        GameView game = new GameView(playerOne,playerTwo);
        game.setPlayerOneFullName(firstPlayer.getFirstName() + " " + firstPlayer.getLastName());
        game.setPlayerTwoFullName(secondPlayer.getFirstName()+ " "+secondPlayer.getLastName());

        gameDao.createGame(game);

        return game;
    }

    @Override
    public GameView getGame(long id) {
        return gameDao.getGame(id);
    }

    @Override
    public GameView assignPoint(long gameid, int player) {
        GameView game = getGame(gameid);
        if(game.getPlayerOne()==player)
            game.setPlayerOneScore(game.getPlayerOneScore()+1);
        else
            game.setPlayerTwoScore(game.getPlayerTwoScore()+1);

        int result = gameDao.updatePoints(game.getId(), game.getPlayerOneScore(), game.getPlayerTwoScore());

        if(game.getPlayerOneScore()==5 || game.getPlayerTwoScore()==5)
            gameDao.upsertLeaders(player);

        if(result == 1)
            return game;
        else
            return null;
    }

    @Override
    public Game deleteGame(long id) {
        return null;
    }
}
