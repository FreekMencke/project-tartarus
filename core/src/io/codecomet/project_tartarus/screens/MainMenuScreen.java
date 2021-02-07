package io.codecomet.project_tartarus.screens;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import io.codecomet.project_tartarus.ProjectTartarus;
import io.codecomet.project_tartarus.scene2d.Amphitheatre;
import io.codecomet.project_tartarus.scene2d.actors.MainMenu;

public class MainMenuScreen implements Screen {

    private final Amphitheatre amphitheatre = new Amphitheatre();

    public MainMenuScreen() {
        amphitheatre.add(new MainMenu());
    }

    @Override
    public void show() {
        ProjectTartarus.addInputProcessor(amphitheatre);
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
        ProjectTartarus.removeInputProcessor(amphitheatre);
    }

    @Override
    public void dispose() {
        amphitheatre.dispose();
    }

}
