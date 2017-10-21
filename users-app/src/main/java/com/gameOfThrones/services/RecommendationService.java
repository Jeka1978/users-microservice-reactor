package com.gameOfThrones.services;

import com.gameOfThrones.model.RecommendationForSerial;
import com.gameOfThrones.model.User;

/**
 * Created by Evegeny on 20/12/2016.
 */
public interface RecommendationService {
    boolean isSerialRecommended(User user, String nameOfTheSerial, int similarity);

    RecommendationForSerial countRecommendations(String nameOfTheSerial);

    User findUser(String name);

}
