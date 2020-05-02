package io.codecomet.project_tartarus.scene2d;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import io.codecomet.project_tartarus.GameConfig;
import io.codecomet.project_tartarus.managers.FontManager;

public class Menu extends Table {

    public Menu() {
        add(generateTitle());
    }

    private Actor generateTitle() {
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = FontManager.getInstance().getDefaultFont(64);

        return new Label(GameConfig.GAME_TITLE, style);
    }

}
