package com.freekmencke.tartarus.map.room;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.freekmencke.tartarus.map.area.Area;

public class EmptyRoom extends Room {

    public EmptyRoom(PooledEngine engine, World world, Vector2 position, float rotation, RoomSize roomSize) {
        super(engine, world, roomSize, position, rotation);
    }

    @Override
    protected Array<Area> createAreas() {
        return new Array<>();
    }
}
