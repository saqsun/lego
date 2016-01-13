package com.lego.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.lego.game.assets.SimpleGameAssetsResolver;
import com.lego.game.screen.LegoScreen;
import com.lego.game.screen.transition.ScreenTransition;

/**
 * Created by sargis on 7/2/15.
 */
public abstract class DirectedGame extends LegoGame {
    private boolean fbosCreated;
    private LegoScreen currScreen;
    private LegoScreen nextScreen;
    private FrameBuffer currFbo;
    private FrameBuffer nextFbo;
    private SpriteBatch batch;
    private float time;
    private ScreenTransition screenTransition;

    public DirectedGame(String bundleId, String storeId, SimpleGameAssetsResolver simpleGameAssetsResolver) {
        super(bundleId, storeId, simpleGameAssetsResolver);
    }

    public void setScreen(LegoScreen screen,
                          ScreenTransition screenTransition) {
        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();
        if (!fbosCreated) {
            currFbo = new FrameBuffer(Pixmap.Format.RGB888, w, h, false);
            nextFbo = new FrameBuffer(Pixmap.Format.RGB888, w, h, false);
            batch = new SpriteBatch();
            fbosCreated = true;
        }
        // start new transition
        nextScreen = screen;
        nextScreen.show(); // activate next screen
        nextScreen.resize(w, h);
        nextScreen.render(0); // let screen notify() once
        if (currScreen != null) currScreen.pause();
        nextScreen.pause();
        Gdx.input.setInputProcessor(null); // disable input
        this.screenTransition = screenTransition;
        time = 0;
    }

    @Override
    public void renderGame() {
        // get delta time and ensure an upper limit of one 60th second
        float deltaTime = Math.min(Gdx.graphics.getDeltaTime(), 1.0f / 60.0f);
        if (nextScreen == null) {
            // no ongoing transition
            if (currScreen != null) currScreen.render(deltaTime);
        } else {
            // ongoing transition
            float duration = 0;
            if (screenTransition != null)
                duration = screenTransition.getDuration();
            // notify progress of ongoing transition
            time = Math.min(time + deltaTime, duration);
            if (screenTransition == null || time >= duration) {
                //no transition effect set or transition has just finished
                if (currScreen != null) currScreen.hide();
                nextScreen.resume();
                // enable input for next screen
                Gdx.input.setInputProcessor(
                        nextScreen.getInputProcessor());
                // switch screens
                currScreen = nextScreen;
                nextScreen = null;
                screenTransition = null;
                currScreen.render(deltaTime);
            } else {
                // render screens to FBOs
                currFbo.begin();
                if (currScreen != null) currScreen.render(deltaTime);
                currFbo.end();
                nextFbo.begin();
                nextScreen.render(deltaTime);
                nextFbo.end();
                // render transition effect to screen
                float alpha = time / duration;
                screenTransition.render(batch, currFbo.getColorBufferTexture(), nextFbo.getColorBufferTexture(), alpha);
            }
        }
        super.renderGame();
    }

    @Override
    public void resize(int width, int height) {
        if (currScreen != null) currScreen.resize(width, height);
        if (nextScreen != null) nextScreen.resize(width, height);
    }

    @Override
    public void pause() {
        if (currScreen != null) currScreen.pause();
    }

    @Override
    public void resume() {
        if (currScreen != null) currScreen.resume();
    }

    @Override
    public void dispose() {
        if (currScreen != null) currScreen.hide();
        if (nextScreen != null) nextScreen.hide();
        if (fbosCreated) {
            currFbo.dispose();
            currScreen = null;
            nextFbo.dispose();
            nextScreen = null;
            batch.dispose();
            fbosCreated = false;
        }
    }

    public LegoScreen getScreen() {
        return currScreen;
    }

    public void setScreen(LegoScreen screen) {
        setScreen(screen, null);
    }

    public Screen getNextScreen() {
        return nextScreen;
    }

}
