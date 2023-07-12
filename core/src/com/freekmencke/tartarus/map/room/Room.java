package com.freekmencke.tartarus.map.room;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.freekmencke.tartarus.entities.components.BodyComponent;
import com.freekmencke.tartarus.entities.factories.EdgeEntityFactory;
import com.freekmencke.tartarus.map.area.Area;
import com.freekmencke.tartarus.map.util.Line;

public abstract class Room implements Disposable {

    public enum RoomSize {
        SMALL(2), MEDIUM(3), LARGE(4), ENORMOUS(6), GIGANTIC(8);
        final int value;

        RoomSize(int numVal) {
            this.value = numVal;
        }
    }

    private final ComponentMapper<BodyComponent> bodyMap = ComponentMapper.getFor(BodyComponent.class);

    protected final PooledEngine engine;
    protected final World world;

    protected RoomSize roomSize;
    private ImmutableArray<Area> areas;
    private ImmutableArray<Entity> walls;
    private final Array<Line> doors;

    protected Vector2 position;
    protected float rotation;

    protected Room(PooledEngine engine, World world, RoomSize roomSize, Vector2 position, float rotation) {
        this.engine = engine;
        this.world = world;
        this.roomSize = roomSize;
        this.position = position;
        this.rotation = rotation;
        this.doors = new Array<>();
    }

    public Room addDoor(Vector2 position, Vector2 vector) {
        doors.add(new Line(position.scl(halfSize()), vector.add(position)));
        return this;
    }

    public void load() {
        areas = new ImmutableArray<>(this.createAreas());
        areas.forEach(Area::load);

        walls =  new ImmutableArray<>(this.createWalls());
        walls.forEach(engine::addEntity);
    }

    public void unload() {
        areas.forEach(Area::unload);

        walls.forEach(wall -> world.destroyBody(bodyMap.get(wall).body));
        walls.forEach(engine::removeEntity);
    }

    public Vector2[] getRelativeCornerCoords() {
        return new Vector2[]{
                new Vector2().sub(halfSize()),
                new Vector2(0, roomSize.value).sub(halfSize()),
                new Vector2(roomSize.value, roomSize.value).sub(halfSize()),
                new Vector2(roomSize.value, 0).sub(halfSize()),
        };
    }

    protected Vector2 halfSize() {
        return new Vector2(roomSize.value, roomSize.value).scl(.5f);
    }

    protected abstract Array<Area> createAreas();

    private Array<Entity> createWalls() {
        Array<Entity> walls = new Array<>();
        Vector2[] corners = this.getRelativeCornerCoords();

        for (int i = 1; i <= corners.length; i++) {
            new Line(corners[i - 1], corners[i == corners.length ? 0 : i]).subtractLines(doors).forEach(l -> walls.add(EdgeEntityFactory.create(engine, world, l.getVertices(), position, rotation, false)));
        }

        return walls;
    }

    protected Vector2 applyRoomTransformToArea(Vector2 position) {
        return position
                .sub(halfSize()) // move center of room to 0, 0
                .rotateAroundDeg(new Vector2(-.5f, -.5f), this.rotation) // remove area offset
                .add(this.position); // add absolute room position
    }

    @Override
    public void dispose() {
        unload();
    }

}
