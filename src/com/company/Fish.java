package com.company;

public class Fish extends Thread {
    private Gender gender;
    private int age;
    private int x;
    private int y;

    public Fish(Gender gender, int age, int x, int y) {
        this.gender = gender;
        this.age = age;
        this.x = x;
        this.y = y;
    }

    @Override
    public void run() {
        while (age != 0) {
            move();
            Aquarium.collision(this);
            age--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        Aquarium.remove(this);
    }

    public void move() {
        int direction = RandomUtil.getRandom(4); // 0-up,1-right,2-bottom,3-left
        if (direction == 0 && y < Aquarium.height) { // up
            y++;
        } else if (direction == 1 && x < Aquarium.width) {
            x++;
        } else if (direction == 2 && y > 0) {
            y--;
        } else if (direction == 3 && x > 0) {
            x--;
        }
    }

    @Override
    public String toString() {
        return "Fish{" +
                "name=" + getName() +
                ", gender=" + gender +
                ", age=" + age +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public boolean crush(Fish fish) {
        return this.x == fish.x && this.y == fish.y && this.gender != fish.gender;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
