package io.codecomet.project_tartarus.entities.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.*;
import io.codecomet.project_tartarus.entities.components.*;

public class PlayerEntityFactory {

    public static Entity create(PooledEngine engine, World world) {
        VelocityComponent velocityComponent = engine.createComponent(VelocityComponent.class);
        velocityComponent.speed.set(3, 3); // TODO: default player speed.

        return engine.createEntity()
            .add(velocityComponent)
            .add(createBodyComponent(engine, world))
            .add(engine.createComponent(ControllerComponent.class))
            .add(engine.createComponent(TransformComponent.class))
            .add(engine.createComponent(PlayerComponent.class));
    }

    private static BodyComponent createBodyComponent(PooledEngine engine, World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bodyDef);

        addHeadFixture(body);
        addShouldersFixture(body);

        BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);
        bodyComponent.body = body;

        return bodyComponent;
    }

    private static void addHeadFixture(Body body) {
        CircleShape headShape = new CircleShape();
        headShape.setRadius(.25f); // TODO: default player head size.

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = headShape;
        body.createFixture(fixtureDef);

        headShape.dispose();
    }

    private static void addShouldersFixture(Body body) {
        PolygonShape shouldersShape = new PolygonShape();
        shouldersShape.setAsBox(.42f, .12f); // TODO: default player shoulder size.

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shouldersShape;
        body.createFixture(fixtureDef);

        shouldersShape.dispose();
    }


}
