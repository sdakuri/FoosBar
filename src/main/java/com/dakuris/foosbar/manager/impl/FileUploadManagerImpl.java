package com.dakuris.foosbar.manager.impl;

import com.dakuris.foosbar.base.GameCSVBean;
import com.dakuris.foosbar.base.GameView;
import com.dakuris.foosbar.base.Player;
import com.dakuris.foosbar.manager.FileUploadManager;
import com.dakuris.foosbar.manager.GameManager;
import com.dakuris.foosbar.manager.PlayerManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by IntelliJ IDEA.
 * User: Shashidhar Dakuri
 * Email: shashi.dakuri@solutionset.com
 * Date: 1/25/14
 * Time: 11:46 PM
 */
public class FileUploadManagerImpl implements FileUploadManager {

    @Autowired
    PlayerManager playerManager;

    @Autowired
    GameManager gameManager;

    @Override
    public void parseUploadedResults(List<GameCSVBean> listOfGames) {
        ListIterator<GameCSVBean> iterator = listOfGames.listIterator();

        while(iterator.hasNext()){
            GameCSVBean game = iterator.next();

            Player playerOne = playerManager.getPlayer(game.getFirstPlayerName(), " ");
            Player playerTwo = playerManager.getPlayer(game.getSecondPlayerName(), " ");

            if(playerOne==null){
                playerOne = playerManager.createPlayer(game.getFirstPlayerName(), " ");
            }

            if(playerTwo==null){
                playerTwo = playerManager.createPlayer(game.getSecondPlayerName(), " ");
            }

            GameView gameView  = gameManager.createGame(playerOne.getId(), game.getFirstPlayerScore(), playerTwo.getId(), game.getSecondPlayerScore());

            gameManager.endGame(gameView.getId());

        }
    }
}
