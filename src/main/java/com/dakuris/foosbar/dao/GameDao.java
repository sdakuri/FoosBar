package com.dakuris.foosbar.dao;

import com.dakuris.foosbar.base.Game;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Shashidhar Dakuri
 * Email: shashidhar.dakuri@gmail.com
 * Date: 1/23/14
 * Time: 3:36 PM
 */
public class GameDAO extends JdbcDaoSupport {

    private static final String CREATE_GAME = "INSERT INTO game(playerone, playertwo, creationdate) VALUES (?,?,?) ";
    private static final String GET_GAME = "SELECT * FROM game WHERE id = ? ";

    public int createGame(Game game){
        return getJdbcTemplate().update(CREATE_GAME,game.getPlayerOne(),game.getPlayerTwo(),new Date(System.currentTimeMillis()));
    }

    public Game getGame(long id){
        return getJdbcTemplate().queryForObject(GET_GAME,rowMapper, id);
    }

    public boolean deleteGame(long id){
        return false;
    }

    private ParameterizedRowMapper<Game> rowMapper = new ParameterizedRowMapper<Game>() {
        @Override
        public Game mapRow(ResultSet resultSet, int i) throws SQLException {
            Game game = new Game();
            game.setId(resultSet.getLong("id"));
            game.setPlayerOne(resultSet.getInt("playerone"));
            game.setPlayerTwo(resultSet.getInt("playertwo"));
            game.setPlayerOneScore(resultSet.getInt("playeronescore"));
            game.setPlayerTwoScore(resultSet.getInt("playertwoscore"));

            return game;
        }
    };
}
