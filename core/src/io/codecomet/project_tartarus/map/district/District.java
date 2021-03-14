package io.codecomet.project_tartarus.map.district;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import io.codecomet.project_tartarus.map.room.Room;

public abstract class District {

    protected final PooledEngine engine;
    protected final World world;

    protected Vector2 position;
    protected float rotation;

    protected ImmutableArray<Room> rooms = new ImmutableArray<>(new Array<>());

    protected District(PooledEngine engine, World world, Vector2 position, float rotation) {
        this.engine = engine;
        this.world = world;
        this.position = position;
        this.rotation = rotation;
    }

    public void load() {
        rooms = new ImmutableArray<>(createAreas());
        rooms.forEach(Room::load);
    }
    public void unload() {
        rooms.forEach(Room::unload);
    }

    protected abstract Array<Room> createAreas();

    protected abstract Vector2 applyTransform(Vector2 position);

}
