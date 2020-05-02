package io.codecomet.project_tartarus;

import com.badlogic.gdx.Game;
import io.codecomet.project_tartarus.screen.MenuScreen;

public class ProjectTartarus extends Game {

	@Override
	public void create() {
		this.setScreen(new MenuScreen());
	}

}
