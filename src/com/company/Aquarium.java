package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class    Aquarium {
    static final Random random = new Random();
    private static final List<Fish> fishes = Collections.synchronizedList(new ArrayList<>());
    private static final ExecutorService executor = Executors.newCachedThreadPool();
    private static final Object lock = new Object();

    public static void main(String[] args) {
        int maleCount = random.nextInt(5) + 1; // 1-5
        int femaleCount = random.nextInt(5) + 1; // 1-5

        System.out.println("Starting Aquarium with " + maleCount + " male and " + femaleCount + " female fishes.\n");

        // Add male fishes
        for (int i = 0; i < maleCount; i++) {
            Fish fish = new Fish("Male ", i + 1);
            fishes.add(fish);
            executor.submit(fish);
        }

        // Add female fishes
        for (int i = 0; i < femaleCount; i++) {
            Fish fish = new Fish("Female ", i + 1);
            fishes.add(fish);
            executor.submit(fish);
        }

        // Watcher Thread for mating
        new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    Fish male = null;
                    Fish female = null;

                    for (Fish f : fishes) {
                        if (!f.isAlive()) continue;
                        if (f.gender.equals("Male") && !f.isBusy()) {
                            male = f;
                        } else if (f.gender.equals("Female") && !f.isBusy()) {
                            female = f;
                        }
                        if (male != null && female != null) break;
                    }

                    if (male != null && female != null) {
                        male.setBusy(true);
                        female.setBusy(true);
                        System.out.println("\n>> " + male.name + " and " + female.name + " are mating...");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        String gender = random.nextBoolean() ? "Male" : "Female";
                        Fish baby = new Fish(gender, fishes.size() + 1);
                        fishes.add(baby);
                        executor.submit(baby);
                        System.out.println(">> New baby fish born! Name: " + baby.name + ", Gender: " + baby.gender);

                        male.setBusy(false);
                        female.setBusy(false);
                    }
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
