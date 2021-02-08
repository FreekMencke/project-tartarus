package io.codecomet.project_tartarus.system.input;

import com.badlogic.gdx.InputAdapter;
import io.codecomet.project_tartarus.ProjectTartarus;
import io.codecomet.project_tartarus.screens.GameScreen;
import io.codecomet.project_tartarus.system.config.KeyBindings;

public class SystemInputAdapter extends InputAdapter {

    @Override
    public boolean keyDown(int keycode) {
        if (ProjectTartarus.KEY_MAP.get(KeyBindings.Action.TOGGLE_DEBUG) == keycode) {
            ProjectTartarus.CONFIG.onNext(ProjectTartarus.CONFIG.getValue().toggleDebug());
            return true;
        }

        if (ProjectTartarus.KEY_MAP.get(KeyBindings.Action.TOGGLE_NERD_STATISTICS) == keycode) {
            ProjectTartarus.CONFIG.onNext(ProjectTartarus.CONFIG.getValue().toggleNerdStatistics());
            return true;
        }

        if (ProjectTartarus.KEY_MAP.get(KeyBindings.Action.RESET_GAME) == keycode) {
            if (!(ProjectTartarus.getInstance().getScreen() instanceof GameScreen)) return false;
            ProjectTartarus.getInstance().setScreen(new GameScreen());
            return true;
        }

        return false;
    }

}
