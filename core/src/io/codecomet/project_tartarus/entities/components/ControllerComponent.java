package io.codecomet.project_tartarus.entities.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

import java.util.HashMap;

public class ControllerComponent implements Component, Pool.Poolable {

    public HashMap<Integer, Boolean> keysPressed = new HashMap<>();

    @Override
    public void reset() {
        keysPressed.clear();
    }
}
