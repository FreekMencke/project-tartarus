package io.codecomet.project_tartarus.map.sector;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import io.codecomet.project_tartarus.map.object.misc.Pot;

public class TestSector extends Sector {

    public TestSector(PooledEngine engine, World world,  Vector2 transform, float rotation) {
        super(engine, world, transform, rotation);
    }
    public TestSector(PooledEngine engine, World world,  Vector2 transform) {
        this(engine, world, transform,0);
    }
    public TestSector(PooledEngine engine, World world) {
        this(engine, world, new  Vector2());
    }

    protected Array<Entity> createEntities() {
        Array<Entity> entities = new Array<>();

        entities.add(Pot.defaultPotBuilder(engine, world).setTransform(applyTransform(new Vector2(- Pot.Size.LARGE, - Pot.Size.LARGE))).build());
        entities.add(Pot.defaultPotBuilder(engine, world).setTransform(applyTransform(new Vector2(Pot.Size.LARGE, Pot.Size.LARGE))).build());
        entities.add(Pot.largePotBuilder(engine, world).setTransform(applyTransform(new Vector2(- Pot.Size.LARGE, Pot.Size.LARGE))).build());
        entities.add(Pot.largePotBuilder(engine, world).setTransform(applyTransform(new Vector2(Pot.Size.LARGE, - Pot.Size.LARGE))).build());

        return entities;
    }

    protected Vector2 applyTransform(Vector2 position) {
        return position
                .rotateDeg(rotation)
                .add(this.position);

    }

}
