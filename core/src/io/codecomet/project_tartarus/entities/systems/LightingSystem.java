package io.codecomet.project_tartarus.entities.systems;

import box2dLight.RayHandler;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import io.codecomet.project_tartarus.entities.components.BodyComponent;
import io.codecomet.project_tartarus.entities.components.TransformComponent;

public class LightingSystem extends IteratingSystem {

    private final RayHandler rayHandler;
    private final OrthographicCamera camera;

    public LightingSystem(World world, OrthographicCamera camera) {
        super(Family.all(BodyComponent.class, TransformComponent.class).get());
        this.camera = camera;

        RayHandler.setGammaCorrection(true);
        RayHandler.useDiffuseLight(true);
        rayHandler = new RayHandler(world);
        rayHandler.setShadows(true);
        rayHandler.setAmbientLight(1,1,1,1);
    }

    @Override
    public void update(float deltaTime) {
        rayHandler.setCombinedMatrix(camera);
        rayHandler.updateAndRender();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
    }

    public RayHandler getRayHandler() {
        return this.rayHandler;
    }

}