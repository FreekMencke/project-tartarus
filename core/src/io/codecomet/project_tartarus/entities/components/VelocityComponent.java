package io.codecomet.project_tartarus.entities.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class VelocityComponent implements Component, Pool.Poolable {

    public static final float DEFAULT_SPEED = 2;  // 2m/s => 7.2km/h
    public static final float DEFAULT_SPEED_DAMPENING = 10; // 10m/s
    public static final float DEFAULT_ROTATION_DAMPENING = 2; // 1 Rad/s

    public float angularDampening = DEFAULT_ROTATION_DAMPENING;
    public float speedDampening = DEFAULT_SPEED_DAMPENING;
    public float speed = DEFAULT_SPEED;

    @Override
    public void reset() {
        angularDampening = DEFAULT_ROTATION_DAMPENING;
        speedDampening = DEFAULT_SPEED_DAMPENING;
        speed = DEFAULT_SPEED;
    }

}
