package com.company.BasicDOD;

import com.company.DemoCommon.Vector2;

public class ParticleData {

    public Vector2 position;
    public Vector2 velocity;

//    private SaturatedFat fattener = new SaturatedFat();

    public ParticleData(Vector2 position, Vector2 velocity) {
        this.position = position;
        this.velocity = velocity;
    }

}
