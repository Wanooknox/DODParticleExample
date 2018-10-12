package com.company.ThreadedDOD;

import com.company.DemoCommon.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Simulator {

    private static final int numThreads = 64;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
    private static final Random rand = new Random();
    private final ParticleMovementSystem movementSystem;
    private final int particleCount;

    private List<ParticleData> particles;

    Simulator(int particleCount) {
        movementSystem = new ParticleMovementSystem();
        this.particleCount = particleCount;
        particles = new ArrayList<>(this.particleCount);
        addParticles(this.particleCount);
    }

    public void run() {
        long iterCount = 0;
        double avgDeltaTime = 0.0f;
        double time = getTime();
        double freshTime;
        while (true) {

            simulate();

            freshTime = getTime();
            avgDeltaTime = getAvgDeltaTime(avgDeltaTime, time, freshTime, iterCount);
            if (iterCount % 1000 == 0) {
                System.out.println(String.format("[ iteration: %s | deltaTime: %s | avgDeltaTime: %s ]", iterCount, freshTime - time, avgDeltaTime));
            }
            time = freshTime;
            iterCount++;
        }
    }

    private void simulate() {
        int chunkSize = (particleCount) / numThreads;
        List<Callable<Void>> futures = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            final int start = chunkSize * i;
            final int end = start + chunkSize - 1;
            final List<ParticleData> subparticles = particles.subList(start, end);

            final int finalI = i;
            Callable<Void> runnable = () -> {
                final int chunkStart = chunkSize * finalI;
                final int chunkEnd = chunkStart + chunkSize - 1;

                for (int j = chunkStart; j < chunkEnd; j++) {
                    ParticleData particleData = subparticles.get(j);
                    movementSystem.update(
                            particleData.position,
                            particleData.velocity);
                }
                return null;
            };

            futures.add(runnable);
        }

        try {
            List<Future<Void>> futures1 = executorService.invokeAll(futures);
            for (Future<Void> voidFuture : futures1) {
                voidFuture.get();
            }

        } catch (InterruptedException | ExecutionException ignore) {

        }
    }

    //region Misc Supporting code
    private double getAvgDeltaTime(double avgDeltaTime, double time, double freshTime, long iterCount) {
        if (avgDeltaTime > 0.001f) {
            return (((iterCount-1)*avgDeltaTime ) + (freshTime - time)) / iterCount;
        } else {
            return freshTime - time;
        }
    }

    private double getTime() {
        return System.nanoTime() / 1_000_000d;
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
