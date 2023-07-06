package com.freekmencke.tartarus.entities.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class PhysicsDebugRenderingSystem extends IteratingSystem {

    private final Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
    private final World world;
    private final OrthographicCamera camera;

    public PhysicsDebugRenderingSystem(World world, OrthographicCamera camera) {
        super(Family.all().get());
        this.world = world;
        this.camera = camera;
    }

    @Override
    public void update(float deltaTime) {
        debugRenderer.render(world, camera.combined);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) { }

}