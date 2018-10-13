package com.company.BasicOOP;

import com.company.DemoCommon.SaturatedFat;
import com.company.DemoCommon.Vector2;

public class Particle {

    private Vector2 position;
    private Vector2 velocity;

    private SaturatedFat fattener = new SaturatedFat();

    public Particle(Vector2 position, Vector2 velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public void update() {
        Move();
    }

    private void Move() {
        position = position.add(velocity);
    }
}
