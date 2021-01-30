package io.codecomet.project_tartarus.system.config;

public interface GameConfiguration {

    final class Defaults {
        public static final String GAME_TITLE = "Project Tartarus";
    }

    final class Translations {
        public static final String START = "Start";
        public static final String SETTINGS = "Settings";
        public static final String EXIT = "Exit";
        public static final String BACK = "Back";
    }


    class Configuration {
        public KeyBindings.KeyMap keyMap = new KeyBindings.KeyMap();

        public boolean debug = false;
        public boolean nerdStatistics = false;
    }

}