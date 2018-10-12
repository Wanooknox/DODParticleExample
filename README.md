# DODParticleExample

Just a quick example of some very basic particle behavior. Compares the performance of an OOP flow vs a DOD flow. Java 1.8.

You can see my presentation here: https://www.youtube.com/watch?v=AxprodpsoYk

# Quick Breakdown
### Object-Oriented Programming Demo

The Object-Oriented Programming demo illustrates a simple particle implementation using common OOP practises. Each `Particle` object has instance fields and methods together. In each iteration we loop over the particles and call the `update()` method.

Launch [BasicOOPDemo.java](src/com/company/BasicOOP/BasicOOPDemo.java) to see it run.

### Data-Oriented Design Demo

The Data-Oriented Design demo illustrates a simple particle implementation using a more data-oriented pattern. `Particle` objects are replaced with `ParticleData`, which strips away the `update()` method. Updates are instead handled by `ParticleMovementSystem`.

Launch [BasicDODDemo.java](src/com/company/BasicDOD/BasicDODDemo.java) to see it run.

