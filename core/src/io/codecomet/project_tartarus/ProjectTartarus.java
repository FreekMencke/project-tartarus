package io.codecomet.project_tartarus;

import com.badlogic.gdx.*;
import io.codecomet.project_tartarus.screens.MenuScreen;
import io.codecomet.project_tartarus.system.config.GameConfiguration;
import io.codecomet.project_tartarus.system.input.SystemInputAdapter;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

import java.text.MessageFormat;

public class ProjectTartarus extends Game {

    public static final BehaviorSubject<GameConfiguration.Configuration> config = BehaviorSubject.createDefault(new GameConfiguration.Configuration());

    @Override
    public void create() {
        GameConfiguration.Settings.init();
        Gdx.input.setInputProcessor(new InputMultiplexer());
        ProjectTartarus.addInputProcessor(new SystemInputAdapter());
        this.setScreen(new MenuScreen());
    }

    public static ProjectTartarus getInstance() {
        return (ProjectTartarus) Gdx.app.getApplicationListener();
    }

    @Override
    public void setScreen(Screen screen) {
        setScreen(screen, true);
    }

    public void setScreen(Screen screen, boolean dispose) {
        Gdx.app.log("SYSTEM", MessageFormat.format("SET SCREEN \"{0}\"", screen.getClass().getSimpleName()));

        if (this.screen != null) {
            Gdx.app.log(getCurrentScreenName(), "Hide");
            this.screen.hide();
            if(dispose) {
                Gdx.app.log(getCurrentScreenName(), "Dispose");
                this.screen.dispose();
            }
        }

        this.screen = screen;
        Gdx.app.log(getCurrentScreenName(), "Show");
        this.screen.show();
        Gdx.app.log(getCurrentScreenName(), MessageFormat.format("Resize to (X: {0}, Y: {1})", Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void resize(int width, int height) {
        if (screen == null) return;
        Gdx.app.log(getCurrentScreenName(), MessageFormat.format("Resize to (X: {0}, Y: {1})", width, height));
        screen.resize(width, height);
    }

    public static void addInputProcessor(InputProcessor inputProcessor) {
        Gdx.app.log("SYSTEM", MessageFormat.format("ADD INPUT_PROCESSOR \"{0}\"", inputProcessor.getClass().getSimpleName()));
        ((InputMultiplexer) Gdx.input.getInputProcessor()).addProcessor(inputProcessor);
    }

    public static void removeInputProcessor(InputProcessor inputProcessor) {
        Gdx.app.log("SYSTEM", MessageFormat.format("REMOVE INPUT_PROCESSOR \"{0}\"", inputProcessor.getClass().getSimpleName()));
        ((InputMultiplexer) Gdx.input.getInputProcessor()).removeProcessor(inputProcessor);
    }

    private static String getCurrentScreenName() {
        return getInstance().getScreen().getClass().getSimpleName();
    }
}
