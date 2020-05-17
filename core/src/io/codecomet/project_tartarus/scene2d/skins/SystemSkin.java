package io.codecomet.project_tartarus.scene2d.skins;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import io.codecomet.project_tartarus.managers.FontManager;

public class SystemSkin extends Skin {

    private static SystemSkin instance = null;
    public static SystemSkin getInstance() {
        return instance == null ? instance = new SystemSkin() : instance;
    }
    private SystemSkin() {
        this.buildSkin();
    }

    private void buildSkin() {
        createLabelStyle();
    }

    private void createLabelStyle() {
        Label.LabelStyle style = new Label.LabelStyle();

        style.font = FontManager.getInstance().getSystemFont(16);

        add("default", style);
    }

}
