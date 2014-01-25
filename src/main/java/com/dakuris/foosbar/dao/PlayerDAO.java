package com.dakuris.foosbar.dao;

import com.dakuris.foosbar.base.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Shashidhar Dakuri
 * Email: shashidhar.dakuri@gmail.com
 * Date: 1/23/14
 * Time: 3:21 PM
 */
public class PlayerDAO extends JdbcDaoSupport {

    Logger log = LogManager.getLogger(PlayerDAO.class);
    private static final String CREATE_PLAYER = "INSERT INTO player(id, firstname, lastname) values (?, ?,?) ";
    private static final String GET_PLAYER = "SELECT * FROM player WHERE id = ? ";
    private static final String GET_PLAYERS = "SELECT * FROM player ";
    private static final String GET_ID = "SELECT MAX(id) FROM player ";

    private long getNextID(){
        try{
            return (getJdbcTemplate().queryForObject(GET_ID,Long.class) +1);
        }catch (NullPointerException npe){
            return 1001;
        }
    }


    public Player createPlayer(Player player){
        player.setId(getNextID());
        try{
            int result = getJdbcTemplate().update(CREATE_PLAYER, player.getId(), player.getFirstName(),player.getLastName());
            if(result==1)
                return player;
            else
                return null;
        }catch(Exception e){
            log.error("Error creating the player: "+e);
            return null;
        }
    }

    public Player getPlayer(long id){
        Player player = null;
        try{
            player = getJdbcTemplate().queryForObject(GET_PLAYER,playerParameterizedRowMapper,id);
        }catch (Exception e){
            log.error("Error retrieving the player with id "+id+ " and the error is: "+e);
        }
        return player;
    }

    public boolean deletePlayer(long id){
        return true;
    }

    public List<Player> getPlayers() {
        return getJdbcTemplate().query(GET_PLAYERS,playerParameterizedRowMapper);
    }

    private ParameterizedRowMapper<Player> playerParameterizedRowMapper
            = new ParameterizedRowMapper<Player>() {
        @Override
        public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
            Player player = new Player();
            player.setId(rs.getInt("id"));
            player.setFirstName(rs.getString("firstname"));
            player.setLastName(rs.getString("lastname"));
            player.setNumberOfGamesWon(rs.getInt("numberofgameswon"));
            return player;
        }
    };
}
