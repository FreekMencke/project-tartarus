package com.freekmencke.tartarus.entities.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.freekmencke.tartarus.entities.builders.EntityBuilder;

public class EdgeEntityFactory {

    public static Entity create(PooledEngine engine, World world, Vector2[] vertices, Vector2 position, float rotation, boolean loop) {
        return EntityBuilder.create(engine)
                .setBody(createBody(world, vertices, loop))
                .setTransform(position, rotation, 0)
                .build();
    }

    private static Body createBody(World world, Vector2[] vertices, boolean loop) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bodyDef);

        addWallFixture(body, vertices, loop);

        return body;
    }

    private static void addWallFixture(Body body, Vector2[] vertices, boolean loop) {
        ChainShape chainShape = new ChainShape();
        if (loop) chainShape.createLoop(vertices);
        else chainShape.createChain(vertices);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = chainShape;
        fixtureDef.friction = .3f;
        body.createFixture(fixtureDef);

        chainShape.dispose();
    }


}
