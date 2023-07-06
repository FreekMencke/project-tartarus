package com.freekmencke.tartarus.screens.scene2d;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.freekmencke.tartarus.TartarusGame;
import com.freekmencke.tartarus.screens.scene2d.actors.NerdStatistics;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

/**
 * Custom implementation of libGDX Stage.
 * Includes NerdStatistics and config subscription (debug).
 */
public class Amphitheatre extends Stage {

    private final CompositeDisposable subscriptions = new CompositeDisposable();
    private final Table content;

    public Amphitheatre() {
        this(new SpriteBatch(), new NerdStatistics());
    }

    public Amphitheatre(Batch batch, NerdStatistics nerdStatistics) {
        super(new ScreenViewport(), batch);

        subscriptions.add(TartarusGame.CONFIG.subscribe(config -> setDebugAll(config.debug)));

        content = new Table();
        content.setFillParent(true);
        addActor(content);

        addActor(nerdStatistics);
    }

    @Override
    public void dispose() {
        super.dispose();
        subscriptions.dispose();
    }

    public void resize(int width, int height) {
        getViewport().update(width, height, true);
    }

    public Table add(Actor... actor) {
        return content.add(actor);
    }

}
