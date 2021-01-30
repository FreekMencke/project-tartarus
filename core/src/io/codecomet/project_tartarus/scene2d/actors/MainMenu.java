package io.codecomet.project_tartarus.scene2d.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.codecomet.project_tartarus.ProjectTartarus;
import io.codecomet.project_tartarus.scene2d.skins.MainMenuSkin;
import io.codecomet.project_tartarus.screens.GameScreen;
import io.codecomet.project_tartarus.screens.SettingsScreen;
import io.codecomet.project_tartarus.system.config.TranslationDictionary;

public class MainMenu extends Table {

    public MainMenu() {
        addTitle();
        addStartButton();
        addSettingsButton();
        addExitButton();
    }

    private void addTitle() {
        Label titleLabel = new Label(TranslationDictionary.Defaults.GAME_TITLE, MainMenuSkin.getInstance(), "title");
        add(titleLabel).spaceBottom(50).row();
    }

    private void addStartButton() {
        TextButton startButton = new TextButton(TranslationDictionary.Translations.START, MainMenuSkin.getInstance());
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ProjectTartarus.getInstance().setScreen(new GameScreen());
            }
        });
        addButton(startButton);
    }

    private void addSettingsButton() {
        TextButton settingsButton = new TextButton(TranslationDictionary.Translations.SETTINGS, MainMenuSkin.getInstance());
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ProjectTartarus.getInstance().setScreen(new SettingsScreen());
            }
        });
        addButton(settingsButton);
    }

    private void addExitButton() {
        TextButton exitButton = new TextButton(TranslationDictionary.Translations.EXIT, MainMenuSkin.getInstance());
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
