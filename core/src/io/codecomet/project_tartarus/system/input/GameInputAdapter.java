package io.codecomet.project_tartarus.system.input;

import com.badlogic.gdx.InputAdapter;
import io.codecomet.project_tartarus.ProjectTartarus;
import io.codecomet.project_tartarus.screens.scene2d.Amphitheatre;
import io.codecomet.project_tartarus.screens.scene2d.actors.GameMenu;
import io.codecomet.project_tartarus.system.config.KeyBindings;

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

        if (ProjectTartarus.KEY_MAP.get(KeyBindings.Action.OPEN_INGAME_MENU) == keycode) {
            if (gameMenu == null) {
                this.amphitheatre.addActor(new GameMenu());
            } else {
                gameMenu.remove();
            }
            ProjectTartarus.CONFIG.onNext(ProjectTartarus.CONFIG.getValue().togglePause());
            return true;
        }

        return false;
    }

}
