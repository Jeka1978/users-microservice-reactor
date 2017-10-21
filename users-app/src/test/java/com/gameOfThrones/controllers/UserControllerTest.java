package com.gameOfThrones.controllers;

import com.gameOfThrones.AppConfig;
import com.gameOfThrones.contsants.Priorities;
import com.gameOfThrones.dao.UserDao;
import com.gameOfThrones.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.gameOfThrones.contsants.Priorities.ACTION;
import static com.gameOfThrones.contsants.Priorities.DRAMA;
import static com.gameOfThrones.contsants.Serials.GAME_OF_THRONES;
import static com.gameOfThrones.contsants.Serials.LOST;
import static com.gameOfThrones.contsants.Serials.PRISON_BREAK;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Evegeny on 21/12/2016.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ContextConfiguration(classes = TestConfig.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDao userDao;

    private List<User> users;


    @Before
    public void setUp() {
        users = new ArrayList<>();
        users.add(User.builder().recommendation(GAME_OF_THRONES).recommendation(LOST).priority(DRAMA, 100).priority(ACTION, 20).build());
        users.add(User.builder().recommendation(LOST).recommendation(GAME_OF_THRONES).recommendation(PRISON_BREAK).priority(DRAMA, 90).priority(ACTION, 60).build());
        users.add(User.builder().recommendation(LOST).recommendation(GAME_OF_THRONES).priority(DRAMA, 100).priority(ACTION, 20).build());
        when(userDao.findByRecommendationsContains(anyString())).thenReturn(users);
        when(userDao.findAll()).thenReturn(users);

    }

    @Test
    public void countUsersWhichRecommends() throws Exception {
        when(userDao.count()).thenReturn((long) users.size());
        mockMvc.perform(
                get("/users/recommendation/count/serial/" + GAME_OF_THRONES)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.possitiveUsers", equalTo(3)))
                .andExpect(jsonPath("$.totalUsers", equalTo(3)));


    }

    @Test
    public void isRecommended() throws Exception {
        User user = User.builder().priority(DRAMA, 80).priority(ACTION, 60).build();
        when(userDao.findByName(anyString())).thenReturn(user);
        mockMvc.perform(
                get("/users/recommendation//serial/" + GAME_OF_THRONES + "/66/jeka")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("true")));
        mockMvc.perform(
                get("/users/recommendation//serial/" + PRISON_BREAK + "/66/jeka"))
                .andExpect(content().string(containsString("false")));

        mockMvc.perform(
                get("/users/recommendation//serial/" + PRISON_BREAK + "/75/jeka"))
                .andExpect(content().string(containsString("true")));

    }

}