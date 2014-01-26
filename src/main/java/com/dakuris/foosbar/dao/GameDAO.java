package com.dakuris.foosbar.dao;

import com.dakuris.foosbar.base.GameView;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Shashidhar Dakuri
 * Email: shashidhar.dakuri@gmail.com
 * Date: 1/23/14
 * Time: 3:36 PM
 */
public class GameDAO extends JdbcDaoSupport {



    private static final String CREATE_GAME = "INSERT INTO game(id, playerone, playertwo, creationdate) VALUES (?, ?,?,?) ";
    private static final String GET_GAME = "SELECT * FROM game g LEFT JOIN   WHERE id = ? ";
    private static final String GET_ID = "SELECT MAX(id) FROM game ";
    private static final String GET_GAME_VIEW = "SELECT g.id, g.playerone, g.playertwo, g.playeronescore, g.playertwoscore, " +
                        "p1.firstname ||' '|| p1.lastname AS playeronename, p2.firstname ||' '|| p2.lastname AS playertwoname FROM game g " +
                        "LEFT JOIN player p1 ON g.playerone=p1.id " +
                        "LEFT JOIN player p2 ON g.playertwo =p2.id WHERE g.id = ? ";
    private static final String ADD_POINTS = "UPDATE game SET playeronescore = ?, playertwoscore =? WHERE id = ?";
    private static final String UPSERT_LEADERBOARD = "SELECT upsert_leaderboard(?)";

    private long getNextID(){
        try{
            return (getJdbcTemplate().queryForObject(GET_ID,Long.class) + 1);
        }catch (NullPointerException npe){
            return 1001;
        }
    }

    public GameView createGame(GameView game){
        game.setId(getNextID());
        getJdbcTemplate().update(CREATE_GAME,game.getId(), game.getPlayerOne(), game.getPlayerTwo(), new Date(System.currentTimeMillis()));

        return game;
    }

    public GameView getGame(long id){
        return getJdbcTemplate().queryForObject(GET_GAME_VIEW,rowMapper, id);
    }

    public boolean deleteGame(long id){
        return false;
    }

    public int updatePoints(long id, int playerOneScore, int playerTwoScore) {
        return getJdbcTemplate().update(ADD_POINTS, playerOneScore, playerTwoScore, id);
    }

    public void upsertLeaders(int playerid){
        String sql = "SELECT upsert_leaderboard("+playerid+")";
        getJdbcTemplate().queryForRowSet(sql);
    }

    private ParameterizedRowMapper<GameView> rowMapper = new ParameterizedRowMapper<GameView>() {
        @Override
        public GameView mapRow(ResultSet resultSet, int i) throws SQLException {
            GameView game = new GameView();
            game.setId(resultSet.getLong("id"));
            game.setPlayerOne(resultSet.getInt("playerone"));
            game.setPlayerTwo(resultSet.getInt("playertwo"));
            game.setPlayerOneScore(resultSet.getInt("playeronescore"));
            game.setPlayerTwoScore(resultSet.getInt("playertwoscore"));
            game.setPlayerOneFullName(resultSet.getString("playeronename"));
            game.setPlayerTwoFullName(resultSet.getString("playertwoname"));

            return game;
        }
    };

}
