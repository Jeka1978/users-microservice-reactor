package com.gameOfThrones.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Evegeny on 21/12/2016.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationForSerial {
    private int possitiveUsers;
    private long totalUsers;
}
