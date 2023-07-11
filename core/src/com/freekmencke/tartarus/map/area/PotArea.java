package com.freekmencke.tartarus.map.area;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.freekmencke.tartarus.map.object.misc.PotFactory;

public class PotArea extends Area {

    public PotArea(PooledEngine engine, World world, Vector2 position, float rotation) {
        super(engine, world, position, rotation);
    }

    protected Array<Entity> createObjects() {
        Array<Entity> entities = new Array<>();

        entities.add(PotFactory.buildPot(engine, world, applyAreaTransformToEntity(new Vector2(- .15f, - .15f)), PotFactory.Size.MEDIUM));
        entities.add(PotFactory.buildPot(engine, world, applyAreaTransformToEntity(new Vector2(.15f, .15f)), PotFactory.Size.MEDIUM));
        entities.add(PotFactory.buildPot(engine, world, applyAreaTransformToEntity(new Vector2(- .15f, .15f)), PotFactory.Size.SMALL));
        entities.add(PotFactory.buildPot(engine, world, applyAreaTransformToEntity(new Vector2(.15f, - .15f)), PotFactory.Size.SMALL));

        return entities;
    }


}
