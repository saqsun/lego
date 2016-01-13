package com.lego.game.assets;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by sargis on 12/23/15.
 */
public interface LegoAssetsResolver extends Disposable {
    void init();

    void load();

    boolean update();

    TextureRegion getTexture(String name);

    Array<TextureAtlas.AtlasRegion> getTextures(String name);

    ParticleEffect getParticleEffect(String name);

    Music getIngameMusic();

    Music getMenuLoop();

    Sound getSound(String name);
}
