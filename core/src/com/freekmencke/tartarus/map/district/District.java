package com.freekmencke.tartarus.map.district;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.freekmencke.tartarus.entities.systems.LightingSystem;
import com.freekmencke.tartarus.map.room.Room;
import com.freekmencke.tartarus.map.theme.DefaultTheme;
import com.freekmencke.tartarus.map.theme.Theme;

public abstract class District {

    protected final PooledEngine engine;
    protected final World world;

    protected Vector2 position;
    protected float rotation;

    protected Theme theme = new DefaultTheme();
    protected ImmutableArray<Room> rooms;

    public District(PooledEngine engine, World world, Vector2 position, float rotation) {
        this.engine = engine;
        this.world = world;
        this.position = position;
        this.rotation = rotation;
    }

    public District(PooledEngine engine, World world, Vector2 position) {
        this(engine, world, position, 0);
    }

    public void load() {
        // use Theme to load specific ambient light
        this.engine.getSystem(LightingSystem.class).setAmbientLight(this.theme.ambientLight);

        rooms = new ImmutableArray<>(createRooms());
        rooms.forEach(Room::load);
    }

    public void unload() {
        rooms.forEach(Room::unload);
    }

    protected abstract Array<Room> createRooms();

    protected Vector2 applyDistrictTransformToRoom(Vector2 position) {
        return position
                .rotateDeg(this.rotation)
                .add(this.position);
    }

}
