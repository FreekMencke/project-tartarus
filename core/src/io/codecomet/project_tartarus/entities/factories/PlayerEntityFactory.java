package io.codecomet.project_tartarus.entities.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import io.codecomet.project_tartarus.entities.components.*;

public class PlayerEntityFactory {

    public static final float HEAD_RADIUS = .12f; // 12cm
    public static final Vector2 SHOULDERS = new Vector2(.2f, .06f); // half-values: 40cm x 12cm

    public static Entity create(PooledEngine engine, World world) {
        return engine.createEntity()
            .add(createBodyComponent(engine, world))
            .add(engine.createComponent(VelocityComponent.class))
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
        headShape.setRadius(HEAD_RADIUS);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = headShape;
        body.createFixture(fixtureDef);

        headShape.dispose();
    }

    private static void addShouldersFixture(Body body) {
        PolygonShape shouldersShape = new PolygonShape();
        shouldersShape.setAsBox(SHOULDERS.x, SHOULDERS.y);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shouldersShape;
        body.createFixture(fixtureDef);

        shouldersShape.dispose();
    }


}
