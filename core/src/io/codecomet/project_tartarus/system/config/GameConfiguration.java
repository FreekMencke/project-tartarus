package io.codecomet.project_tartarus.system.config;

public interface GameConfiguration {

    final class Defaults {
        public static final String GAME_TITLE = "Project Tartarus";
    }


    class Configuration {
        public KeyBindings.KeyMap keyMap = new KeyBindings.KeyMap();

        public boolean debug = false;
        public boolean nerdStatistics = false;
    }

}