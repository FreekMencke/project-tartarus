package io.codecomet.project_tartarus.scene2d.actors;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import io.codecomet.project_tartarus.ProjectTartarus;
import io.codecomet.project_tartarus.entities.components.PlayerComponent;
import io.codecomet.project_tartarus.entities.components.TransformComponent;
import io.codecomet.project_tartarus.managers.FontManager;
import io.codecomet.project_tartarus.scene2d.skins.SystemSkin;

import java.text.DecimalFormat;
import java.text.MessageFormat;

public class NerdStatistics extends Table {

    private static final int OFFSET = 8;
    private static final String DECIMAL_FORMAT_STR = "0.000";

    private final BitmapFont font = FontManager.getInstance().getDefaultFont(16);

    private Label fpsLabel;
    private Label positionLabel;

    private final PooledEngine engine;
    private final Family playerFamily;
    private final ComponentMapper<TransformComponent> transformMap = ComponentMapper.getFor(TransformComponent.class);
    private ImmutableArray<Entity> entities = new ImmutableArray<>(new Array<>());

    public NerdStatistics() {
        super();
        this.engine = null;
        this.playerFamily = null;

        this.init();
    }

    public NerdStatistics(PooledEngine engine) {
        super();
        this.engine = engine;
        playerFamily = Family.all(PlayerComponent.class, TransformComponent.class).get();

        this.init();
    }

    private void init() {
        setFillParent(true);
        align(Align.topLeft);


        fpsLabel = new Label("", SystemSkin.getInstance());
        positionLabel = new Label("", SystemSkin.getInstance());

        pad(8);

        add(fpsLabel).fillX().row();
        add(positionLabel).fillX().row();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT_STR);

        fpsLabel.setText(MessageFormat.format("FPS: {0} ", Gdx.graphics.getFramesPerSecond()));

        if (this.engine == null) return;
        entities = engine.getEntitiesFor(playerFamily);
        if(entities.size() == 0) return;

        Vector3 position = transformMap.get(entities.first()).position;
        positionLabel.setText(MessageFormat.format("Coords: x: {0}, y: {1} ", decimalFormat.format(position.x), decimalFormat.format(position.y)));
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
