package com.freekmencke.tartarus.entities.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class CameraComponent implements Component, Pool.Poolable {

    public boolean enabled = true;

    @Override
    public void reset() {
        enabled = true;
    }
}