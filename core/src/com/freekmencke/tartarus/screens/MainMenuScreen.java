package com.freekmencke.tartarus.screens;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.freekmencke.tartarus.TartarusGame;
import com.freekmencke.tartarus.screens.scene2d.Amphitheatre;
import com.freekmencke.tartarus.screens.scene2d.actors.MainMenu;
import com.freekmencke.tartarus.system.config.GameConfiguration;

public class MainMenuScreen implements Screen {

    private final Amphitheatre amphitheatre = new Amphitheatre();

    public MainMenuScreen() {
        TartarusGame.CONFIG.onNext(new GameConfiguration.Configuration());
        amphitheatre.add(new MainMenu());
    }

    @Override
    public void show() {
        TartarusGame.CONFIG.onNext(new GameConfiguration.Configuration());
        TartarusGame.addInputProcessor(amphitheatre);
    }

    @Override
    public void render(float delta) {
        amphitheatre.act();
        ScreenUtils.clear(0,0,0,1);
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
