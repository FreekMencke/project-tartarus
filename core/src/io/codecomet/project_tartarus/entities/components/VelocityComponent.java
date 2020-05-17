package io.codecomet.project_tartarus.entities.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class VelocityComponent implements Component, Pool.Poolable {

    public float dampening = 10; // TODO: default velocity dampening.
    public Vector2 speed = new Vector2();

    @Override
    public void reset() {
        dampening = 10; // TODO: default velocity dampening.
        speed.set(0, 0);
    }

}
