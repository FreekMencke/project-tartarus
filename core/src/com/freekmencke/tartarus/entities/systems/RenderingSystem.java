package com.freekmencke.tartarus.entities.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.freekmencke.tartarus.entities.components.TextureComponent;
import com.freekmencke.tartarus.entities.components.TransformComponent;

import java.util.Comparator;

public class RenderingSystem extends SortedIteratingSystem {

    private static class ZComparator implements Comparator<Entity> {
        private final ComponentMapper<TransformComponent> transformMap = ComponentMapper.getFor(TransformComponent.class);

        @Override
        public int compare(Entity entityA, Entity entityB) {
            return (int) Math.signum(
                transformMap.get(entityA).position.z - transformMap.get(entityB).position.z
            );
        }
    }

    static final float PIXELS_PER_METER = 200.0f; // TODO: MAKE DEFAULT + ADD ZOOM
    static final float PIXELS_TO_METRES = 1.0f / PIXELS_PER_METER;

    private final SpriteBatch batch;
    private final OrthographicCamera camera = new OrthographicCamera();

    private final ComponentMapper<TextureComponent> textureMap = ComponentMapper.getFor(TextureComponent.class);
    private final ComponentMapper<TransformComponent> transformMap = ComponentMapper.getFor(TransformComponent.class);

    public RenderingSystem(SpriteBatch batch) {
        super(Family.all(TransformComponent.class, TextureComponent.class).get(), new ZComparator());

        this.batch = batch;

        camera.viewportWidth = Gdx.graphics.getWidth() / PIXELS_PER_METER;
        camera.viewportHeight = Gdx.graphics.getHeight() / PIXELS_PER_METER;
    }

    @Override
    public void update(float deltaTime) {
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.enableBlending();

        batch.begin();
        super.update(deltaTime);
        batch.end();
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        TextureComponent texture = textureMap.get(entity);
        TransformComponent transform = transformMap.get(entity);

        if (texture.region == null) {
            return;
        }

        float width;
        float height;

        if (!texture.size.equals(Vector2.Zero)) {
            width = texture.size.x * PIXELS_PER_METER;
            height = texture.size.y * PIXELS_PER_METER;
        } else {
            width = texture.region.getRegionWidth();
            height = texture.region.getRegionHeight();
        }

        float originX = width / 2f;
        float originY = height / 2f;

        batch.draw(texture.region,
            transform.position.x - originX, transform.position.y - originY,
            originX, originY,
            width, height,
            transform.scale.x * PIXELS_TO_METRES, transform.scale.y * PIXELS_TO_METRES,
            transform.rotation);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void resize(int width, int height) {
        camera.viewportWidth = width * PIXELS_TO_METRES;
        camera.viewportHeight = height * PIXELS_TO_METRES;
        camera.update();
    }

}
