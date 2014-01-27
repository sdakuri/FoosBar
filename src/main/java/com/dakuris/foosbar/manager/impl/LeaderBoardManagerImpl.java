package com.dakuris.foosbar.manager.impl;

import com.dakuris.foosbar.base.Player;
import com.dakuris.foosbar.dao.LeaderBoardDAO;
import com.dakuris.foosbar.manager.LeaderBoardManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Shashidhar Dakuri
 * Email: shashidhar.dakuri@gmail.com
 * Date: 1/23/14
 * Time: 3:40 PM
 */
public class LeaderBoardManagerImpl implements LeaderBoardManager {

    @Autowired
    LeaderBoardDAO dao;

    @Override
    public List<Player> getMonthlyLeaderBoard() {
        return dao.getMonthlyLeaders();
    }

    @Override
    public List<Player> getQuarterlyLeaderBoard() {
        return dao.getQuaterlyLeaders();
    }

    @Override
    public List<Player> getYearlyLeaderBoard() {
        return dao.getYearlyLeaders();
    }

    @Override
    public List<Player> getAllTimeLeaderBoard() {
        return dao.getAllTimeLeaders();
    }

    @Override
    public List<Player> getAllPlayers() {
        return dao.getAllPlayers();
    }
}
