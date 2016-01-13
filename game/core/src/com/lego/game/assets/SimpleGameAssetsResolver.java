package com.lego.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;
import com.google.inject.Inject;
import com.lego.game.utils.ResolutionDescriptor;

import java.io.File;

/**
 * Created by khachatur on 4/13/15.
 */
public abstract class SimpleGameAssetsResolver implements LegoAssetsResolver {
    //TODO: @xcho add particle pooling!
    //TODO: @xcho add spine pooling!
    private static final String TAG = SimpleGameAssetsResolver.class.getCanonicalName();

    protected final ObjectMap<String, TextureRegion> textures;
    protected final ObjectMap<String, Array<TextureAtlas.AtlasRegion>> textureRegionArrays;
    protected final ObjectMap<String, FileHandle> particles;
    @Inject
    public ResolutionDescriptor resolutionDescriptor;
    protected TextureAtlas gameAtlas;
    protected Music ingameMusic;
    protected AssetManager assetManager;
    protected String gameAtlasName;
    protected String audioFilesExtension;
    protected Music menuLoop;
    protected String atlasRootPath;
    protected final Json json;
    protected final float ANIMATIONS_ORIGINAL_SIZE = 1080f;

    private SimpleGameAssetsResolver(ResolutionDescriptor resolutionDescriptor) {
        this.resolutionDescriptor = resolutionDescriptor;
        json = new Json();
        textures = new ObjectMap<>();
        textureRegionArrays = new ObjectMap<>();
        particles = new ObjectMap<>();
    }

    public TextureAtlas getGameAtlas() {
        return gameAtlas;
    }

    @Override
    public void init() {
        audioFilesExtension = "";
        switch (Gdx.app.getType()) {
            case iOS:
                audioFilesExtension = "mp3";
                break;
            case Android:
                audioFilesExtension = "ogg";
                break;
            case Desktop:
                String absolutePath = Gdx.files.internal("").file().getAbsolutePath();
                if (absolutePath.contains("ios")) {
                    audioFilesExtension = "mp3";
                } else {
                    audioFilesExtension = "ogg";
                }
                break;
        }
        String aspectRatio = resolutionDescriptor.getAspectRatio().getCanonical();
        String resolution = resolutionDescriptor.getWith() + "x" + resolutionDescriptor.getHeight();
        atlasRootPath = "atlas" + File.separator + aspectRatio + File.separator + resolution + File.separator;
    }

    @Override
    public void load() {
        assetManager = new AssetManager();
        //
        textures.clear();
        particles.clear();
        //
        gameAtlasName = atlasRootPath + "texturepacker" + File.separator + "game.atlas";

        assetManager.load(gameAtlasName, TextureAtlas.class);
        assetManager.load("audio/Ingame Music." + audioFilesExtension, Music.class);
        assetManager.load("audio/Menu Loop." + audioFilesExtension, Music.class);
    }

    @Override
    public boolean update() {
        boolean done = assetManager.update();
        if (done) {
            initAssets();
        }
        Gdx.app.log(TAG, "progress : " + assetManager.getProgress() * 100 + "%");
        return done;
    }

    private void initAssets() {
        ingameMusic = assetManager.get("audio/Ingame Music." + audioFilesExtension);
        menuLoop = assetManager.get("audio/Menu Loop." + audioFilesExtension);
        gameAtlas = new TextureAtlas(gameAtlasName);
        initSkin();
    }

    private void initSkin() {
        String skinJson = Gdx.files.internal("ui.json").readString();
        skinJson = skinJson.replaceAll("\\{resolution\\}", resolutionDescriptor.getResolution());
        skinJson = skinJson.replaceAll("\\{aspectRatio\\}", resolutionDescriptor.getAspectRatio().getCanonical());
        FileHandle skinJsonFileHandle = FileHandle.tempFile("ui.json");
        skinJsonFileHandle.writeString(skinJson, false);
        //skin = new Skin(skinJsonFileHandle, gameAtlas);
        skinJsonFileHandle.delete();
    }

    @Override
    public TextureRegion getTexture(String name) {
        if (textures.containsKey(name)) {
            return textures.get(name);
        }
        TextureAtlas.AtlasRegion region = gameAtlas.findRegion(name);
        textures.put(name, region);
        return region;
    }

    @Override
    public Array<TextureAtlas.AtlasRegion> getTextures(String name) {
        if (textureRegionArrays.containsKey(name)) {
            return textureRegionArrays.get(name);
        }
        Array<TextureAtlas.AtlasRegion> atlasRegions = gameAtlas.findRegions(name);
        textureRegionArrays.put(name, atlasRegions);
        return atlasRegions;
    }

    public ParticleEffect getParticleEffect(String name) {
        FileHandle particle;
        if (particles.containsKey(name)) {
            particle = particles.get(name);
        } else {
            particle = Gdx.files.internal(name);
            particles.put(name, particle);
        }
        ParticleEffect effect = new ParticleEffect();
        effect.load(particle, gameAtlas);
        float scaleFactor = resolutionDescriptor.getWith() / ANIMATIONS_ORIGINAL_SIZE;
        effect.scaleEffect(scaleFactor);
        return effect;
    }

    public Music getIngameMusic() {
        return ingameMusic;
    }

    public Music getMenuLoop() {
        return menuLoop;
    }

    public abstract Sound getSound(String name);

    @Override
    public void dispose() {
        gameAtlas.dispose();
        ingameMusic.dispose();
        menuLoop.dispose();
        textures.clear();
        textureRegionArrays.clear();
        assetManager.dispose();
    }


}
