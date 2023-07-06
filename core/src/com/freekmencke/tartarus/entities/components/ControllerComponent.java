package com.freekmencke.tartarus.entities.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class ControllerComponent implements Component, Pool.Poolable {

    public Vector2 direction = new Vector2();
    public Vector2 touchPosition = new Vector2();
    public boolean isTouching = false;

    @Override
    public void reset() {
        direction.set(0, 0);
        touchPosition.set(0, 0);
        isTouching = false;
    }
}
