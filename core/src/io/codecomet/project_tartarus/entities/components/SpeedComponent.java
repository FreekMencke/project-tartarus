package io.codecomet.project_tartarus.entities.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class SpeedComponent implements Component, Pool.Poolable {

    public float dampening = 10; // TODO: default velocity dampening.
    public Vector2 speed = Vector2.Zero;

    @Override
    public void reset() {
        dampening = 10; // TODO: default velocity dampening.
        speed = Vector2.Zero;
    }

}
