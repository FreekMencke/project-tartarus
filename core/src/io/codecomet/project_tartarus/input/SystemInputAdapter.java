package io.codecomet.project_tartarus.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import io.codecomet.project_tartarus.GameConfig;
import io.codecomet.project_tartarus.ProjectTartarus;

public class SystemInputAdapter extends InputAdapter {

    @Override
    public boolean keyUp(int keycode) {
        GameConfig nextConfig = ProjectTartarus.config.getValue();

        if (keycode == Input.Keys.F8) {
            nextConfig.debug = !nextConfig.debug;
            ProjectTartarus.config.onNext(nextConfig);
            return true;
        }
        if (keycode == Input.Keys.F7) {
            nextConfig.nerdStatistics = !nextConfig.nerdStatistics;
            ProjectTartarus.config.onNext(nextConfig);
            return true;
        }
        return false;
    }

}
