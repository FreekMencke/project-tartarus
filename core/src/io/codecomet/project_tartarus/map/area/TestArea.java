package io.codecomet.project_tartarus.map.area;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import io.codecomet.project_tartarus.entities.factories.WallEntityFactory;
import io.codecomet.project_tartarus.map.sector.Sector;
import io.codecomet.project_tartarus.map.sector.TestSector;

public class TestArea extends Area {

    public Polygon bounds = new Polygon(new float[] { -1, -1, -1, 1, 1, 1, 1, -1  });

    public TestArea(PooledEngine engine, World world, Vector2 position, float rotation) {
        super(engine, world, position, rotation);
        bounds.rotate(rotation);
        bounds.translate(position.x, position.y);
    }
    public TestArea(PooledEngine engine, World world, Vector2 position) {
        this(engine, world, position, 0);
    }
    public TestArea(PooledEngine engine, World world) {
        this(engine, world, new Vector2());
    }

    @Override
    public Rectangle getBoundingBox() {
        return bounds.getBoundingRectangle();
    }

    protected Array<Sector> createAreas() {
        Array<Sector> areas = new Array<>();

        areas.add(new TestSector(engine, world, applyTransform(new Vector2(-.5f, -.5f)), rotation));
        areas.add(new TestSector(engine, world, applyTransform(new Vector2(-.5f, .5f)), rotation));
        areas.add(new TestSector(engine, world, applyTransform(new Vector2(.5f, .5f)), rotation));
        areas.add(new TestSector(engine, world, applyTransform(new Vector2(.5f, -.5f)), rotation));

        return areas;
    }

    @Override
    protected Array<Entity> createWalls() {
        Array<Entity> walls = new Array<>();

        walls.add(WallEntityFactory.create(engine, world, new Vector2(.1f, 2.1f), applyTransform(new Vector2(-1 , 0)), rotation));
        walls.add(WallEntityFactory.create(engine, world, new Vector2(.1f, 2.1f), applyTransform(new Vector2(0, 1)), rotation + 90));
        walls.add(WallEntityFactory.create(engine, world, new Vector2(.1f, 2.1f), applyTransform(new Vector2(1, 0)), rotation));
        walls.add(WallEntityFactory.create(engine, world, new Vector2(.1f, 1.1f), applyTransform(new Vector2(.5f,-1)), rotation + 90));

        return walls;
    }

    @Override
    protected Vector2 applyTransform(Vector2 position) {
        return position
                .rotateDeg(rotation)
                .add(this.position);
    }
}
