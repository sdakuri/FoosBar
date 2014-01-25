package com.dakuris.foosbar.controller;

import com.dakuris.foosbar.base.Player;
import com.dakuris.foosbar.manager.PlayerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: Shashidhar Dakuri
 * Email: shashi.dakuri@solutionset.com
 * Date: 1/24/14
 * Time: 2:13 PM
 */

@Controller
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private PlayerManager playerManager;

    @RequestMapping(value="/register", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody void registerPlayer(@RequestBody Player player){
        playerManager.createPlayer(player.getFirstName(), player.getLastName());
    }

    @RequestMapping(value="/get/{id}", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody Player getPlayer(@PathVariable int id){
        return playerManager.getPlayer(id);
    }
}
