package com.project.UBER.TravelApp.utils;

import java.util.concurrent.ThreadLocalRandom;

public class DistanceCalculator {

    public static double estimateDistance(String  pickup, String drop){
        int diff = Math.abs((pickup == null ? 0 : pickup.length())- (drop== null ? 0 : drop.length()));

        int random = ThreadLocalRandom.current().nextInt(1,6);
        return Math.max(0.5, diff + random);
    }
}
