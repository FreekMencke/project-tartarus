package io.codecomet.project_tartarus.entities.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

import java.util.HashMap;

public class ControllerComponent implements Component, Pool.Poolable {

    public HashMap<Integer, Boolean> keysPressed = new HashMap<>();
    public Vector2 touchPosition = new Vector2();
    public boolean isTouching = false;

    @Override
    public void reset() {
        keysPressed.clear();
        touchPosition.set(0, 0);
        isTouching = false;
    }
}
