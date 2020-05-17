package io.codecomet.project_tartarus.entities.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import io.codecomet.project_tartarus.entities.components.BodyComponent;
import io.codecomet.project_tartarus.entities.components.ControllerComponent;
import io.codecomet.project_tartarus.entities.components.VelocityComponent;

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

        // TODO: make configurable keymap
        boolean up = controller.keysPressed.getOrDefault(Input.Keys.W, false);
        boolean right = controller.keysPressed.getOrDefault(Input.Keys.D, false);
        boolean down = controller.keysPressed.getOrDefault(Input.Keys.S, false);
        boolean left = controller.keysPressed.getOrDefault(Input.Keys.A, false);

        applyVelocityToBody(up, right, down, left, body, velocityMap.get(entity));

        rotateBodyToCursor(body, controller);
    }

    private void applyVelocityToBody(boolean up, boolean right, boolean down, boolean left, Body body, VelocityComponent velocity) {
        // DETERMINE VELOCITY
        float xSpeed = body.getLinearVelocity().x;
        float ySpeed = body.getLinearVelocity().y;

        if (left && !right) xSpeed = -velocity.speed.x;
        else if (right && !left) xSpeed = velocity.speed.x;

        if (up && !down) ySpeed = velocity.speed.y;
        else if (down && !up) ySpeed = -velocity.speed.y;

        if ((up || down) && (left || right)) {
            xSpeed = (float) Math.sqrt(Math.pow(xSpeed, 2) / 2) * Math.signum(xSpeed);
            ySpeed = (float) Math.sqrt(Math.pow(ySpeed, 2) / 2) * Math.signum(ySpeed);
        }

        // APPLY VELOCITY TO BODY
        body.setLinearVelocity(xSpeed, ySpeed);
        body.setLinearDamping(velocity.dampening);
    }

    private void rotateBodyToCursor(Body body, ControllerComponent controller) {
        if (!controller.isTouching) return;

        Vector2 bodyWorldPosition = body.getPosition();
        Vector3 mouseWorldPosition = camera.unproject(new Vector3(controller.touchPosition, 0));

        // CALCULATE ANGLE WITH X-AXIS
        float angle = MathUtils.atan2(mouseWorldPosition.y - bodyWorldPosition.y, mouseWorldPosition.x - bodyWorldPosition.x);
        // CALCULATE ANGLE WITH Y-AXIS
        angle += ANGLE_OFFSET;
        // FIX ANGLE RANGE FROM 0-2PI
        if (angle < 0) angle += 360 * MathUtils.degRad;

        body.setTransform(bodyWorldPosition, angle);
    }

}
