package io.codecomet.project_tartarus.scene2d.skins;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import io.codecomet.project_tartarus.system.managers.FontManager;

public class SystemSkin extends Skin {

    private static SystemSkin instance = null;

    public static SystemSkin getInstance() {
        return instance == null ? instance = new SystemSkin() : instance;
    }

    private SystemSkin() {
        this.buildSkin();
    }

    private void buildSkin() {
        createTitleStyle();
        createLabelStyle();
    }

    private void createTitleStyle() {
        Label.LabelStyle style = new Label.LabelStyle();

        style.font = FontManager.getInstance().getSystemFont(48);

        add("title", style);
    }

    private void createLabelStyle() {
        Label.LabelStyle style = new Label.LabelStyle();

        style.font = FontManager.getInstance().getSystemFont(16);

        add("default", style);
    }

    private void createDefaultButton() {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();

        style.font = FontManager.getInstance().getSystemFont(32);
        style.fontColor = Color.WHITE;

        style.up = newDrawable("base", Color.BROWN);
        style.over = newDrawable("base", new Color(0x5e2f0dff)); // Color.Brown 10% darker
        style.down = newDrawable("base", new Color(0x48240aff)); // Color.Brown 15% darker

        add("default", style);
    }

}
