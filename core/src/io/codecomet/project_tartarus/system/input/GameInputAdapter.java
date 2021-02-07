package io.codecomet.project_tartarus.system.input;

import com.badlogic.gdx.InputAdapter;
import io.codecomet.project_tartarus.ProjectTartarus;
import io.codecomet.project_tartarus.scene2d.Amphitheatre;
import io.codecomet.project_tartarus.scene2d.actors.GameMenu;
import io.codecomet.project_tartarus.system.config.GameConfiguration;
import io.codecomet.project_tartarus.system.config.KeyBindings;

public class GameInputAdapter extends InputAdapter {

    private final Amphitheatre amphitheatre;

    private GameMenu gameMenu;

    public GameInputAdapter(Amphitheatre amphitheatre) {
        this.amphitheatre = amphitheatre;
    }

    @Override
    public boolean keyDown(int keycode) {
        GameConfiguration.Configuration config = ProjectTartarus.config.getValue();

        if (config.keyMap.get(KeyBindings.Action.OPEN_INGAME_MENU) == keycode) {
            if(config.paused = !amphitheatre.getActors().contains(gameMenu, true)) {
                this.amphitheatre.addActor(gameMenu = new GameMenu());
            } else {
                gameMenu.remove();
                gameMenu = null;
            }
            ProjectTartarus.config.onNext(config);
            return true;
        }

        return false;
    }

}
