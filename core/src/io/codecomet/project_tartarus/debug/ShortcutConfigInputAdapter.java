package io.codecomet.project_tartarus.debug;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import io.codecomet.project_tartarus.GameConfig;
import io.codecomet.project_tartarus.ProjectTartarus;

public class ShortcutConfigInputAdapter extends InputAdapter {

    @Override
    public boolean keyUp(int keycode) {
        GameConfig nextConfig = ProjectTartarus.config.getValue();

        if (keycode == Input.Keys.F8) {
            nextConfig.debug = !nextConfig.debug;
            ProjectTartarus.config.onNext(nextConfig);
            return true;
        }
        if (keycode == Input.Keys.F7) {
            nextConfig.fpsCounter = !nextConfig.fpsCounter;
            ProjectTartarus.config.onNext(nextConfig);
            return true;
        }
        return false;
    }

}
