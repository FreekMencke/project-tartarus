package io.codecomet.project_tartarus.map.district;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import io.codecomet.project_tartarus.map.room.PotRoom;
import io.codecomet.project_tartarus.map.room.Room;

public class TestDistrict extends District {

    public TestDistrict(PooledEngine engine, World world, Vector2 position, float rotation) {
        super(engine, world, position, rotation);
    }

    public TestDistrict(PooledEngine engine, World world, Vector2 position) {
        this(engine, world, position, 0);
    }

    public TestDistrict(PooledEngine engine, World world) {
        this(engine, world, new Vector2());
    }

    @Override
    protected Array<Room> createRooms() {
        Array<Room> rooms = new Array<>();

        rooms.add(new PotRoom(engine, world, applyTransform(new Vector2()), this.rotation));
        rooms.add(new PotRoom(engine, world, applyTransform(new Vector2(2, 0)), this.rotation));
        rooms.add(new PotRoom(engine, world, applyTransform(new Vector2(2, -2)), this.rotation));

        return rooms;
    }

    @Override
    protected Vector2 applyTransform(Vector2 position) {
        return position
                .rotateDeg(this.rotation)
                .add(this.position);
    }

}
