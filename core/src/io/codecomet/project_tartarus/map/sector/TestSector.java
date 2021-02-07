package io.codecomet.project_tartarus.map.sector;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import io.codecomet.project_tartarus.map.object.misc.Pot;

public class TestSector extends Sector {

    public TestSector(PooledEngine engine, World world) {
        super(engine, world);
    }
    public TestSector(PooledEngine engine, World world, Vector3 position) {
        super(engine, world, position);
    }

    protected Array<Entity> createEntities() {
        Array<Entity> entities = new Array<>();

        entities.add(Pot.defaultPotBuilder(engine, world).setTransform(new Vector3(.5f - Pot.Size.LARGE, .5f - Pot.Size.LARGE, 0).add(position)).build());
        entities.add(Pot.defaultPotBuilder(engine, world).setTransform(new Vector3(.5f + Pot.Size.LARGE, .5f + Pot.Size.LARGE, 0).add(position)).build());
        entities.add(Pot.largePotBuilder(engine, world).setTransform(new Vector3(.5f - Pot.Size.LARGE, .5f + Pot.Size.LARGE, 0).add(position)).build());
        entities.add(Pot.largePotBuilder(engine, world).setTransform(new Vector3(.5f + Pot.Size.LARGE, .5f - Pot.Size.LARGE, 0).add(position)).build());

        return entities;
    }

}
