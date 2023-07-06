package com.freekmencke.tartarus.system.input;

import com.badlogic.gdx.InputAdapter;
import com.freekmencke.tartarus.TartarusGame;
import com.freekmencke.tartarus.screens.GameScreen;
import com.freekmencke.tartarus.system.config.KeyBindings;

public class SystemInputAdapter extends InputAdapter {

    @Override
    public boolean keyDown(int keycode) {
        if (TartarusGame.KEY_MAP.get(KeyBindings.Action.TOGGLE_DEBUG) == keycode) {
            TartarusGame.CONFIG.onNext(TartarusGame.CONFIG.getValue().toggleDebug());
            return true;
        }

        if (TartarusGame.KEY_MAP.get(KeyBindings.Action.TOGGLE_NERD_STATISTICS) == keycode) {
            TartarusGame.CONFIG.onNext(TartarusGame.CONFIG.getValue().toggleNerdStatistics());
            return true;
        }

        if (TartarusGame.KEY_MAP.get(KeyBindings.Action.RESET_GAME) == keycode) {
            if (!(TartarusGame.getInstance().getScreen() instanceof GameScreen)) return false;
            TartarusGame.getInstance().setScreen(new GameScreen());
            return true;
        }

        return false;
    }

}
