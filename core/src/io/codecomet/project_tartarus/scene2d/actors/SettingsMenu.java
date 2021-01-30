package io.codecomet.project_tartarus.scene2d.actors;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.codecomet.project_tartarus.ProjectTartarus;
import io.codecomet.project_tartarus.scene2d.skins.MainMenuSkin;
import io.codecomet.project_tartarus.scene2d.skins.SystemSkin;
import io.codecomet.project_tartarus.screens.MenuScreen;
import io.codecomet.project_tartarus.system.config.GameConfiguration;

public class SettingsMenu extends Table {

    public SettingsMenu() {
        addTitle();
        addBackButton();
    }

    private void addTitle() {
        Label titleLabel = new Label(GameConfiguration.Translations.SETTINGS, SystemSkin.getInstance(), "title");
        add(titleLabel).spaceBottom(50).row();
    }

    private void addBackButton() {
        TextButton backButton = new TextButton(GameConfiguration.Translations.BACK, MainMenuSkin.getInstance());
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ProjectTartarus.getInstance().setScreen(new MenuScreen());
            }
        });
        addButton(backButton);
    }


    private void addButton(Button button) {
        add(button).width(200).height(50).spaceBottom(10).row();
    }

}
