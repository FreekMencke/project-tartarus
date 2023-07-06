package com.freekmencke.tartarus.system.input;

import com.badlogic.gdx.InputAdapter;
import com.freekmencke.tartarus.TartarusGame;
import com.freekmencke.tartarus.screens.scene2d.Amphitheatre;
import com.freekmencke.tartarus.screens.scene2d.actors.GameMenu;
import com.freekmencke.tartarus.system.config.KeyBindings;

import java.util.Arrays;

public class GameInputAdapter extends InputAdapter {

    private final Amphitheatre amphitheatre;

    public GameInputAdapter(Amphitheatre amphitheatre) {
        this.amphitheatre = amphitheatre;
    }

    @Override
    public boolean keyDown(int keycode) {
        GameMenu gameMenu = (GameMenu) Arrays.stream(amphitheatre.getActors().toArray())
                .filter(a -> a instanceof GameMenu)
                .findFirst()
                .orElse(null);

        if (TartarusGame.KEY_MAP.get(KeyBindings.Action.OPEN_INGAME_MENU) == keycode) {
            if (gameMenu == null) {
                this.amphitheatre.addActor(new GameMenu());
            } else {
                gameMenu.remove();
            }
            TartarusGame.CONFIG.onNext(TartarusGame.CONFIG.getValue().togglePause());
            return true;
        }

        return false;
    }

}
