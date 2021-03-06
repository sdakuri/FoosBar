package com.dakuris.foosbar.dao;

import com.dakuris.foosbar.base.Player;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Shashidhar Dakuri
 * Email: shashidhar.dakuri@gmail.com
 * Date: 1/23/14
 * Time: 3:40 PM
 */
public class LeaderBoardDAO  extends JdbcDaoSupport {

    private static final String GET_MONTHLY_LEADERS = "SELECT p.id, p.firstname, p.lastname, l.gameswon FROM leaders l\n" +
                            "LEFT JOIN player P ON p.id = l.player\n" +
                            "WHERE l.type='MONTHLY' AND l.gameswon>0 \n" +
                            "ORDER BY l.gameswon DESC\n" +
                            "LIMIT 5";
    private static final String GET_QUATERLY_LEADERS = "SELECT p.id, p.firstname, p.lastname, l.gameswon FROM leaders l\n" +
                            "LEFT JOIN player P ON p.id = l.player\n" +
                            "WHERE l.type='QUARTERLY' AND l.gameswon>0 \n" +
                            "ORDER BY l.gameswon DESC\n" +
                            "LIMIT 5";
    private static final String GET_YEARLY_LEADERS = "SELECT p.id, p.firstname, p.lastname, l.gameswon FROM leaders l\n" +
                            "LEFT JOIN player P ON p.id = l.player\n" +
                            "WHERE l.type='YEARLY' AND l.gameswon>0 \n" +
                            "ORDER BY l.gameswon DESC\n" +
                            "LIMIT 5";
    private static final String GET_ALL_TIME_LEADERS = "SELECT p.id, p.firstname, p.lastname, l.gameswon FROM leaders l\n" +
                            "LEFT JOIN player P ON p.id = l.player\n" +
                            "WHERE l.type='ALLTIME' AND l.gameswon>0 \n" +
                            "ORDER BY l.gameswon DESC\n" +
                            "LIMIT 5";

    private static final String GET_ALL_PLAYERS = "SELECT p.id, p.firstname, p.lastname, l.gameswon FROM leaders l \n" +
                                "LEFT JOIN player P ON p.id = l.player \n" +
                                "WHERE l.type='ALLTIME' AND l.gameswon>0 \n" +
                                "ORDER BY l.gameswon DESC ";


    public List<Player> getMonthlyLeaders() {
        return getJdbcTemplate().query(GET_MONTHLY_LEADERS, rowMapper);
    }

    public List<Player> getQuaterlyLeaders() {
        return getJdbcTemplate().query(GET_QUATERLY_LEADERS,rowMapper);
    }

    public List<Player> getYearlyLeaders() {
        return getJdbcTemplate().query(GET_YEARLY_LEADERS,rowMapper);
    }

    public List<Player> getAllTimeLeaders() {
        return getJdbcTemplate().query(GET_ALL_TIME_LEADERS,rowMapper);
    }

    public List<Player> getAllPlayers() {
        return getJdbcTemplate().query(GET_ALL_PLAYERS,rowMapper);
    }

    ParameterizedRowMapper<Player> rowMapper = new ParameterizedRowMapper<Player>() {
        @Override
        public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
            Player player = new Player();
            player.setId(rs.getInt("id"));
            player.setFirstName(rs.getString("firstname"));
            player.setLastName(rs.getString("lastname"));
            player.setNumberOfGamesWon(rs.getInt("gameswon"));
            return player;
        }
    };
}
