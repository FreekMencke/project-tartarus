package io.codecomet.project_tartarus.system.config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public interface GameConfiguration {

    class Configuration {
        public KeyBindings.KeyMap keyMap = new KeyBindings.KeyMap();

        public boolean debug = false;
        public boolean nerdStatistics = false;
    }

    class Settings {

        private static final String PREFERENCES_KEY = "GAME_CONFIGURATION_SETTINGS";
        private static Preferences prefs;

        public static void init() {
            load();
            apply();
        }

        public static void load() {
            prefs = Gdx.app.getPreferences(Settings.PREFERENCES_KEY);
        }

        //region vSync setting
        private static final String VSYNC_KEY = "VSYNC";
        public static boolean isVSyncEnabled() {
            return prefs.getBoolean(VSYNC_KEY, true);
        }

        public static boolean toggleVSync() {
            boolean nextValue = !isVSyncEnabled();
            prefs.putBoolean(VSYNC_KEY, nextValue);
            return nextValue;
        }
        //endregion

        //region fullScreen setting
        private static final String FULLSCREEN_KEY = "FULLSCREEN";
        public static boolean isFullscreenEnabled() {
            return prefs.getBoolean(FULLSCREEN_KEY, false);
        }

        public static boolean toggleFullscreen() {
            boolean nextValue = !isFullscreenEnabled();
            prefs.putBoolean(FULLSCREEN_KEY, nextValue);
            return nextValue;
        }
        //endregion

        public static void save() {
            apply();
            prefs.flush();
        }

        private static void apply() {
            Gdx.app.log("SYSTEM", "LOADING SETTINGS");
            // vSync
            Gdx.graphics.setVSync(isVSyncEnabled());

            // fullScreen
            if(isFullscreenEnabled()) {
                Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
            } else {
                Gdx.graphics.setWindowedMode(1280,720);
            }
        }

    }

}