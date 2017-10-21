package com.gameOfThrones.sdk.services;

import com.gameOfThrones.model.RecommendationForSerial;
import com.gameOfThrones.model.User;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Evegeny on 19/12/2016.
 */
@Service
@PropertySource("classpath:location.properties")
public class UserSdkService {
    private RestTemplate restTemplate = new RestTemplate();
    @Value("${userServiceHome}")
    private String home;


    public User findPerson(int id) {
        return restTemplate.getForObject(home + "/users/" + id, User.class);
    }

    public User findPersonByName(String name) {
        return restTemplate.getForObject(home + "/users/search/findByName?name=" + name, User.class);
    }

    @SneakyThrows
    public String saveUser(User user) {
        Map<String, Object> map = new HashMap<>();

        Field[] fields = user.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(user));
        }

        return restTemplate.postForObject(home + "/users/", map, String.class);
    }

    public RecommendationForSerial countUsersWhichRecommends(String serialName) {
        return restTemplate.getForObject(home + "/users/recommendation/count/serial/" +serialName,RecommendationForSerial.class);
    }



}
