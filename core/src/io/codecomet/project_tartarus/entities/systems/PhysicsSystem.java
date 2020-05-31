package io.codecomet.project_tartarus.entities.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import io.codecomet.project_tartarus.entities.components.BodyComponent;
import io.codecomet.project_tartarus.entities.components.TransformComponent;

public class PhysicsSystem extends IteratingSystem {

    public static final float MAX_STEP_TIME = 1 / 60f;
    private static float accumulator = 0f;

    private final World world;

    private final ComponentMapper<BodyComponent> bodyMap = ComponentMapper.getFor(BodyComponent.class);
    private final ComponentMapper<TransformComponent> transformMap = ComponentMapper.getFor(TransformComponent.class);

    public PhysicsSystem(World world) {
        super(Family.all(BodyComponent.class, TransformComponent.class).get());
        this.world = world;
    }

    @Override
    public void update(float deltaTime) {
        accumulator += deltaTime;

        if (accumulator >= MAX_STEP_TIME) {
            world.step(MAX_STEP_TIME, 8, 3);
            super.update(MAX_STEP_TIME);
            accumulator -= MAX_STEP_TIME;
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transformComponent = transformMap.get(entity);
        BodyComponent bodyComp = bodyMap.get(entity);

        Vector2 position = bodyComp.body.getPosition();
        transformComponent.position.x = position.x;
        transformComponent.position.y = position.y;

        transformComponent.rotation = bodyComp.body.getAngle() * MathUtils.radiansToDegrees;
    }

}