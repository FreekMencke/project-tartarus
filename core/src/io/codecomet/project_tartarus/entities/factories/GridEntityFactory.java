package io.codecomet.project_tartarus.entities.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import io.codecomet.project_tartarus.entities.components.TextureComponent;
import io.codecomet.project_tartarus.entities.components.TransformComponent;

public class GridEntityFactory {

    private static final Texture gridTexture = new Texture("test-grid.png");

    public static Entity create(PooledEngine engine) {
        return create(engine, new Vector3());
    }

    public static Entity create(PooledEngine engine, Vector3 position) {
        Gdx.app.log("GridEntityFactory", "Create");

        TransformComponent transformComponent = engine.createComponent(TransformComponent.class);
        transformComponent.position.set(position);
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        textureComponent.region = new TextureRegion(gridTexture);

        return engine.createEntity()
            .add(textureComponent)
            .add(transformComponent);
    }


}
