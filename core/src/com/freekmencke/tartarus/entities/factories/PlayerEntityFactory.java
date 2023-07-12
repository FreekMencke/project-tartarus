package com.freekmencke.tartarus.entities.factories;

import box2dLight.ConeLight;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.freekmencke.tartarus.entities.builders.EntityBuilder;
import com.freekmencke.tartarus.entities.components.CameraComponent;
import com.freekmencke.tartarus.entities.components.ConeLightComponent;
import com.freekmencke.tartarus.entities.components.ControllerComponent;
import com.freekmencke.tartarus.entities.components.PlayerComponent;
import com.freekmencke.tartarus.entities.systems.LightingSystem;
import com.freekmencke.tartarus.map.theme.DefaultTheme;

public class PlayerEntityFactory {

    private static final Texture PLAYER_TEXTURE = new Texture("textures/player/body.png");

    public static final float FLAT_EGDE_RADIUS = .02f;
    public static final float HEAD_RADIUS = .15f; // 15cm
    public static final Vector2 SHOULDERS = new Vector2(.25f, .075f); // half-values: 50cm x 16cm

    public static Entity create(PooledEngine engine, World world, Vector2 position, float rotation) {
        Body body = createBody(world);

        return EntityBuilder.create(engine)
                .setBody(body)
                .setTransform(position, rotation, 0)
                .setTexture(new TextureRegion(PLAYER_TEXTURE), new Vector2(SHOULDERS.x, HEAD_RADIUS).scl(2))
                .setVelocity()
                .build()
                .add(createConeLightComponent(engine, body))
                .add(engine.createComponent(CameraComponent.class))
                .add(engine.createComponent(ControllerComponent.class))
                .add(engine.createComponent(PlayerComponent.class));
    }

    private static Body createBody(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bodyDef);
        body.setFixedRotation(true);

        addHeadFixture(body);
        addShouldersFixture(body);

        return body;
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
        FixtureDef fixtureDef = new FixtureDef();

        shouldersShape.set(new Vector2[]{
                new Vector2(-SHOULDERS.x + FLAT_EGDE_RADIUS, -SHOULDERS.y),
                new Vector2(-SHOULDERS.x, -SHOULDERS.y + FLAT_EGDE_RADIUS),
                new Vector2(-SHOULDERS.x, SHOULDERS.y - FLAT_EGDE_RADIUS),
                new Vector2(-SHOULDERS.x + FLAT_EGDE_RADIUS, SHOULDERS.y),
                new Vector2(SHOULDERS.x - FLAT_EGDE_RADIUS, SHOULDERS.y),
                new Vector2(SHOULDERS.x, SHOULDERS.y - FLAT_EGDE_RADIUS),
                new Vector2(SHOULDERS.x, -SHOULDERS.y + FLAT_EGDE_RADIUS),
                new Vector2(SHOULDERS.x - FLAT_EGDE_RADIUS, -SHOULDERS.y),
        });

        fixtureDef.shape = shouldersShape;
        fixtureDef.density = 1000;
        fixtureDef.friction = .5f;
        body.createFixture(fixtureDef);
        shouldersShape.dispose();
    }

    private static ConeLightComponent createConeLightComponent(Engine engine, Body body) {
        ConeLight coneLight = new ConeLight(engine.getSystem(LightingSystem.class).getRayHandler(), 256, new DefaultTheme().ambientLight, 4, 0,0, 0, 60);
        coneLight.attachToBody(body, 0,-.1f, 90);
        coneLight.setIgnoreAttachedBody(true);
        coneLight.setSoft(true);
        coneLight.setSoftnessLength(.5f);

        ConeLightComponent coneLightComponent = engine.createComponent(ConeLightComponent.class);
        coneLightComponent.coneLight = coneLight;

        return coneLightComponent;
    }


}
