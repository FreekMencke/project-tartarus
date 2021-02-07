package io.codecomet.project_tartarus.system.config;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.ObjectMap;

public interface KeyBindings {

    final class Action {
        // GAME
        public static final String OPEN_INGAME_MENU = "INGAME_MENU";

        // CHARACTER MOVEMENT
        public static final String UP = "UP";
        public static final String RIGHT = "RIGHT";
        public static final String DOWN = "DOWN";
        public static final String LEFT = "LEFT";

        // SYSTEM
        public static final String TOGGLE_DEBUG = "TOGGLE_DEBUG";
        public static final String TOGGLE_NERD_STATISTICS = "TOGGLE_NERD_STATISTICS";
        public static final String RESET_GAME = "RESET_TO_MAIN_MENU";
    }

    /**
     * A KeyMap is a map containing entries that have a "KeyBindings.Action" action with a "Input.Keys" key.
     */
    class KeyMap extends ObjectMap<String, Integer> {
        public KeyMap() {
            this.setupGameControls();
            this.setupMovementControls();
            this.setupSystemControls();
        }

        private void setupGameControls() {
            put(Action.OPEN_INGAME_MENU, Input.Keys.ESCAPE);
        }

        private void setupMovementControls() {
            put(Action.UP, Input.Keys.W);
            put(Action.RIGHT, Input.Keys.D);
            put(Action.DOWN, Input.Keys.S);
            put(Action.LEFT, Input.Keys.A);
        }

        private void setupSystemControls() {
            put(Action.TOGGLE_NERD_STATISTICS, Input.Keys.F7);
            put(Action.TOGGLE_DEBUG, Input.Keys.F8);

            put(Action.RESET_GAME, Input.Keys.F12);
        }
    }

}


