package com.dakuris.foosbar.controller;

import com.dakuris.foosbar.base.Game;
import com.dakuris.foosbar.manager.GameManager;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Shashidhar Dakuri
 * Email: shashi.dakuri@solutionset.com
 * Date: 1/24/14
 * Time: 2:14 PM
 */
@Controller
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameManager gameManager;

    @RequestMapping(value="/start", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody Game startGame(@RequestBody String players){
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(players);
            int firstplayer = mapper.convertValue(node.get("firstplayer"),Integer.class);
            int secondplayer = mapper.convertValue(node.get("secondplayer"),Integer.class);

            Game game = gameManager.createGame(firstplayer, secondplayer);

            return game;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }

    }

    @RequestMapping(value="/start", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Game startGame1(@RequestParam(value = "firstplayer") int playerOne, @RequestParam(value = "secondplayer") int playerTwo){


            Game game = gameManager.createGame(playerOne, playerTwo);

            return game;


    }
}
