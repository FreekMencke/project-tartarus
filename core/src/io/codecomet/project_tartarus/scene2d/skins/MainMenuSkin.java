package io.codecomet.project_tartarus.scene2d.skins;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import io.codecomet.project_tartarus.managers.FontManager;

public class MainMenuSkin extends Skin {

    private static MainMenuSkin instance = null;

    public static MainMenuSkin getInstance() {
        return instance == null ? instance = new MainMenuSkin() : instance;
    }

    private MainMenuSkin() {
        this.buildSkin();
    }

    private void buildSkin() {
        createBaseTexture();

        createTitleStyle();
        createDefaultButton();
    }

    private void createBaseTexture() {
        // Generate a 1x1 white texture and store it in the skin named "base".
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);

        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        add("base", new Texture(pixmap));
    }

    private void createTitleStyle() {
        Label.LabelStyle style = new Label.LabelStyle();

        style.font = FontManager.getInstance().getDefaultFont(64);

        add("title", style);
    }

    private void createDefaultButton() {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();

        style.font = FontManager.getInstance().getDefaultFont(32);
        style.fontColor = Color.WHITE;

        style.up = newDrawable("base", Color.BROWN);
        style.over = newDrawable("base", new Color(0x5e2f0dff)); // Color.Brown 10% darker
        style.down = newDrawable("base", new Color(0x48240aff)); // Color.Brown 15% darker

        add("default", style);
    }

}
