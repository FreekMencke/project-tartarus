package io.codecomet.project_tartarus.input;

import com.badlogic.gdx.InputAdapter;
import io.codecomet.project_tartarus.ProjectTartarus;
import io.codecomet.project_tartarus.config.GameConfiguration;
import io.codecomet.project_tartarus.config.KeyBindings;
import io.codecomet.project_tartarus.screens.MenuScreen;

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

        if (config.keyMap.get(KeyBindings.Action.RESET_TO_MAIN_MENU) == keycode) {
            ProjectTartarus.getInstance().setScreen(new MenuScreen());
            return true;
        }

        return false;
    }

}
