package io.codecomet.project_tartarus.input;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ObjectMap;
import io.codecomet.project_tartarus.entities.components.ControllerComponent;

public class ControllerInputAdapter extends InputAdapter {

    private final ComponentMapper<ControllerComponent> controllerMap = ComponentMapper.getFor(ControllerComponent.class);
    private final ObjectMap<Integer, Boolean> keysPressed = new ObjectMap<>();

    private final PooledEngine engine;
    private final Family family;

    public ControllerInputAdapter(PooledEngine engine) {
        this.engine = engine;
        this.family = Family.all(ControllerComponent.class).get();
    }

    @Override
    public boolean keyDown(int keycode) {
        engine.getEntitiesFor(family)
            .forEach(entity -> {
                keysPressed.put(keycode, true);
                updateDirection(entity);
            });

        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        engine.getEntitiesFor(family)
            .forEach(entity -> {
                keysPressed.put(keycode, false);
                updateDirection(entity);
            });
        return super.keyDown(keycode);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        engine.getEntitiesFor(family)
            .forEach(e -> updateMousePosition(e, screenX, screenY, true));

        return super.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        engine.getEntitiesFor(family)
            .forEach(e -> updateMousePosition(e, screenX, screenY, true));

        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        ImmutableArray<Entity> entities = engine.getEntitiesFor(family);

        entities.forEach(e -> updateMousePosition(e, screenX, screenY, false));

        return super.touchUp(screenX, screenY, pointer, button);
    }

    private void updateMousePosition(Entity e, float x, float y, boolean isTouching) {
        ControllerComponent c = controllerMap.get(e);

        c.touchPosition.set(x, y);
        c.isTouching = isTouching;
    }

    private void updateDirection(Entity e) {
        ControllerComponent c = controllerMap.get(e);

        Vector2 direction = new Vector2();

        // TODO: make configurable keymap
        if (keysPressed.get(Input.Keys.W, false)) direction.add(0, 1);
        if (keysPressed.get(Input.Keys.D, false)) direction.add(1, 0);
        if (keysPressed.get(Input.Keys.S, false)) direction.sub(0, 1);
        if (keysPressed.get(Input.Keys.A, false)) direction.sub(1, 0);

        c.direction.set(direction);
    }

}
