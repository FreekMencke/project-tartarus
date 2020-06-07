package io.codecomet.project_tartarus.system.input;

import com.badlogic.gdx.InputAdapter;
import io.codecomet.project_tartarus.ProjectTartarus;
import io.codecomet.project_tartarus.screens.GameScreen;
import io.codecomet.project_tartarus.system.config.GameConfiguration;
import io.codecomet.project_tartarus.system.config.KeyBindings;

public class SystemInputAdapter extends InputAdapter {

    @Override
    public boolean keyUp(int keycode) {
        GameConfiguration.Configuration config = ProjectTartarus.config.getValue();

        if (config.keyMap.get(KeyBindings.Action.TOGGLE_DEBUG) == keycode) {
            config.debug = !config.debug;
            ProjectTartarus.config.onNext(config);
            return true;
        }

        if (config.keyMap.get(KeyBindings.Action.TOGGLE_NERD_STATISTICS) == keycode) {
            config.nerdStatistics = !config.nerdStatistics;
            ProjectTartarus.config.onNext(config);
            return true;
        }

        if (config.keyMap.get(KeyBindings.Action.RESET_GAME) == keycode) {
            if (!(ProjectTartarus.getInstance().getScreen() instanceof GameScreen)) return false;
            ProjectTartarus.getInstance().setScreen(new GameScreen());
            return true;
        }

        return false;
    }

}
