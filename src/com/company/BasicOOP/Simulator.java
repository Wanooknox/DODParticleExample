package com.company.BasicOOP;

import com.company.DemoCommon.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulator {

    private static final Random rand = new Random();

    private List<Particle> particles = new ArrayList<>();

    Simulator(int particleCount) {
        addParticles(particleCount);
    }

    public void run() {
        long iterCount = 0;
        float avgDeltaTime = 0.0f;
        long time = getTime();
        long freshTime;
        while (true) {

            for (Particle particle : particles) {
                particle.update();
            }

            freshTime = getTime();
            avgDeltaTime = getAvgDeltaTime(avgDeltaTime, time, freshTime, iterCount);
            System.out.println(String.format("[ iteration: %s | deltaTime: %s | avgDeltaTime: %s ]", iterCount, freshTime - time, avgDeltaTime));
            time = freshTime;
            iterCount++;
        }

    }

    //region Misc Supporting code
    private float getAvgDeltaTime(float avgDeltaTime, long time, long freshTime, long iterCount) {
        if (avgDeltaTime > 0.001f) {
            return (((iterCount-1)*avgDeltaTime ) + (freshTime - time)) / iterCount;
        } else {
            return freshTime - time;
        }
    }

    private long getTime() {
        return System.currentTimeMillis();
    }

    private void addParticles(int count) {
        for (int i = 0; i < count; i++) {
            Vector2 position = getRandomVector(1f);
            Vector2 velocity = getRandomVector(rand.nextInt(5));

            particles.add(new Particle(position, velocity));
        }
    }

    private Vector2 getRandomVector(float scalar) {
        return new Vector2(rand.nextFloat() * scalar, rand.nextFloat() * scalar);
    }
    //endregion
}
