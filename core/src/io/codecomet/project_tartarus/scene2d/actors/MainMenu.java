package io.codecomet.project_tartarus.scene2d.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.codecomet.project_tartarus.ProjectTartarus;
import io.codecomet.project_tartarus.config.GameConfiguration;
import io.codecomet.project_tartarus.scene2d.skins.MainMenuSkin;
import io.codecomet.project_tartarus.screens.GameScreen;

public class MainMenu extends Table {

    public MainMenu() {
        addTitle();
        addStartButton();
        addSettingsButton();
        addExitButton();
    }

    private void addTitle() {
        Label titleLabel = new Label(GameConfiguration.Defaults.GAME_TITLE, MainMenuSkin.getInstance(), "title");
        add(titleLabel).spaceBottom(50).row();
    }

    private void addStartButton() {
        TextButton startButton = new TextButton("Start", MainMenuSkin.getInstance());
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ProjectTartarus.getInstance().setScreen(new GameScreen());
            }
        });
        addButton(startButton);
    }

    private void addSettingsButton() {
        TextButton settingsButton = new TextButton("Settings", MainMenuSkin.getInstance());
        addButton(settingsButton);
    }

    private void addExitButton() {
        TextButton exitButton = new TextButton("Exit", MainMenuSkin.getInstance());
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        addButton(exitButton);
    }

    private void addButton(Button button) {
        add(button).width(250).height(60).spaceBottom(20).row();
    }

}
