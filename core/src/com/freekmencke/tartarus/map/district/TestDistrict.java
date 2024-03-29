package com.freekmencke.tartarus.map.district;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.freekmencke.tartarus.map.room.EmptyRoom;
import com.freekmencke.tartarus.map.room.PotRoom;
import com.freekmencke.tartarus.map.room.Room;
import com.freekmencke.tartarus.map.theme.CaveTheme;

public class TestDistrict extends District {

    public TestDistrict(PooledEngine engine, World world) {
        this(engine, world, new Vector2(), 0);
    }

    public TestDistrict(PooledEngine engine, World world, Vector2 position, float rotation) {
        super(engine, world, position, rotation);

        this.theme = new CaveTheme();
    }

    @Override
    protected Array<Room> createRooms() {
        Array<Room> rooms = new Array<>();

        rooms.add(new EmptyRoom(engine, world, applyDistrictTransformToRoom(new Vector2(0,0)), this.rotation, Room.RoomSize.LARGE).addDoor(new Vector2(1, 0), new Vector2(0, -1)));
        rooms.add(new PotRoom(engine, world, applyDistrictTransformToRoom(new Vector2(3, 0)), this.rotation).addDoor(new Vector2(-1, -1), new Vector2(0, 1)).addDoor(new Vector2(0, -1), new Vector2(1, 0)));
        rooms.add(new PotRoom(engine, world, applyDistrictTransformToRoom(new Vector2(5, 0)), this.rotation).addDoor(new Vector2(-1, -1), new Vector2(1, 0)));
        rooms.add(new PotRoom(engine, world, applyDistrictTransformToRoom(new Vector2(4, -2)), this.rotation).addDoor(new Vector2(-1, 1), new Vector2(1, 0)).addDoor(new Vector2(0, 1), new Vector2(1, 0)));

        return rooms;
    }

}
