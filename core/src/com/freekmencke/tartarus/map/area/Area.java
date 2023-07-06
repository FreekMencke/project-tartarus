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

    protected final PooledEngine engine;
    protected final World world;

    private final ComponentMapper<BodyComponent> bodyMap = ComponentMapper.getFor(BodyComponent.class);

    public float rotation;
    public Vector2 position;

    private ImmutableArray<Entity> entities = new ImmutableArray<>(new Array<>());

    protected Area(PooledEngine engine, World world, Vector2 position) {
        this.engine = engine;
        this.world = world;
        this.position = position;
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

    protected abstract Vector2 applyTransform(Vector2 position);

    @Override
    public void dispose() {
        this.unload();
    }

}
