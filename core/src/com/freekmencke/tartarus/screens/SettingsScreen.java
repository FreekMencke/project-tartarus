package com.freekmencke.tartarus.screens;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.freekmencke.tartarus.TartarusGame;
import com.freekmencke.tartarus.screens.scene2d.Amphitheatre;
import com.freekmencke.tartarus.screens.scene2d.actors.SettingsMenu;
import com.freekmencke.tartarus.system.config.GameConfiguration;

public class SettingsScreen implements Screen {

    private final Amphitheatre amphitheatre = new Amphitheatre();

    public SettingsScreen() {
        TartarusGame.CONFIG.onNext(new GameConfiguration.Configuration());
        amphitheatre.add(new SettingsMenu());
    }

    @Override
    public void show() {
        TartarusGame.addInputProcessor(amphitheatre);
    }

    @Override
    public void render(float delta) {
        amphitheatre.act();
        ScreenUtils.clear(0, 0, 0, 1);
        amphitheatre.draw();
    }

    @Override
    public void resize(int width, int height) {
        amphitheatre.resize(width, height);
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() {
        TartarusGame.removeInputProcessor(amphitheatre);
    }

    @Override
    public void dispose() {
        amphitheatre.dispose();
    }

}
