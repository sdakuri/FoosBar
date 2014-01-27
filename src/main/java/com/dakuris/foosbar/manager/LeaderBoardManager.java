package com.dakuris.foosbar.manager;

import com.dakuris.foosbar.base.Player;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Shashidhar Dakuri
 * Email: shashidhar.dakuri@gmail.com
 * Date: 1/23/14
 * Time: 3:37 PM
 */
public interface LeaderBoardManager {

    public List<Player> getMonthlyLeaderBoard();

    public List<Player> getQuarterlyLeaderBoard();

    public List<Player> getYearlyLeaderBoard();

    public List<Player> getAllTimeLeaderBoard();

    List<Player> getAllPlayers();
}
