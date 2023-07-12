package com.freekmencke.tartarus.entities.components;

import box2dLight.ConeLight;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class ConeLightComponent implements Component, Pool.Poolable {

    public ConeLight coneLight = null;

    @Override
    public void reset() {
        coneLight.dispose();
        coneLight = null;
    }
}
