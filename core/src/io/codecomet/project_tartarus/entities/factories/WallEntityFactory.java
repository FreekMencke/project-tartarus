package io.codecomet.project_tartarus.entities.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import io.codecomet.project_tartarus.entities.components.BodyComponent;
import io.codecomet.project_tartarus.entities.components.TransformComponent;

public class WallEntityFactory {

    public static Entity create(PooledEngine engine, World world, Vector2 size, Vector2 position, float rotation) {
        TransformComponent transformComponent = createTransformComponent(engine, position, rotation);

        return engine.createEntity()
                .add(transformComponent)
                .add(createBodyComponent(engine, world, transformComponent, size));
    }

    private static TransformComponent createTransformComponent(PooledEngine engine, Vector2 position, float rotation) {
        TransformComponent transformComponent = engine.createComponent(TransformComponent.class);
        transformComponent.position.set(new Vector3(position, 0));
        transformComponent.rotation = rotation;
        return transformComponent;
    }

    private static BodyComponent createBodyComponent(PooledEngine engine, World world, TransformComponent transformComponent, Vector2 size) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bodyDef);
        body.setTransform(transformComponent.position.x, transformComponent.position.y, transformComponent.rotation * MathUtils.degRad);

        addWallFixture(body, size);

        BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);
        bodyComponent.body = body;

        return bodyComponent;
    }

    private static void addWallFixture(Body body, Vector2 size) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size.x/2, size.y/2, new Vector2(), 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = .3f;
        body.createFixture(fixtureDef);

        shape.dispose();
    }


}
