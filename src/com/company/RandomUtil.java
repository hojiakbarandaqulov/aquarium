package com.company;

import java.util.Random;

public class RandomUtil {
    public static Random random = new Random();

    public static int getRandom(int n) { // 2  ->  [0,1]
        return random.nextInt(n); // [0,1,2,3,4,....n-1]
    }

    public static int getRandom(int a, int b) {
        return random.nextInt(a, b); // [a,a+1,a+2,a+3,a+4,....b)
    }
}
