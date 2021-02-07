package io.codecomet.project_tartarus.screens;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import io.codecomet.project_tartarus.ProjectTartarus;
import io.codecomet.project_tartarus.entities.factories.GridEntityFactory;
import io.codecomet.project_tartarus.entities.factories.PlayerEntityFactory;
import io.codecomet.project_tartarus.entities.input.ControllerInputAdapter;
import io.codecomet.project_tartarus.entities.systems.*;
import io.codecomet.project_tartarus.map.area.TestArea;
import io.codecomet.project_tartarus.scene2d.Amphitheatre;
import io.codecomet.project_tartarus.scene2d.actors.NerdStatistics;
import io.codecomet.project_tartarus.system.input.GameInputAdapter;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class GameScreen implements Screen {

    private final CompositeDisposable subscriptions = new CompositeDisposable();

    private final PooledEngine engine = new PooledEngine();
    private final SpriteBatch spriteBatch = new SpriteBatch();
    private final World world = new World(Vector2.Zero, true);

    private final Amphitheatre amphitheatre = new Amphitheatre(spriteBatch, new NerdStatistics(engine));
    private final GameInputAdapter gameInputAdapter = new GameInputAdapter(amphitheatre);
    private final ControllerInputAdapter controllerInputAdapter = new ControllerInputAdapter(engine);

    private final RenderingSystem renderingSystem = new RenderingSystem(spriteBatch);
    private final PhysicsDebugRenderingSystem physicsDebugRenderingSystem = new PhysicsDebugRenderingSystem(world, renderingSystem.getCamera());

    public GameScreen() {
        Array<EntitySystem> pausableSystems = new Array<>();
        pausableSystems.add(new PhysicsSystem(world));
        pausableSystems.add(new ControllerMovementSystem(renderingSystem.getCamera()));
        pausableSystems.add(new PlayerCameraSystem(renderingSystem.getCamera())); // AFTER PHYSICS + BEFORE RENDER

        pausableSystems.forEach(engine::addSystem);

        engine.addSystem(renderingSystem);
        engine.addSystem(physicsDebugRenderingSystem);

        subscriptions.add(ProjectTartarus.config.subscribe(config -> {
            pausableSystems.forEach(ps -> ps.setProcessing(!config.paused));
            physicsDebugRenderingSystem.setProcessing(config.debug);
        }));
    }

    @Override
    public void show() {
        ProjectTartarus.addInputProcessor(amphitheatre);
        ProjectTartarus.addInputProcessor(gameInputAdapter);
        ProjectTartarus.addInputProcessor(controllerInputAdapter);

        engine.addEntity(GridEntityFactory.create(engine)); // CREATE TEST GRID
        engine.addEntity(PlayerEntityFactory.create(engine, world)); // CREATE PLAYER

        new TestArea(engine, world).load();
    }

    @Override
    public void render(float delta) {
        amphitheatre.act();

        ScreenUtils.clear(0,0,0,1);

        engine.update(delta);
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
        ProjectTartarus.removeInputProcessor(gameInputAdapter);
        ProjectTartarus.removeInputProcessor(controllerInputAdapter);
    }

    @Override
    public void dispose() {
        subscriptions.dispose();
        engine.removeAllEntities();
        engine.clearPools();
        world.dispose();
        amphitheatre.dispose();
        spriteBatch.dispose();
    }

}
