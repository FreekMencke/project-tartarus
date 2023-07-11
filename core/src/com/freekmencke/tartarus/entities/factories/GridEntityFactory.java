package com.freekmencke.tartarus.entities.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.freekmencke.tartarus.entities.builders.EntityBuilder;

public class GridEntityFactory {

    private static final Texture gridTexture = new Texture("textures/test-grid.png");

    public static Entity create(PooledEngine engine) {
        return EntityBuilder.create(engine)
                .setTransform(new Vector2(), 0, -100)
                .setTexture(new TextureRegion(gridTexture), new Vector2(32, 32))
                .build();
    }


}
