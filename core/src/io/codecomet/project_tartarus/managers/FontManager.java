package io.codecomet.project_tartarus.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.HashMap;

enum FontType {
    DefaultFont
}

public class FontManager {

    private final HashMap<FontType, HashMap<Integer, BitmapFont>> fontCache = new HashMap<>();
    private final FreeTypeFontGenerator defaultFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/YoungSerif-Regular.ttf"));

    private static FontManager instance = null;
    public static FontManager getInstance() {
        return instance == null ? instance = new FontManager() : instance;
    }
    private FontManager() {
        fontCache.put(FontType.DefaultFont, new HashMap<>());
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

}
