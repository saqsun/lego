package com.lego.game.screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lego.game.utils.ResolutionDescriptor;

import java.io.File;

/**
 * Created by sargis on 6/9/15.
 */
public class SplashScreen implements LegoScreen {
    private ResolutionDescriptor resolutionDescriptor;

    private OrthographicCamera camera;

    private Viewport viewport;

    private Sprite splash;
    private SpriteBatch batch;
    private Texture splashTexture;

    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        switch (Gdx.app.getType()) {
            case iOS:
                initForIOS();
                break;
            case Android:
                initForAndroid();
                break;
            case Desktop:
                initForDesktop();
                break;
        }
        //splash.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        viewport.apply(true);
    }

    private void initForDesktop() {
        try {
            initForIOS();
        } catch (Exception e) {
            initForAndroid();
        }
    }

    private void initForAndroid() {
        int width = resolutionDescriptor.getWith();
        int height = resolutionDescriptor.getHeight();
        viewport = new StretchViewport(width, height, camera);
        splashTexture = new Texture("splash" + File.separator +
                resolutionDescriptor.getAspectRatio().getCanonical() + File.separator +
                resolutionDescriptor.getResolution() + File.separator + "splash.png");
        splashTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        splash = new Sprite(splashTexture);
    }

    private void initForIOS() {
        viewport = new ScreenViewport(camera);
        String splashName = "Default";
        switch (Gdx.app.getType() == Application.ApplicationType.Desktop ?
                resolutionDescriptor.getWith() :
                Gdx.graphics.getWidth()) {
            case 640:
                splashName = Gdx.graphics.getHeight() != 1136 ? "Default@2x" : "Default-568h@2x";
                break;
            case 1536:
                splashName = "Default@2x~ipad";
                break;
            case 768:
                splashName = "Default~ipad";
                break;
            case 2048:
                splashName = "Default-1024w-1366h@2x~ipad";
                break;
            case 1080:
                splashName = "Default-414w-736h@3x";
                break;
            case 750:
                splashName = "Default-375w-667h@2x";
                break;
        }
        splashTexture = new Texture(Gdx.files.internal(splashName + ".png"));
        splashTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        splash = new Sprite(splashTexture);
        float scaleX = (float) Gdx.graphics.getWidth() / splash.getWidth();
        float scaleY = (float) Gdx.graphics.getHeight() / splash.getHeight();
        splash.setOriginCenter();
        splash.setScale(Math.max(scaleX, scaleY));
        Input.Orientation nativeOrientation = Gdx.input.getNativeOrientation();
        switch (nativeOrientation) {
            case Landscape:
                splash.setY(splash.getHeight() * (scaleX - 1) * .5f);
                splash.setX((Gdx.graphics.getWidth() - splash.getWidth()) * .5f);
                break;
            case Portrait:
                splash.setX(splash.getWidth() * (scaleY - 1) * .5f);
                splash.setY((Gdx.graphics.getHeight() - splash.getHeight()) * .5f);
                break;
        }

    }

    @Override
    public void resume() {
    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        splash.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
        // NOOP
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        splashTexture.dispose();
    }

    @Override
    public InputProcessor getInputProcessor() {
        return null;
    }
}
