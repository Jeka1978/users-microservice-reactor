package com.gameOfThrones.controllers;

import com.gameOfThrones.model.User;
import com.gameOfThrones.model.RecommendationForSerial;
import com.gameOfThrones.services.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Evegeny on 20/12/2016.
 */
@RestController
@RequestMapping("/users/recommendation/")
public class UserController {
    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/count/serial/{serialName}")
    public RecommendationForSerial countUsersWhichRecommends(@PathVariable String serialName) {
        return recommendationService.countRecommendations(serialName);
    }

    @GetMapping("/serial/{serialName}/{similarity}/{userName}")
    public String isRecommended(@PathVariable("serialName") String serialName, @PathVariable("similarity") int similarity, @PathVariable("userName") String userName) {
        User user = recommendationService.findUser(userName);
        return String.valueOf(recommendationService.isSerialRecommended(user, serialName, similarity));
    }
}
