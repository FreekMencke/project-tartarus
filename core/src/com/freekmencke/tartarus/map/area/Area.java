package com.freekmencke.tartarus.map.area;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.freekmencke.tartarus.entities.components.BodyComponent;

public abstract class Area implements Disposable {

    private final ComponentMapper<BodyComponent> bodyMap = ComponentMapper.getFor(BodyComponent.class);

    protected final PooledEngine engine;
    protected final World world;

    public float rotation;
    public Vector2 position;

    private ImmutableArray<Entity> entities = new ImmutableArray<>(new Array<>());

    protected Area(PooledEngine engine, World world, Vector2 position, float rotation) {
        this.engine = engine;
        this.world = world;
        this.position = position;
        this.rotation = rotation;
    }

    public void load() {
        entities = new ImmutableArray<>(createObjects());
        entities.forEach(engine::addEntity);
    }
    public void unload() {
        entities.forEach(e -> world.destroyBody(bodyMap.get(e).body));
        entities.forEach(engine::removeEntity);
    }

    protected abstract Array<Entity> createObjects();

    protected Vector2 applyAreaTransformToEntity(Vector2 position) {
        return position.rotateDeg(this.rotation) // rotate entity around origin.
                .add(.5f, .5f) // offset .5f since area size is always 1
                .add(this.position); // add absolute area position
    }

    @Override
    public void dispose() {
        this.unload();
    }

}
