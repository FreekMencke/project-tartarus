package io.codecomet.project_tartarus.entities.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.Body;
import io.codecomet.project_tartarus.entities.components.BodyComponent;
import io.codecomet.project_tartarus.entities.components.ControllerComponent;
import io.codecomet.project_tartarus.entities.components.SpeedComponent;

import java.util.HashMap;

public class ControllerMovementSystem extends IteratingSystem {

    private final ComponentMapper<BodyComponent> bodyMap = ComponentMapper.getFor(BodyComponent.class);
    private final ComponentMapper<ControllerComponent> controllerMap = ComponentMapper.getFor(ControllerComponent.class);
    private final ComponentMapper<SpeedComponent> speedMap = ComponentMapper.getFor(SpeedComponent.class);

    public ControllerMovementSystem() {
        super(Family.all(
            BodyComponent.class,
            ControllerComponent.class,
            SpeedComponent.class
        ).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Body body = bodyMap.get(entity).body;
        HashMap<Integer, Boolean> keysPressed = controllerMap.get(entity).keysPressed;
        SpeedComponent speedComponent = speedMap.get(entity);

        float xSpeed = body.getLinearVelocity().x;
        float ySpeed = body.getLinearVelocity().y;

        // TODO: make configurable keymap
        boolean W = keysPressed.getOrDefault(Input.Keys.W, false);
        boolean A = keysPressed.getOrDefault(Input.Keys.A, false);
        boolean S = keysPressed.getOrDefault(Input.Keys.S, false);
        boolean D = keysPressed.getOrDefault(Input.Keys.D, false);

        // HORIZONTAL MOVEMENT
        if (A && !D) xSpeed = -speedComponent.speed.x;
        else if (D && !A) xSpeed = speedComponent.speed.x;

        // VERTICAL MOVEMENT
        if (W && !S) ySpeed = speedComponent.speed.y;
        else if (S && !W) ySpeed = -speedComponent.speed.y;

        // DIAGONAL MOVEMENT
        if((W || S) && (A || D)) {
            xSpeed = (float)Math.sqrt(Math.pow(xSpeed, 2) /2) * Math.signum(xSpeed);
            ySpeed = (float)Math.sqrt(Math.pow(ySpeed, 2) /2) * Math.signum(ySpeed);
        }

        body.setLinearVelocity(xSpeed, ySpeed);
        body.setLinearDamping(speedComponent.dampening);
    }
}
