package com.dakuris.foosbar.manager;

import com.dakuris.foosbar.base.Game;
import com.dakuris.foosbar.base.GameView;
import com.dakuris.foosbar.base.Player;

/**
 * Created by IntelliJ IDEA.
 * User: Shashidhar Dakuri
 * Email: shashidhar.dakuri@gmail.com
 * Date: 1/23/14
 * Time: 3:34 PM
 */
public interface GameManager {

    public GameView createGame(int playerOne, int playerTwo);

    public Game getGame(long id);

    public Game deleteGame(long id);

    GameView assignPoint(long gameid, int player);

    void endGame(long gameid);
}
