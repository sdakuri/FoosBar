package com.dakuris.foosbar.controller;

import com.dakuris.foosbar.base.Player;
import com.dakuris.foosbar.manager.LeaderBoardManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Shashidhar Dakuri
 * Email: shashi.dakuri@solutionset.com
 * Date: 1/24/14
 * Time: 2:14 PM
 */
@Controller
@RequestMapping("/leaders")
public class LeaderboardController {

    @Autowired
    LeaderBoardManager leaderBoardManager;

    @RequestMapping(value = "/monthly", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Player> getMonthlyLeaders(){
        return leaderBoardManager.getMonthlyLeaderBoard();
    }

    @RequestMapping(value = "/quarterly", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Player> getQuaterlyLeaders(){
        return leaderBoardManager.getQuarterlyLeaderBoard();
    }

    @RequestMapping(value = "/yearly", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Player> getYearlyLeaders(){
        return leaderBoardManager.getYearlyLeaderBoard();
    }

    @RequestMapping(value = "/alltime", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Player> getAllTimeLeaders(){
        return leaderBoardManager.getAllTimeLeaderBoard();
    }

    @RequestMapping(value = "/allplayers", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Player> getAllPlayers(){
        return leaderBoardManager.getAllPlayers();
    }
}
