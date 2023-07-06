package com.freekmencke.tartarus.entities.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.freekmencke.tartarus.entities.components.BodyComponent;
import com.freekmencke.tartarus.entities.components.ControllerComponent;
import com.freekmencke.tartarus.entities.components.VelocityComponent;

public class ControllerMovementSystem extends IteratingSystem {

    private static final float ANGLE_OFFSET = -90 * MathUtils.degRad;

    private final ComponentMapper<BodyComponent> bodyMap = ComponentMapper.getFor(BodyComponent.class);
    private final ComponentMapper<ControllerComponent> controllerMap = ComponentMapper.getFor(ControllerComponent.class);
    private final ComponentMapper<VelocityComponent> velocityMap = ComponentMapper.getFor(VelocityComponent.class);

    private final OrthographicCamera camera;

    public ControllerMovementSystem(OrthographicCamera camera) {
        super(Family.all(
            BodyComponent.class,
            ControllerComponent.class,
            VelocityComponent.class
        ).get());

        this.camera = camera;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Body body = bodyMap.get(entity).body;
        ControllerComponent controller = controllerMap.get(entity);
        VelocityComponent velocity = velocityMap.get(entity);

        Vector2 direction = new Vector2(controller.direction);

        applyVelocityToBody(body, direction, velocity);
        velocity.currentSpeed = body.getLinearVelocity().len();

        if (controller.isTouching) rotateBodyToCursor(body, controller);
        else rotateToDirection(body, direction);
    }

    private void applyVelocityToBody(Body body, Vector2 direction, VelocityComponent velocity) {
        if (direction.isZero()) return;

        Vector2 linearVelocity = new Vector2(0, velocity.speed);
        linearVelocity.rotateDeg(new Vector2(direction).rotate90(-1).angleDeg());

        body.applyForceToCenter(linearVelocity.scl(50), true);
    }

    private void rotateBodyToCursor(Body body, ControllerComponent controller) {
        Vector2 bodyWorldPosition = body.getPosition();
        Vector3 mouseWorldPosition = camera.unproject(new Vector3(controller.touchPosition, 0));

        Vector2 relativeMousePosition =  new Vector2(mouseWorldPosition.x - bodyWorldPosition.x, mouseWorldPosition.y -bodyWorldPosition.y);
        float angle = relativeMousePosition.rotate90(-1).angleDeg() * MathUtils.degRad;

        body.setTransform(bodyWorldPosition, angle);
    }

    private void rotateToDirection(Body body, Vector2 direction) {
        if (direction.equals(Vector2.Zero)) return;

        body.setTransform(body.getPosition(), (direction.angleDeg() * MathUtils.degRad) + ANGLE_OFFSET);
    }

}
