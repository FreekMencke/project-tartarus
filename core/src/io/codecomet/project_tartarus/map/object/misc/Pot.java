package io.codecomet.project_tartarus.map.object.misc;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import io.codecomet.project_tartarus.entities.components.*;


public interface Pot {

    class Size {
        public static final float DEFAULT = .10f;
        public static final float LARGE = .15f;
    }

    static PotBuilder defaultPotBuilder(PooledEngine engine, World world) {
        return PotBuilder.create(engine)
            .setBody(createPotBody(world, Size.DEFAULT))
            .setTexture(Size.DEFAULT)
            .setTransform(new Vector2());
    }

    static PotBuilder largePotBuilder(PooledEngine engine, World world) {
        return PotBuilder.create(engine)
            .setBody(createPotBody(world, Size.LARGE))
            .setTexture(Size.LARGE)
            .setTransform(new Vector2());
    }

    class PotBuilder {
        private static final Texture POT_TEXTURE = new Texture("textures/object/pot.png");

        private final PooledEngine engine;

        private BodyComponent bodyComponent;
        private TextureComponent textureComponent;
        private TransformComponent transformComponent;

        private PotBuilder(PooledEngine engine) {
            this.engine = engine;
        }

        public static PotBuilder create(PooledEngine engine) {
            return new PotBuilder(engine);
        }

        public PotBuilder setBody(Body body) {
            if(bodyComponent == null) bodyComponent = engine.createComponent(BodyComponent.class);
            else bodyComponent.reset();

            bodyComponent.body = body;
            return this;
        }

        public PotBuilder setTexture(float radius) {
            if(textureComponent == null) textureComponent = engine.createComponent(TextureComponent.class);
            else textureComponent.reset();

            textureComponent.region = new TextureRegion(POT_TEXTURE);
            textureComponent.size.set(radius * 2, radius * 2);
            return this;
        }

        public PotBuilder setTransform(Vector2 position) {
            if(transformComponent == null) transformComponent = engine.createComponent(TransformComponent.class);
            else transformComponent.reset();

            transformComponent.position.set(new Vector3(position, 0));
            transformComponent.rotation = MathUtils.random(MathUtils.PI2); // Random rotation
            return this;
        }

        public Entity build() {
            bodyComponent.body.setTransform(
                new Vector2(transformComponent.position.x, transformComponent.position.y),
                transformComponent.rotation
            );

            VelocityComponent velocityComponent = engine.createComponent(VelocityComponent.class);
            velocityComponent.speedDampening = 8;

            return engine.createEntity()
                .add(bodyComponent)
                .add(textureComponent)
                .add(transformComponent)
                .add(velocityComponent);
        }
    }

    static Body createPotBody(World world, float size) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bodyDef);

        CircleShape potShape = new CircleShape();
        potShape.setRadius(size);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = potShape;
        fixtureDef.density = 200;
        fixtureDef.friction = .3f;

        body.createFixture(fixtureDef);
        potShape.dispose();

        return body;
    }

}
