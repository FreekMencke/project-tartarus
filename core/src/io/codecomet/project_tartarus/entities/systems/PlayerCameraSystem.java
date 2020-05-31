package io.codecomet.project_tartarus.entities.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector3;
import io.codecomet.project_tartarus.entities.components.CameraComponent;
import io.codecomet.project_tartarus.entities.components.PlayerComponent;
import io.codecomet.project_tartarus.entities.components.TransformComponent;

public class PlayerCameraSystem extends IteratingSystem {

    private static float accumulator = 0f;

    private final OrthographicCamera camera;

    private final ComponentMapper<CameraComponent> cameraMap = ComponentMapper.getFor(CameraComponent.class);
    private final ComponentMapper<TransformComponent> transformMap = ComponentMapper.getFor(TransformComponent.class);

    public PlayerCameraSystem(OrthographicCamera camera) {
        super(Family.all(CameraComponent.class, PlayerComponent.class, TransformComponent.class).get());
        this.camera = camera;
    }


    /**
     * Since the physics system uses fixed steps, we need to update our camera in fixed steps as well.
     */
    @Override
    public void update(float deltaTime) {
        accumulator += deltaTime;

        if (accumulator >= PhysicsSystem.MAX_STEP_TIME) {
            super.update(PhysicsSystem.MAX_STEP_TIME);
            accumulator -= PhysicsSystem.MAX_STEP_TIME;
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (!cameraMap.get(entity).enabled) return;

        Vector3 nextPosition = new Vector3(transformMap.get(entity).position);
        nextPosition.z = camera.position.z; // don't use transform Z for camera

        camera.position.interpolate(nextPosition, PhysicsSystem.MAX_STEP_TIME, Interpolation.pow2Out);
    }

}