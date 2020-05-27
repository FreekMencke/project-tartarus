package io.codecomet.project_tartarus.scene2d.actors;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import io.codecomet.project_tartarus.ProjectTartarus;
import io.codecomet.project_tartarus.entities.components.PlayerComponent;
import io.codecomet.project_tartarus.entities.components.TransformComponent;
import io.codecomet.project_tartarus.scene2d.skins.SystemSkin;

import java.text.DecimalFormat;
import java.text.MessageFormat;

public class NerdStatistics extends Table {

    private static final int OFFSET = 8;
    private static final String DECIMAL_FORMAT_STR = "0.000";

    private Label fpsLabel;
    private Label positionLabel;
    private Label rotationLabel;

    private final PooledEngine engine;
    private final Family playerFamily;
    private final ComponentMapper<TransformComponent> transformMap = ComponentMapper.getFor(TransformComponent.class);

    public NerdStatistics() {
        this.engine = null;
        this.playerFamily = null;

        this.init();
    }

    public NerdStatistics(PooledEngine engine) {
        this.engine = engine;
        playerFamily = Family.all(PlayerComponent.class).get();

        this.init();
    }

    private void init() {
        setFillParent(true);
        align(Align.topLeft);
        pad(OFFSET);

        fpsLabel = new Label("", SystemSkin.getInstance());
        positionLabel = new Label("", SystemSkin.getInstance());
        rotationLabel = new Label("", SystemSkin.getInstance());

        add(fpsLabel).fillX().row();
        add(positionLabel).fillX().row();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        fpsLabel.setText(MessageFormat.format("FPS: {0} ", Gdx.graphics.getFramesPerSecond()));

        if (this.engine == null) return;

        ImmutableArray<Entity> entities = engine.getEntitiesFor(playerFamily);
        if(entities.size() == 0) return;

        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT_STR);
        TransformComponent transformComponent = transformMap.get(entities.first());

        positionLabel.setText(
            MessageFormat.format(
                "Transform: (x: {0}, y: {1}, rot: {2}°) ",
                decimalFormat.format(transformComponent.position.x),
                decimalFormat.format(transformComponent.position.y),
                decimalFormat.format(transformComponent.rotation)
            )
        );
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (!ProjectTartarus.config.getValue().nerdStatistics) return;

        super.draw(batch, parentAlpha);
    }

    @Override
    public void drawDebug(ShapeRenderer shapes) {
        if (!ProjectTartarus.config.getValue().nerdStatistics) return;

        super.drawDebug(shapes);
    }
}
