# DODParticleExample

Just a quick example of some very basic particle behavior. Compares the performance of an OOP flow vs a DOD flow. Java 1.8.

You can see my presentation here: https://www.youtube.com/watch?v=AxprodpsoYk

I've included a [maven pom.xml](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html#The_POM) for quick project setup. Build with:
```
> mvn package
```

# Quick Breakdown
### Object-Oriented Programming Demo

The Object-Oriented Programming demo illustrates a simple particle implementation using common OOP practises. Each `Particle` object has instance fields and methods together. In each iteration we loop over the particles and call the `update()` method.<sup>1</sup>

Launch [BasicOOPDemo.java](src/com/company/BasicOOP/BasicOOPDemo.java) to see it run.

### Data-Oriented Design Demo

The Data-Oriented Design demo illustrates a simple particle implementation using a more data-oriented pattern. `Particle` objects are replaced with `ParticleData`, which strips away the `update()` method. Updates are instead handled by `ParticleMovementSystem`.

Launch [BasicDODDemo.java](src/com/company/BasicDOD/BasicDODDemo.java) to see it run.

### Threaded Data-Oriented Design Demo

The Threaded demo illustrates the same basic implementation as the Data-Oriented Design demo, with the addition of multi-threading. The threads are fed subdivisions of the main array of particles, rather than splitting off multiple new arrays, which avoids time wasted on extra allocation of memory. This version should lead to a roughly 10x (YMMV) speed increase. 

Launch [ThreadedDODDemo.java](src/com/company/BasicDOD/ThreadedDOD/ThreadedDODDemo.java) to see it run.

###### Footnotes
<sup>1</sup> For extra fun, open [Particle.java](src/com/company/BasicOOP/Particle.java) and comment out the extra 'fat' data. This data is NOT used by the logic and removing it doubles the performance on my laptop. See line 11: `private SaturatedFat fattener = new SaturatedFat();`