package io.codecomet.project_tartarus.map.room;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import io.codecomet.project_tartarus.map.area.Area;
import io.codecomet.project_tartarus.map.area.PotArea;
import io.codecomet.project_tartarus.utils.Matrix;

public class PotRoom extends Room {

    public PotRoom(PooledEngine engine, World world, Vector2 position, float rotation) {
        super(engine, world, RoomSize.SMALL, position, rotation);
    }

    protected void createAreas(Matrix<Area> areas) {
        areas.setElement(0,0, new PotArea(engine, world));
        areas.setElement(1,0, new PotArea(engine, world));
        areas.setElement(0,1, new PotArea(engine, world));
        areas.setElement(1,1, new PotArea(engine, world));
    }

}
