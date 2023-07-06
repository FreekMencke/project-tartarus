package com.freekmencke.tartarus.map.district;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.freekmencke.tartarus.entities.components.BodyComponent;
import com.freekmencke.tartarus.entities.factories.EdgeEntityFactory;
import com.freekmencke.tartarus.map.room.Room;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class District {

    protected final PooledEngine engine;
    protected final World world;

    private final ComponentMapper<BodyComponent> bodyMap = ComponentMapper.getFor(BodyComponent.class);

    protected Vector2 position;
    protected float rotation;

    protected ImmutableArray<Room> rooms = new ImmutableArray<>(new Array<>());
    protected ImmutableArray<Entity> walls = new ImmutableArray<>(new Array<>());

    protected District(PooledEngine engine, World world, Vector2 position, float rotation) {
        this.engine = engine;
        this.world = world;
        this.position = position;
        this.rotation = rotation;
    }

    public void load() {
        rooms = new ImmutableArray<>(createRooms());
        rooms.forEach(Room::load);

        walls = new ImmutableArray<>(createWalls());
        walls.forEach(engine::addEntity);
    }

    public void unload() {
        rooms.forEach(Room::unload);

        walls.forEach(wall -> world.destroyBody(bodyMap.get(wall).body));
        walls.forEach(engine::removeEntity);
    }

    protected abstract Array<Room> createRooms();

    protected Array<Entity> createWalls() {
        ArrayList<Vector2[]> vertices = new ArrayList<>();

        rooms.forEach(room -> {
            Array<Vector2> corners = room.getCornerCoords();
            for (int i = 1; i < corners.size; i++) {
                vertices.add(new Vector2[]{corners.get(i - 1), corners.get(i)});
            }
            vertices.add(new Vector2[]{corners.get(corners.size - 1), corners.get(0)});
        });

        Array<Entity> walls = new Array<>();

        Arrays.stream(vertices.toArray(Vector2[][]::new)).filter(vert -> {
            long count = Arrays.stream(vertices.toArray(Vector2[][]::new))
                    .filter(innerVert -> vert[0].equals(innerVert[0]) && vert[1].equals(innerVert[1]) || vert[1].equals(innerVert[0]) && vert[0].equals(innerVert[1]))
                    .count();
            return count == 1;
        }).forEach(dv -> walls.add(EdgeEntityFactory.create(engine, world, dv, applyTransform(new Vector2()), 0)));

        return walls;
    }

    protected abstract Vector2 applyTransform(Vector2 position);

}
