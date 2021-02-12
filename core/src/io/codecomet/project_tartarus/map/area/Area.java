package io.codecomet.project_tartarus.map.area;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import io.codecomet.project_tartarus.entities.components.BodyComponent;
import io.codecomet.project_tartarus.map.sector.Sector;

public abstract class Area implements Disposable {

    protected final PooledEngine engine;
    protected final World world;

    private final ComponentMapper<BodyComponent> bodyMap = ComponentMapper.getFor(BodyComponent.class);

    protected Vector2 position;
    protected float rotation;

    protected ImmutableArray<Sector> areas = new ImmutableArray<>(new Array<>());
    protected ImmutableArray<Entity> walls = new ImmutableArray<>(new Array<>());

    public Area(PooledEngine engine, World world, Vector2 position, float rotation) {
        this.engine = engine;
        this.world = world;
        this.position = position;
        this.rotation = rotation;
    }

    public void load() {
        areas = new ImmutableArray<>(createAreas());
        areas.forEach(Sector::load);

        walls = new ImmutableArray<>(createWalls());
        walls.forEach(engine::addEntity);
    }
    public void unload() {
        areas.forEach(Sector::unload);

        walls.forEach(e -> world.destroyBody(bodyMap.get(e).body));
        walls.forEach(engine::removeEntity);
    }

    public abstract Rectangle getBoundingBox();

    protected abstract Array<Sector> createAreas();
    protected abstract Array<Entity> createWalls();

    protected abstract Vector2 applyTransform(Vector2 position);

    @Override
    public void dispose() {
        unload();
    }

}
