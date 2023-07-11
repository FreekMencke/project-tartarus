package com.freekmencke.tartarus.entities.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class VelocityComponent implements Component, Pool.Poolable {

    public static final float DEFAULT_SPEED = 12;  // speed * 50 N
    public static final float DEFAULT_SPEED_DAMPENING = 10; // 10m/s
    public static final float DEFAULT_ANGULAR_DAMPENING = 2; // 1 Rad/s

    public float currentSpeed = 0;
    public float speed = DEFAULT_SPEED;
    public float angularDampening = DEFAULT_ANGULAR_DAMPENING;
    public float speedDampening = DEFAULT_SPEED_DAMPENING;

    @Override
    public void reset() {
        currentSpeed = 0;
        speed = DEFAULT_SPEED;
        angularDampening = DEFAULT_ANGULAR_DAMPENING;
        speedDampening = DEFAULT_SPEED_DAMPENING;
    }

}
