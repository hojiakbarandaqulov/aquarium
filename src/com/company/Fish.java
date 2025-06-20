package com.company;

public class Fish implements Runnable {
    String gender;
    String name;
    int lifeSpan;
    private boolean busy = false;

    public Fish(String gender, int id) {
        this.gender = gender;
        this.name = gender + "Fish#" + id;
        this.lifeSpan = Aquarium.random.nextInt(10) + 5; // lifespan: 5 - 14 seconds
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    @Override
    public void run() {
        System.out.println(name + " born. Gender: " + gender + ", LifeSpan: " + lifeSpan + "s");
        try {
            Thread.sleep(lifeSpan * 1000L);
            System.out.println(name + " has died after living for " + lifeSpan + " seconds.");
        } catch (InterruptedException e) {
            System.out.println(name + " interrupted.");
        }
    }

    public boolean isAlive() {
        // Simple way to simulate if the thread is still alive: check remaining lifeSpan
        return true;
    }
}

