package com.lego.ashley.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by khachatur on 1/14/16.
 */
public class TextureComponent implements Component, Pool.Poolable{
    public TextureRegion textureRegion;

    @Override
    public void reset() {
        textureRegion = null;
    }
}
