package io.codecomet.project_tartarus.entities.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import io.codecomet.project_tartarus.entities.components.*;

public class PlayerEntityFactory {

    private static final Texture PLAYER_TEXTURE = new Texture("textures/player/body.png");

    public static final float HEAD_RADIUS = .15f; // 15cm
    public static final Vector2 SHOULDERS = new Vector2(.25f, .075f); // half-values: 50cm x 16cm

    public static Entity create(PooledEngine engine, World world) {
        Gdx.app.log("PlayerEntityFactory", "Create");

        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        textureComponent.region = new TextureRegion(PLAYER_TEXTURE);
        textureComponent.size = new Vector2(SHOULDERS.x, HEAD_RADIUS).scl(2); // scl(2) because radius & half-values

        return engine.createEntity()
            .add(createBodyComponent(engine, world))
            .add(engine.createComponent(CameraComponent.class))
            .add(engine.createComponent(ControllerComponent.class))
            .add(engine.createComponent(TransformComponent.class))
            .add(textureComponent)
            .add(engine.createComponent(PlayerComponent.class))
            .add(engine.createComponent(VelocityComponent.class));
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
