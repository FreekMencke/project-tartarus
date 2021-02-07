package io.codecomet.project_tartarus.map.area;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import io.codecomet.project_tartarus.map.sector.Sector;

public abstract class Area implements Disposable {

    protected final PooledEngine engine;
    protected final World world;

    protected Vector3 position = Vector3.Zero.cpy();
    public final ImmutableArray<Sector> areas;

    public Area(PooledEngine engine, World world) {
        this.engine = engine;
        this.world = world;
        areas = new ImmutableArray<>(createAreas());
    }

    public Area(PooledEngine engine, World world, Vector3 position) {
        this.engine = engine;
        this.world = world;
        this.position = position;
        this.areas = new ImmutableArray<>(createAreas());
    }

    public void load() {
        this.areas.forEach(Sector::load);
    }
    public void unload() {
        this.areas.forEach(Sector::load);
    }

    protected abstract Array<Sector> createAreas();

    @Override
    public void dispose() {
        unload();
    }

}
