package com.company;

public class FishFactory {

    public static Fish creatFish() {
        Gender gender = randomGender();
        int age = RandomUtil.getRandom(10, 15);
        int x = RandomUtil.getRandom(Aquarium.width + 1);
        int y = RandomUtil.getRandom(Aquarium.height + 1);
        Fish fish = new Fish(gender, age, x, y);
        return fish;
    }

    public static Gender randomGender() {
        int randomNumber = RandomUtil.getRandom(2);
        if (randomNumber == 0) {
            return Gender.FEMALE;
        } else {
            return Gender.MALE;
        }
    }

}
