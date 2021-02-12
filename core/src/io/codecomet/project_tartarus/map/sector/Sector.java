package io.codecomet.project_tartarus.map.sector;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import io.codecomet.project_tartarus.entities.components.BodyComponent;

public abstract class Sector implements Disposable {

    protected final PooledEngine engine;
    protected final World world;

    private final ComponentMapper<BodyComponent> bodyMap = ComponentMapper.getFor(BodyComponent.class);

    protected Vector2 position;
    protected float rotation;

    private ImmutableArray<Entity> entities = new ImmutableArray<>(new Array<>());

    public Sector(PooledEngine engine, World world, Vector2 position, float rotation) {
        this.engine = engine;
        this.world = world;
        this.position = position;
        this.rotation = rotation;
    }

    public void load() {
        entities = new ImmutableArray<>(createEntities());
        entities.forEach(engine::addEntity);
    }
    public void unload() {
        entities.forEach(e -> world.destroyBody(bodyMap.get(e).body));
        entities.forEach(engine::removeEntity);
    }

    protected abstract Array<Entity> createEntities();

    protected abstract Vector2 applyTransform(Vector2 position);

    @Override
    public void dispose() {
        this.unload();
    }

}
