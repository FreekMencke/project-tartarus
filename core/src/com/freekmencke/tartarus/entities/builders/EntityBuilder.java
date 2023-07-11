package com.freekmencke.tartarus.entities.builders;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.freekmencke.tartarus.entities.components.BodyComponent;
import com.freekmencke.tartarus.entities.components.TextureComponent;
import com.freekmencke.tartarus.entities.components.TransformComponent;
import com.freekmencke.tartarus.entities.components.VelocityComponent;

public class EntityBuilder {

    private final PooledEngine engine;

    private BodyComponent bodyComponent;
    private TextureComponent textureComponent;
    private TransformComponent transformComponent;
    private VelocityComponent velocityComponent;

    private EntityBuilder(PooledEngine engine) {
        this.engine = engine;
    }

    public static EntityBuilder create(PooledEngine engine) {
        return new EntityBuilder(engine);
    }

    public EntityBuilder setBody(Body body) {
        if (bodyComponent == null) bodyComponent = engine.createComponent(BodyComponent.class);
        else bodyComponent.reset();

        bodyComponent.body = body;

        return this;
    }

    public EntityBuilder setTexture(TextureRegion region, Vector2 size) {
        if (textureComponent == null) textureComponent = engine.createComponent(TextureComponent.class);
        else textureComponent.reset();

        textureComponent.region = region;
        textureComponent.size.set(size);

        return this;
    }

    public EntityBuilder setTransform(Vector2 position, float rotation, int zIndex) {
        if (transformComponent == null) transformComponent = engine.createComponent(TransformComponent.class);
        else transformComponent.reset();

        transformComponent.position.set(new Vector3(position, zIndex));
        transformComponent.rotation = rotation;

        return this;
    }

    public EntityBuilder setVelocity() {
        return this.setVelocity(VelocityComponent.DEFAULT_SPEED_DAMPENING, VelocityComponent.DEFAULT_ANGULAR_DAMPENING);
    }

    public EntityBuilder setVelocity(float speedDampening, float angularDampening) {
        if (velocityComponent == null) velocityComponent = engine.createComponent(VelocityComponent.class);
        else velocityComponent.reset();

        velocityComponent.speedDampening = speedDampening;
        velocityComponent.angularDampening = angularDampening;

        return this;
    }

    public Entity build() {
        Entity entity = engine.createEntity().add(transformComponent);

        if (bodyComponent != null) {
            bodyComponent.body.setTransform(
                    new Vector2(transformComponent.position.x, transformComponent.position.y),
                    transformComponent.rotation * MathUtils.degRad
            );
            entity.add(bodyComponent);
        }
        if (textureComponent != null) entity.add(textureComponent);
        if (velocityComponent != null) entity.add(velocityComponent);

        return entity;
    }

}
