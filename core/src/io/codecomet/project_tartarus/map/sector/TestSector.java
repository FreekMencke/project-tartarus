package io.codecomet.project_tartarus.map.sector;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import io.codecomet.project_tartarus.map.object.misc.Pot;

public class TestSector implements Disposable {

    private final PooledEngine engine;
    private final World world;

    public final ImmutableArray<Entity> entities;

    public TestSector(PooledEngine engine, World world) {
        this.engine = engine;
        this.world = world;
        this.entities = new ImmutableArray<>(createEntities());
    }

    private Array<Entity> createEntities() {
        Array<Entity> entities = new Array<>();

        entities.add(Pot.defaultPotBuilder(engine, world).setTransform(new Vector3(1.28f, 0, 0)).build());
        entities.add(Pot.defaultPotBuilder(engine, world).setTransform(new Vector3(1.0f, .28f, 0)).build());
        entities.add(Pot.largePotBuilder(engine, world).setTransform(new Vector3(1.28f, .28f, 0)).build());
        entities.add(Pot.largePotBuilder(engine, world).setTransform(new Vector3(1f, 0, 0)).build());

        return entities;
    }

    @Override
    public void dispose() {
        this.entities.forEach(engine::removeEntity);
    }
}
