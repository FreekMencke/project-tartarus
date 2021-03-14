package io.codecomet.project_tartarus.map.area;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import io.codecomet.project_tartarus.map.object.misc.Pot;

public class PotArea extends Area {

    public PotArea(PooledEngine engine, World world) {
        super(engine, world, new Vector2());
    }

    protected Array<Entity> createObjects() {
        Array<Entity> entities = new Array<>();

        entities.add(Pot.defaultPotBuilder(engine, world).setTransform(applyTransform(new Vector2(- Pot.Size.LARGE, - Pot.Size.LARGE))).build());
        entities.add(Pot.defaultPotBuilder(engine, world).setTransform(applyTransform(new Vector2(Pot.Size.LARGE, Pot.Size.LARGE))).build());
        entities.add(Pot.largePotBuilder(engine, world).setTransform(applyTransform(new Vector2(- Pot.Size.LARGE, Pot.Size.LARGE))).build());
        entities.add(Pot.largePotBuilder(engine, world).setTransform(applyTransform(new Vector2(Pot.Size.LARGE, - Pot.Size.LARGE))).build());

        return entities;
    }

    protected Vector2 applyTransform(Vector2 position) {
        return position
                .rotateDeg(this.rotation) // room rotation.
                .add(.5f, .5f) // offset for bottom-left coordinate system.
                .add(this.position); // offset for room position.

    }

}
