package io.codecomet.project_tartarus.map.sector;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public abstract class Sector implements Disposable {

    protected final PooledEngine engine;
    protected final World world;

    protected Vector3 position = Vector3.Zero.cpy();

    public final ImmutableArray<Entity> entities;

    public Sector(PooledEngine engine, World world) {
        this.engine = engine;
        this.world = world;
        this.entities = new ImmutableArray<>(createEntities());
    }

    public Sector(PooledEngine engine, World world, Vector3 position) {
        this.engine = engine;
        this.world = world;
        this.position = position;
        this.entities = new ImmutableArray<>(createEntities());
    }

    public void load() {
        this.entities.forEach(engine::addEntity);
    }
    public void unload() {
        this.entities.forEach(engine::removeEntity);
    }

    protected abstract Array<Entity> createEntities();

    @Override
    public void dispose() {
        this.unload();
    }

}
