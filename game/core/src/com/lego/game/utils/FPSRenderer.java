package com.lego.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by sargis on 5/21/15.
 */
public class FPSRenderer implements Disposable {
    private final BitmapFont font;
    private final SpriteBatch batch;
    private final float scale;

    public FPSRenderer() {
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.classpath("com/badlogic/gdx/utils/arial-15.fnt"));
        scale = (Gdx.graphics.getWidth() / 640f) * 1.1f;
        font.getData().setScale(scale);
    }


    public void render() {
        batch.begin();
        int fps = Gdx.graphics.getFramesPerSecond();
        if (fps >= 45) {
            // 45 or more FPS show up in green
            font.setColor(Color.GREEN);
        } else if (fps >= 30) {
            // 30 or more FPS show up in yellow
            font.setColor(Color.YELLOW);
        } else {
            // less than 30 FPS show up in red
            font.setColor(Color.RED);
        }
        font.draw(batch, "FPS: " + fps, 5, 15 * scale);
        batch.end();
        font.setColor(Color.WHITE);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
