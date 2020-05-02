package io.codecomet.project_tartarus.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import io.codecomet.project_tartarus.ProjectTartarus;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		config.setTitle("Project Tartarus");
		config.setWindowedMode(1280,720);

		new Lwjgl3Application(new ProjectTartarus(), config);
	}
}
