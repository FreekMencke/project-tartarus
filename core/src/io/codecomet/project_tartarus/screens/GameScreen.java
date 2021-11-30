package io.codecomet.project_tartarus.screens;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
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
import io.codecomet.project_tartarus.map.district.TestDistrict;
import io.codecomet.project_tartarus.screens.scene2d.Amphitheatre;
import io.codecomet.project_tartarus.screens.scene2d.actors.NerdStatistics;
import io.codecomet.project_tartarus.system.config.GameConfiguration;
import io.codecomet.project_tartarus.system.input.GameInputAdapter;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class GameScreen implements Screen {

    private final CompositeDisposable subscriptions = new CompositeDisposable();

    private final PooledEngine engine = new PooledEngine();
    private final SpriteBatch spriteBatch = new SpriteBatch();

    private final Amphitheatre amphitheatre = new Amphitheatre(spriteBatch, new NerdStatistics(engine));
    private final GameInputAdapter gameInputAdapter = new GameInputAdapter(amphitheatre);
    private final ControllerInputAdapter controllerInputAdapter = new ControllerInputAdapter(engine);

    private final World world = new World(Vector2.Zero, true);

    private ImmutableArray<EntitySystem> pausableSystems = new ImmutableArray<>(new Array<>());
    private final RenderingSystem renderingSystem = new RenderingSystem(spriteBatch);
    private final LightingSystem lightingSystem = new LightingSystem(world, renderingSystem.getCamera());
    private final PhysicsDebugRenderingSystem physicsDebugRenderingSystem = new PhysicsDebugRenderingSystem(world, renderingSystem.getCamera());

    public GameScreen() {
        ProjectTartarus.CONFIG.onNext(new GameConfiguration.Configuration());

        subscriptions.add(ProjectTartarus.CONFIG.subscribe(c -> {
            pausableSystems.forEach(ps -> ps.setProcessing(!c.paused));
            physicsDebugRenderingSystem.setProcessing(c.debug);
        }));

        loadSystems();
        loadWorld();
    }

    @Override
    public void show() {
        ProjectTartarus.addInputProcessor(amphitheatre);
        ProjectTartarus.addInputProcessor(gameInputAdapter);
        ProjectTartarus.addInputProcessor(controllerInputAdapter);
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
    }

    /** Add order is important. Systems will be run in the order they are added. */
    private void loadSystems() {
        Array<EntitySystem> pausableSystems = new Array<>();
        pausableSystems.add(new PhysicsSystem(world));
        pausableSystems.add(new ControllerMovementSystem(renderingSystem.getCamera()));
        pausableSystems.add(new PlayerCameraSystem(renderingSystem.getCamera())); // AFTER PHYSICS + BEFORE RENDER
        this.pausableSystems = new ImmutableArray<>(pausableSystems);

        this.pausableSystems.forEach(engine::addSystem);
        engine.addSystem(renderingSystem);
        engine.addSystem(lightingSystem);
        engine.addSystem(physicsDebugRenderingSystem);
    }

    /** Create game world. */
    private void loadWorld() {
        engine.addEntity(GridEntityFactory.create(engine)); // CREATE TEST GRID
        engine.addEntity(PlayerEntityFactory.create(engine, world)); // CREATE PLAYER

        new TestDistrict(engine, world, new Vector2()).load();
    }

}
