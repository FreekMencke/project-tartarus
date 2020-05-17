package io.codecomet.project_tartarus.input;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.InputAdapter;
import io.codecomet.project_tartarus.entities.components.ControllerComponent;

public class ControllerInputAdapter extends InputAdapter {

    private final ComponentMapper<ControllerComponent> controllerMap = ComponentMapper.getFor(ControllerComponent.class);

    private final PooledEngine engine;
    private final Family family;

    public ControllerInputAdapter(PooledEngine engine) {
        this.engine = engine;
        this.family = Family.all(ControllerComponent.class).get();
    }

    @Override
    public boolean keyDown(int keycode) {
        ImmutableArray<Entity> entities = engine.getEntitiesFor(family);

        entities.forEach(entity -> controllerMap.get(entity).keysPressed.put(keycode, true));

        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        ImmutableArray<Entity> entities = engine.getEntitiesFor(family);

        entities.forEach(entity -> controllerMap.get(entity).keysPressed.put(keycode, false));

        return super.keyDown(keycode);
    }

}
