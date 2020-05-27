package io.codecomet.project_tartarus.screens;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import io.codecomet.project_tartarus.ProjectTartarus;
import io.codecomet.project_tartarus.entities.factories.PlayerEntityFactory;
import io.codecomet.project_tartarus.entities.systems.ControllerMovementSystem;
import io.codecomet.project_tartarus.entities.systems.PhysicsDebugSystem;
import io.codecomet.project_tartarus.entities.systems.PhysicsSystem;
import io.codecomet.project_tartarus.entities.systems.RenderingSystem;
import io.codecomet.project_tartarus.input.ControllerInputAdapter;
import io.codecomet.project_tartarus.scene2d.Amphitheatre;
import io.codecomet.project_tartarus.scene2d.actors.NerdStatistics;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class GameScreen implements Screen {

    private final PooledEngine engine = new PooledEngine();
    private final SpriteBatch spriteBatch = new SpriteBatch();
    private final World world = new World(Vector2.Zero, true);

    private final Amphitheatre amphitheatre = new Amphitheatre(spriteBatch, new NerdStatistics(engine));
    private final ControllerInputAdapter controllerInputAdapter = new ControllerInputAdapter(engine);

    private final RenderingSystem renderingSystem = new RenderingSystem(spriteBatch);
    private final PhysicsDebugSystem physicsDebugSystem = new PhysicsDebugSystem(world, renderingSystem.getCamera());

    private final CompositeDisposable subscriptions = new CompositeDisposable();

    public GameScreen() {
        engine.addSystem(renderingSystem);
        engine.addSystem(new PhysicsSystem(world));
        engine.addSystem(new ControllerMovementSystem(renderingSystem.getCamera()));

        subscriptions.add(
            ProjectTartarus.config.subscribe(config -> {
                if (config.debug) engine.addSystem(physicsDebugSystem);
                else engine.removeSystem(physicsDebugSystem);
            })
        );
    }

    @Override
    public void show() {
        ProjectTartarus.addInputProcessor(amphitheatre);
        ProjectTartarus.addInputProcessor(controllerInputAdapter);

        // CREATE PLAYER
        engine.addEntity(PlayerEntityFactory.create(engine, world));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        engine.update(delta);

        amphitheatre.act();
        amphitheatre.draw();
    }

    @Override
    public void resize(int width, int height) {
        amphitheatre.resize(width, height);
        renderingSystem.resize(width, height);
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() {
        ProjectTartarus.removeInputProcessor(amphitheatre);
        ProjectTartarus.removeInputProcessor(controllerInputAdapter);
    }

    @Override
    public void dispose() {
        subscriptions.dispose();
        amphitheatre.dispose();
        spriteBatch.dispose();
        world.dispose();
    }
}
