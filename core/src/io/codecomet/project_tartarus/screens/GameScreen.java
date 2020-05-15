package io.codecomet.project_tartarus.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import io.codecomet.project_tartarus.ProjectTartarus;
import io.codecomet.project_tartarus.entities.components.BodyComponent;
import io.codecomet.project_tartarus.entities.components.TransformComponent;
import io.codecomet.project_tartarus.entities.systems.PhysicsDebugSystem;
import io.codecomet.project_tartarus.entities.systems.PhysicsSystem;
import io.codecomet.project_tartarus.entities.systems.RenderingSystem;
import io.codecomet.project_tartarus.scene2d.Amphitheatre;

public class GameScreen implements Screen {

    private final PooledEngine engine = new PooledEngine();
    private final SpriteBatch spriteBatch = new SpriteBatch();
    private final World world = new World(Vector2.Zero, true);

    private final Amphitheatre amphitheatre = new Amphitheatre(spriteBatch);
    private final RenderingSystem renderingSystem = new RenderingSystem(spriteBatch);

    public GameScreen() {
        ProjectTartarus.addInputProcessor(amphitheatre);

        engine.addSystem(renderingSystem);
        engine.addSystem(new PhysicsSystem(world));
        engine.addSystem(new PhysicsDebugSystem(world, renderingSystem.getCamera()));
    }

    @Override
    public void show() {
        BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyComponent.body = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(2f);
        fixtureDef.shape = circleShape;
        bodyComponent.body.createFixture(fixtureDef);
        circleShape.dispose();
        bodyComponent.body.setLinearVelocity(10,10);
        bodyComponent.body.setLinearDamping(1);


        Entity test = engine.createEntity()
            .add(engine.createComponent(TransformComponent.class))
            .add(bodyComponent);

        engine.addEntity(test);
    }

    @Override
    public void render(float delta) {
        amphitheatre.act();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        engine.update(delta);

        amphitheatre.draw();
    }

    @Override
    public void resize(int width, int height) {
        amphitheatre.resize(width,height);
        renderingSystem.resize(width, height);
    }

    @Override public void pause() { }
    @Override public void resume() { }

    @Override
    public void hide() {
        ProjectTartarus.removeInputProcessor(amphitheatre);
    }

    @Override
    public void dispose() {
        amphitheatre.dispose();
        spriteBatch.dispose();
        world.dispose();
    }
}
