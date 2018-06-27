package com.company.BasicDOD;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulator {

    private static final Random rand = new Random();
    private final ParticleMovementSystem movementSystem;

    private List<ParticleData> particles = new ArrayList<>();

    Simulator(int particleCount) {
        movementSystem = new ParticleMovementSystem();
        addParticles(particleCount);
    }

    public void run() {
        long iterCount = 0;
        long time = getTime();
        long freshTime;
        while (true) {

            simulate();

            freshTime = getTime();
            System.out.println(String.format("[ iteration: %s | deltaTime: %s  | totalTime: %s ]", iterCount, freshTime - time, time));
            time = freshTime;
            iterCount++;
        }
    }

    private void simulate() {
        for (int i = 0; i < particles.size(); i++) {
            // it is faster to update each particle via mutation
            movementSystem.updateMutate(particles.get(i).position, particles.get(i).velocity);

            // the functional construction leads to a roughly 2x increase in deltaTime
            //particles.set(i, movementSystem.updateFunctional(particles.get(i).position, particles.get(i).velocity));
        }
    }

    //region Misc Supporting code
    private long getTime() {
        return System.currentTimeMillis();
    }

    private void addParticles(int count) {
        for (int i = 0; i < count; i++) {
            Vector2 position = getRandomVector(1f);
            Vector2 velocity = getRandomVector(rand.nextInt(5));

            particles.add(new ParticleData(position, velocity));
        }
    }

    private Vector2 getRandomVector(float scalar) {
        return new Vector2(rand.nextFloat() * scalar, rand.nextFloat() * scalar);
    }
    //endregion
}