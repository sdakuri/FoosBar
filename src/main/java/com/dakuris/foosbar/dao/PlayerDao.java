package com.dakuris.foosbar.dao;

import com.dakuris.foosbar.base.Player;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Created by IntelliJ IDEA.
 * User: Shashidhar Dakuri
 * Email: shashidhar.dakuri@gmail.com
 * Date: 1/23/14
 * Time: 3:21 PM
 */
public class PlayerDAO extends JdbcDaoSupport {

    private static final String CREATE_PLAYER = "";

    public Player createPlayer(Player player){
        return null;
    }

    public Player getPlayer(long id){
        return null;
    }

    public boolean deletePlayer(long id){
        return false;
    }
}
