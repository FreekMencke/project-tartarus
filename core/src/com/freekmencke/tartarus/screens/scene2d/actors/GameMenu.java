package com.freekmencke.tartarus.screens.scene2d.actors;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.freekmencke.tartarus.TartarusGame;
import com.freekmencke.tartarus.screens.scene2d.skins.SystemSkin;
import com.freekmencke.tartarus.screens.MainMenuScreen;
import com.freekmencke.tartarus.system.config.TranslationDictionary;

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
                TartarusGame.CONFIG.onNext(TartarusGame.CONFIG.getValue().togglePause());
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
                TartarusGame.getInstance().setScreen(new MainMenuScreen());
            }
        });

        add(resumeButton).width(400).height(50).row();
    }


}
