package com.gameOfThrones.dao;

import com.gameOfThrones.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Evegeny on 11/12/2016.
 */
public interface UserDao extends MongoRepository<User,Integer> {

    @RestResource(path ="byAge")
    List<User> findByAgeGreaterThan(@Param("age") int age);


    User findByName(@Param("name") String name);

    @RestResource(exported = false)
    List<User> findByRecommendationsContains(String nameOfSerial);


}
