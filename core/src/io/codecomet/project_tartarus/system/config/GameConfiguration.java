package io.codecomet.project_tartarus.system.config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.Map;

public interface GameConfiguration {

    class Configuration {
        public KeyBindings.KeyMap keyMap = new KeyBindings.KeyMap();

        public boolean debug = false;
        public boolean nerdStatistics = false;
    }

    class Settings {

        private static final String PREFERENCES_KEY = "GAME_CONFIGURATION_SETTINGS";
        private static Map<String, ?> savedPrefs;
        private static Preferences unsavedPrefs;

        public static void init() {
            unsavedPrefs = Gdx.app.getPreferences(Settings.PREFERENCES_KEY);
            savedPrefs = unsavedPrefs.get();
            apply();
        }

        public static void load() {
            unsavedPrefs.put(savedPrefs);
        }

        //region vSync setting
        private static final String VSYNC_KEY = "VSYNC";
        public static boolean isVSyncEnabled() {
            return unsavedPrefs.getBoolean(VSYNC_KEY, true);
        }

        public static boolean toggleVSync() {
            boolean nextValue = !isVSyncEnabled();
            unsavedPrefs.putBoolean(VSYNC_KEY, nextValue);
            return nextValue;
        }
        //endregion

        //region fullScreen setting
        private static final String FULLSCREEN_KEY = "FULLSCREEN";
        public static boolean isFullscreenEnabled() {
            return unsavedPrefs.getBoolean(FULLSCREEN_KEY, false);
        }

        public static boolean toggleFullscreen() {
            boolean nextValue = !isFullscreenEnabled();
            unsavedPrefs.putBoolean(FULLSCREEN_KEY, nextValue);
            return nextValue;
        }
        //endregion

        public static void save() {
            apply();
            unsavedPrefs.flush();
            savedPrefs = unsavedPrefs.get();
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