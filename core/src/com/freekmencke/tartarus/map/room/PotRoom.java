package com.freekmencke.tartarus.map.room;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.freekmencke.tartarus.map.area.Area;
import com.freekmencke.tartarus.map.area.PotArea;


public class PotRoom extends Room {

    public PotRoom(PooledEngine engine, World world, Vector2 position, float rotation) {
        super(engine, world, RoomSize.SMALL, position, rotation);
    }

    protected Array<Area> createAreas() {
        Array<Area> areas = new Array<>();

        areas.add(new PotArea(engine, world, applyRoomTransformToArea(new Vector2(0, 0)), this.rotation + 45));
        areas.add(new PotArea(engine, world, applyRoomTransformToArea(new Vector2(0, 1)), this.rotation + 45));
        areas.add(new PotArea(engine, world, applyRoomTransformToArea(new Vector2(1, 0)), this.rotation));
        areas.add(new PotArea(engine, world, applyRoomTransformToArea(new Vector2(1, 1)), this.rotation));

        return areas;
    }

}
