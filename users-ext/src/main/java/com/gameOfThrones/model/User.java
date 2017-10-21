package com.gameOfThrones.model;

import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Evegeny on 11/12/2016.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;

    private int age;

    private String name;


    @Singular
    private Map<String,Integer> priorities;

    @Singular
    private List<String> recommendations;


    public boolean isSimilar(User currentUser, int similarity) {
        double totalDifference=0.0;
        for (String priority : priorities.keySet()) {
            totalDifference+=Math.abs(priorities.get(priority) - currentUser.priorities.get(priority));
        }
        int possibleDifference = 100 - similarity;
        return totalDifference/priorities.size()<possibleDifference;
    }
}
