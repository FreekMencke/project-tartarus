package com.freekmencke.tartarus.system.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.HashMap;

public class FontManager {

    private enum FontType {
        DefaultFont,
        SystemFont
    }

    private final HashMap<FontType, HashMap<Integer, BitmapFont>> fontCache = new HashMap<>();

    private final FreeTypeFontGenerator defaultFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/YoungSerif-Regular.ttf"));
    private final FreeTypeFontGenerator systemFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/LiberationMono-Regular.ttf"));

    private static FontManager instance = null;

    public static FontManager getInstance() {
        return instance == null ? instance = new FontManager() : instance;
    }

    private FontManager() {
        fontCache.put(FontType.DefaultFont, new HashMap<>());
        fontCache.put(FontType.SystemFont, new HashMap<>());
    }

    public BitmapFont getDefaultFont(int size) {
        if (fontCache.get(FontType.DefaultFont).containsKey(size)) {
            return fontCache.get(FontType.DefaultFont).get(size);
        }

        FreeTypeFontGenerator.FreeTypeFontParameter parameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameters.size = size;

        BitmapFont font = defaultFontGenerator.generateFont(parameters);
        fontCache.get(FontType.DefaultFont).put(size, font);

        return font;
    }

    public BitmapFont getSystemFont(int size) {
        if (fontCache.get(FontType.SystemFont).containsKey(size)) {
            return fontCache.get(FontType.SystemFont).get(size);
        }

        FreeTypeFontGenerator.FreeTypeFontParameter parameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameters.mono = true;
        parameters.size = size;

        BitmapFont font = systemFontGenerator.generateFont(parameters);
        fontCache.get(FontType.SystemFont).put(size, font);

        return font;
    }

}
