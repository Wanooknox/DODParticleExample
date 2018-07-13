# DODParticleExample

Just a quick example of some very basic particle behavior. Compares the performance of an OOP flow vs a DOD flow. Java 1.8.

You can see my presenation here: https://www.youtube.com/watch?v=AxprodpsoYk

I've just added the file [threading_v2.patch](threading_v2.patch) which contains some code changes to multi-thread the DOD demo. This seems to push the over all performance 10x faster (YMMV). Apply that patch to your local copy if you're interested in the threading potential.
