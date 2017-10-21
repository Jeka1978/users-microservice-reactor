package com.gameOfThrones.services;

import com.gameOfThrones.dao.UserDao;
import com.gameOfThrones.model.RecommendationForSerial;
import com.gameOfThrones.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Evegeny on 20/12/2016.
 */
@Service
public class RecommendationServiceImpl implements RecommendationService {


    @Autowired
    private UserDao userDao;
    @Override
    public boolean isSerialRecommended(User user, String nameOfTheSerial, int similarity) {
        List<User> users = userDao.findAll();
        int similarUsersLikes=0;
        int similarUsersDoesntLike=0;

        for (User currentUser : users) {
            if (user.isSimilar(currentUser, similarity)) {
                if (currentUser.getRecommendations().contains(nameOfTheSerial)) {
                    similarUsersLikes++;
                }else {
                    similarUsersDoesntLike++;
                }
            }
        }
        return similarUsersLikes>similarUsersDoesntLike;
    }


    @Override
    public RecommendationForSerial countRecommendations(String nameOfTheSerial) {
        long totalUsers = userDao.count();
        int positiveUsers = userDao.findByRecommendationsContains(nameOfTheSerial).size();
        return RecommendationForSerial.builder().possitiveUsers(positiveUsers).totalUsers(totalUsers).build();
    }

    @Override
    public User findUser(String name) {
        return userDao.findByName(name);
    }


}







