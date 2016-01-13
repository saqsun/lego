package com.lego.game.screen.transition;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by sargis on 7/2/15.
 */
public interface ScreenTransition {
    float getDuration();

    void render(SpriteBatch batch, Texture currScreen, Texture nextScreen, float alpha);
}
