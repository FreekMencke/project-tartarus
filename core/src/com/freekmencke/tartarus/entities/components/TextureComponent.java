package com.freekmencke.tartarus.entities.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class TextureComponent implements Component, Pool.Poolable {
    public TextureRegion region = null;
    /** Size in Box2D meters. */
    public Vector2 size = new Vector2();

    @Override
    public void reset() {
        region = null;
        size = new Vector2();
    }
}
