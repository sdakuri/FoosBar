package com.dakuris.foosbar.dao;

import com.dakuris.foosbar.base.Game;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Created by IntelliJ IDEA.
 * User: Shashidhar Dakuri
 * Email: shashidhar.dakuri@gmail.com
 * Date: 1/23/14
 * Time: 3:36 PM
 */
public class GameDAO extends JdbcDaoSupport {

    public int createGame(Game game){
        return 0;
    }

    public Game getGame(long id){
        return null;
    }

    public boolean deleteGame(long id){
        return false;
    }
}
