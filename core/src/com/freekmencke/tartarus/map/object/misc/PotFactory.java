package com.freekmencke.tartarus.map.object.misc;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.freekmencke.tartarus.entities.builders.EntityBuilder;


public class PotFactory {

    public enum Size {
        SMALL(.10f), MEDIUM(.15f);
        final float value;
        Size(float numVal) {
            this.value = numVal;
        }
    }

    private static final Texture POT_TEXTURE = new Texture("textures/object/pot.png");

    public static Entity buildPot(PooledEngine engine, World world, Vector2 position, PotFactory.Size size) {
        return EntityBuilder.create(engine)
                .setBody(createPotBody(world, size.value))
                .setTexture(new TextureRegion(POT_TEXTURE), new Vector2(size.value, size.value).scl(2))
                .setTransform(position, MathUtils.random(MathUtils.PI2), 0)
                .setVelocity(8, 4)
                .build();
    }

    private static Body createPotBody(World world, float size) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bodyDef);

        CircleShape potShape = new CircleShape();
        potShape.setRadius(size);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = potShape;
        fixtureDef.density = 500;
        fixtureDef.friction = .3f;

        body.createFixture(fixtureDef);
        potShape.dispose();

        return body;
    }

}
