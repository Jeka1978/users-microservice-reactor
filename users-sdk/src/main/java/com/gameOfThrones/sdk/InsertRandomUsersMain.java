package com.gameOfThrones.sdk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameOfThrones.model.User;
import com.gameOfThrones.sdk.services.UserSdkService;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;

import static com.gameOfThrones.contsants.Priorities.GENRES;
import static com.gameOfThrones.contsants.Serials.All_SERIALS;

/**
 * Created by Evegeny on 11/12/2016.
 */
public class InsertRandomUsersMain {

    public static void main(String[] args) throws JsonProcessingException {

        DataFactory dataFactory = new DataFactory();
        Random random = new Random();


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.gameOfThrones.sdk");
        UserSdkService userSdkService = context.getBean(UserSdkService.class);
        for (int i = 0; i < 20; i++) {
            User.UserBuilder builder = User.builder();
            builder.age(dataFactory.getNumberBetween(10, 60)).name(dataFactory.getName());
            for (String genre : GENRES) {
                builder.priority(genre, dataFactory.getNumberBetween(1, 100));
            }
            int numberOfRecommendations = random.nextInt(All_SERIALS.size());
            for (int j = 0; j <= numberOfRecommendations; j++) {
                builder.recommendation(All_SERIALS.get(j));
            }
            User user = builder.build();
            userSdkService.saveUser(user);
            ObjectMapper mapper = new ObjectMapper();
            String s = mapper.writeValueAsString(user);
            System.out.println(s);
        }

    }
}

