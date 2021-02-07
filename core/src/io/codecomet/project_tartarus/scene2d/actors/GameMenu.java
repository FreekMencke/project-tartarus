package io.codecomet.project_tartarus.scene2d.actors;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import io.codecomet.project_tartarus.ProjectTartarus;
import io.codecomet.project_tartarus.scene2d.skins.SystemSkin;
import io.codecomet.project_tartarus.screens.MainMenuScreen;
import io.codecomet.project_tartarus.system.config.GameConfiguration;
import io.codecomet.project_tartarus.system.config.TranslationDictionary;

public class GameMenu extends Table {

    public GameMenu() {
        setFillParent(true);
        background(SystemSkin.getInstance().newDrawable("base", 0,0,0, .5f));

        addTitle();
        addResumeButton();
        addExitToMainMenuButton();
    }

    private void addTitle() {
        Label titleLabel = new Label(TranslationDictionary.Translations.MENU, SystemSkin.getInstance(), "title");
        add(titleLabel).align(Align.center).spaceBottom(50).row();
    }

    private void addResumeButton() {
        TextButton resumeButton = new TextButton(TranslationDictionary.Translations.RESUME, SystemSkin.getInstance());

        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameConfiguration.Configuration config = ProjectTartarus.config.getValue();
                config.paused = false;
                ProjectTartarus.config.onNext(config);
                remove();
            }
        });

        add(resumeButton).width(400).height(50).spaceBottom(20).row();
    }

    private void addExitToMainMenuButton() {
        TextButton resumeButton = new TextButton(TranslationDictionary.Translations.EXIT_TO_MAIN_MENU, SystemSkin.getInstance());

        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ProjectTartarus.getInstance().setScreen(new MainMenuScreen());
            }
        });

        add(resumeButton).width(400).height(50).row();
    }


}
