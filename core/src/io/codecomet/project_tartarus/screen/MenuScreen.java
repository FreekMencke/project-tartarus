package io.codecomet.project_tartarus.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.codecomet.project_tartarus.GameConfig;
import io.codecomet.project_tartarus.scene2d.Menu;

public class MenuScreen implements Screen {

    private final Stage stage = new Stage(new ScreenViewport());

    public MenuScreen() {
        stage.setDebugAll(GameConfig.DEBUG);

        Table content = new Table();
        content.setFillParent(true);
        stage.addActor(content);

        content.add(new Menu());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.act();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override public void pause() { }
    @Override public void resume() { }
    @Override public void hide() { }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
