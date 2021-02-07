package io.codecomet.project_tartarus.map.area;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import io.codecomet.project_tartarus.map.sector.Sector;
import io.codecomet.project_tartarus.map.sector.TestSector;

public class TestArea extends Area {

    public TestArea(PooledEngine engine, World world) {
        super(engine, world);
    }
    public TestArea(PooledEngine engine, World world, Vector3 position) {
        super(engine, world, position);
    }

    protected Array<Sector> createAreas() {
        Array<Sector> areas = new Array<>();

        areas.add(new TestSector(engine, world));
        areas.add(new TestSector(engine, world, new Vector3(0, 1, 0)));
        areas.add(new TestSector(engine, world, new Vector3(1, 0, 0)));
        areas.add(new TestSector(engine, world, new Vector3(1, 1, 0)));

        return areas;
    }


}
