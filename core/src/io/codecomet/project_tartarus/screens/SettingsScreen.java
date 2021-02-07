package io.codecomet.project_tartarus.screens;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import io.codecomet.project_tartarus.ProjectTartarus;
import io.codecomet.project_tartarus.scene2d.Amphitheatre;
import io.codecomet.project_tartarus.scene2d.actors.SettingsMenu;

public class SettingsScreen implements Screen {

    private final Amphitheatre amphitheatre = new Amphitheatre();

    public SettingsScreen() {
        amphitheatre.add(new SettingsMenu());
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
