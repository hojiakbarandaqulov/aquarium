package com.company;

import java.util.LinkedList;
import java.util.List;

public class Aquarium {
    public static final int width = 5; // x axis
    public static final int height = 5; // y axis
    public static int MAX_NUMBER = (width + 1) * (height + 1); // y axis

    private static List<Fish> fishList = new LinkedList<>();

    public void start() {
        int fishCount = RandomUtil.getRandom(5, 10);
        for (int i = 0; i < fishCount; i++) {
            Fish fish = FishFactory.creatFish();
            fish.start();
            fishList.add(fish);
        }
    }

    public static synchronized void collision(Fish current){
        if (fishList.size() >= MAX_NUMBER) {
            return;
        }
        for (Fish fish : fishList) {
            if (current.crush(fish)) { //
                Fish babyFish = FishFactory.creatFish();
                System.out.println("Collision " + current.getName() + " + " + fish.getName() + " = " + babyFish.getName());
                babyFish.start();
                fishList.add(babyFish);
                printStatistics();
                break;
            }
        }
    }

    public static synchronized void remove(Fish fish) {
        fishList.remove(fish);
        System.out.println("RIP: " + fish.getName());
        printStatistics();
    }

    public static void printStatistics() {
        int male = 0, female = 0;
        int totalCount = fishList.size();
        for (Fish fish : fishList) {
            if (fish.getGender().equals(Gender.MALE)) {
                male++;
            } else {
                female++;
            }
        }
        System.out.println("-------------------------------------");
        System.out.println("Total fish: " + totalCount + ", male = " + male + ", female = " + female);
    }
}
