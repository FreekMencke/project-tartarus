package io.codecomet.project_tartarus.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import java.util.HashMap;

enum FontType {
    DefaultFont
}

public class FontManager {

    private static FontManager instance = null;

    private final HashMap<FontType, HashMap<Integer, BitmapFont>> fontCache = new HashMap<>();

    private final FreeTypeFontGenerator defaultFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/YoungSerif-Regular.ttf"));

    private FontManager() {
        fontCache.put(FontType.DefaultFont, new HashMap<Integer, BitmapFont>());
    }

    public static FontManager getInstance() {
        if (instance == null) instance = new FontManager();

        return instance;
    }

    public BitmapFont getDefaultFont(int size) {
        if (fontCache.get(FontType.DefaultFont).containsKey(size)) {
            return fontCache.get(FontType.DefaultFont).get(size);
        }

        FreeTypeFontParameter parameters = new FreeTypeFontParameter();
        parameters.size = size;

        BitmapFont font = defaultFontGenerator.generateFont(parameters);
        fontCache.get(FontType.DefaultFont).put(size, font);

        return font;
    }

}
