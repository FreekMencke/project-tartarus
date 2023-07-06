package com.freekmencke.tartarus.map.room;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.freekmencke.tartarus.map.area.Area;
import com.freekmencke.tartarus.utils.Matrix;

public abstract class Room implements Disposable {

    public enum RoomSize {
        SMALL(2), MEDIUM(3), LARGE(4), ENORMOUS(6), GIGANTIC(8);

        int value;

        RoomSize(int numVal) {
            this.value = numVal;
        }
    }

    protected final PooledEngine engine;
    protected final World world;

    protected RoomSize roomSize;

    private Matrix<Area> areas;
    protected Vector2 position;
    protected float rotation;

    protected Room(PooledEngine engine, World world, RoomSize roomSize, Vector2 position, float rotation) {
        this.engine = engine;
        this.world = world;
        this.roomSize = roomSize;
        this.areas = new Matrix<>(roomSize.value);
        this.position = position;
        this.rotation = rotation;
    }

    public void load() {
        this.createAreas(areas);
        areas.getAllElements().forEach(element -> {
            // Rotate element position around room center (offset  for bottom-left coordinate area), then add room position.
            element.value.position.set(element.position.cpy().rotateAroundDeg(halfSize().sub(.5f, .5f), rotation).add(position));
            element.value.rotation = rotation; // Set area rotation to the room's rotation.
            element.value.load();
        });
    }

    public void unload() {
        areas.getAllElements().forEach(e -> e.value.unload());
        this.areas = new Matrix<>(roomSize.value);
    }

    public Array<Vector2> getCornerCoords() {
        return new Array<>(new Vector2[]{
                new Vector2().rotateAroundDeg(halfSize(), rotation).add(position),
                new Vector2(0, roomSize.value).rotateAroundDeg(halfSize(), rotation).add(position),
                new Vector2(roomSize.value, roomSize.value).rotateAroundDeg(halfSize(), rotation).add(position),
                new Vector2(roomSize.value, 0).rotateAroundDeg(halfSize(), rotation).add(position),
        });
    }

    protected Vector2 halfSize() {
        return new Vector2(roomSize.value, roomSize.value).scl(.5f);
    }

    protected abstract void createAreas(Matrix<Area> matrix);

    @Override
    public void dispose() {
        unload();
    }

}
