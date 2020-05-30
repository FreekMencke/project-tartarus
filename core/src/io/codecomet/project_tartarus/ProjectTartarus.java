package io.codecomet.project_tartarus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import io.codecomet.project_tartarus.config.GameConfiguration;
import io.codecomet.project_tartarus.input.SystemInputAdapter;
import io.codecomet.project_tartarus.screens.MenuScreen;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class ProjectTartarus extends Game {

    public static final BehaviorSubject<GameConfiguration.Configuration> config = BehaviorSubject.createDefault(new GameConfiguration.Configuration());

    @Override
    public void create() {
        Gdx.input.setInputProcessor(new InputMultiplexer());
        ProjectTartarus.addInputProcessor(new SystemInputAdapter());
        this.setScreen(new MenuScreen());
    }

    public static ProjectTartarus getInstance() {
        return (ProjectTartarus) Gdx.app.getApplicationListener();
    }

    public static void addInputProcessor(InputProcessor inputProcessor) {
        InputMultiplexer inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
        inputMultiplexer.addProcessor(inputProcessor);
    }

    public static void removeInputProcessor(InputProcessor inputProcessor) {
        InputMultiplexer inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
        inputMultiplexer.removeProcessor(inputProcessor);
    }
}
