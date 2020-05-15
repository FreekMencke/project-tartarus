package io.codecomet.project_tartarus.scene2d.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import io.codecomet.project_tartarus.GameConfig;
import io.codecomet.project_tartarus.ProjectTartarus;
import io.codecomet.project_tartarus.managers.FontManager;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

import java.text.MessageFormat;

public class NerdStatistics extends Actor implements Disposable {

    private static final int OFFSET = 8;

    private final BitmapFont font = FontManager.getInstance().getDefaultFont(16);
    private final CompositeDisposable subscriptions = new CompositeDisposable();

    private GameConfig config = ProjectTartarus.config.getValue();

    public NerdStatistics() {
        subscriptions.add(ProjectTartarus.config.subscribe(config -> this.config = config));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (!config.fpsCounter) return;

        font.setColor(Color.WHITE);
        GlyphLayout fpsText = font.draw(
            batch,
            MessageFormat.format("FPS: {0}", Gdx.graphics.getFramesPerSecond()),
            OFFSET, (getStage().getHeight() - OFFSET)
        );
        setBounds(OFFSET, (getStage().getHeight() - OFFSET) - fpsText.height, fpsText.width, fpsText.height);
    }

    @Override
    public void drawDebug(ShapeRenderer shapes) {
        if (!config.fpsCounter) return;
        super.drawDebug(shapes);
    }

    @Override
    public void dispose() {
        subscriptions.dispose();
    }

}
