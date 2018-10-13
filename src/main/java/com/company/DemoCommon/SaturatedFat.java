package com.company.DemoCommon;

import java.util.Random;

public class SaturatedFat {

    public String thing1;
    public boolean thing2;
    public long thing3;
    public double thing4;
    public float[] thing5;

    private Random rand;

    public SaturatedFat() {
        rand = new Random();

        int arrayCount = rand.nextInt(100);

        this.thing1 = "this is words";
        this.thing2 = rand.nextBoolean();
        this.thing3 = rand.nextLong();
        this.thing4 = rand.nextDouble();
        this.thing5 = new float[arrayCount];

        for (int i = 0; i < arrayCount; i++) {
            this.thing5[i] = rand.nextFloat();
        }
    }
}
