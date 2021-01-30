package io.codecomet.project_tartarus.scene2d.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import io.codecomet.project_tartarus.ProjectTartarus;
import io.codecomet.project_tartarus.scene2d.skins.SystemSkin;
import io.codecomet.project_tartarus.screens.MenuScreen;
import io.codecomet.project_tartarus.system.config.GameConfiguration;
import io.codecomet.project_tartarus.system.config.TranslationDictionary;

public class SettingsMenu extends Table {

    public SettingsMenu() {
        GameConfiguration.Settings.load();

        addTitle();

        addVSyncToggle();
        addFullScreenToggle();

        addFooterButtons();
    }

    private void addTitle() {
        Label titleLabel = new Label(TranslationDictionary.Translations.SETTINGS, SystemSkin.getInstance(), "title");
        add(titleLabel).align(Align.center).spaceBottom(50).row();
    }

    private void addVSyncToggle() {
        CheckBox vSyncCheckBox = new CheckBox(TranslationDictionary.Translations.ENABLE_VSYNC, SystemSkin.getInstance());

        vSyncCheckBox.getImage().setScaling(Scaling.fill);
        vSyncCheckBox.getImageCell().size(32).space(20);

        vSyncCheckBox.setChecked(GameConfiguration.Settings.isVSyncEnabled());
        vSyncCheckBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameConfiguration.Settings.toggleVSync();
            }
        });

        add(vSyncCheckBox).left().row();
    }

    private void addFullScreenToggle() {
        CheckBox vSyncCheckBox = new CheckBox(TranslationDictionary.Translations.ENABLE_FULLSCREEN, SystemSkin.getInstance());

        vSyncCheckBox.getImage().setScaling(Scaling.fill);
        vSyncCheckBox.getImageCell().size(32).space(20);

        vSyncCheckBox.setChecked(GameConfiguration.Settings.isFullscreenEnabled());
        vSyncCheckBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameConfiguration.Settings.toggleFullscreen();
            }
        });

        add(vSyncCheckBox).left().row();
    }

    private void addFooterButtons() {
        HorizontalGroup hGroup = new HorizontalGroup();
        hGroup.addActor(new Container<>(createApplyButton()).prefWidth(200).prefHeight(50));
        hGroup.addActor(new Container<>(createBackButton()).prefWidth(200).prefHeight(50));
        hGroup.space(20);

        add(hGroup).spaceTop(50).row();
    }

    private TextButton createApplyButton() {
        TextButton applyButton = new TextButton(TranslationDictionary.Translations.APPLY, SystemSkin.getInstance());

        applyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameConfiguration.Settings.save();
            }
        });

        return applyButton;
    }

    private TextButton createBackButton() {
        TextButton backButton = new TextButton(TranslationDictionary.Translations.BACK, SystemSkin.getInstance());

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ProjectTartarus.getInstance().setScreen(new MenuScreen());
            }
        });
        return backButton;
    }

}
