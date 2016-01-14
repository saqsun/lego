package com.lego.game;

import com.badlogic.gdx.math.MathUtils;

/**
 * Created by sargis on 10/6/15.
 */
public class DesktopGame extends LegoGameListenerAdapter {
    public DesktopGame(LegoGame game) {
        super(game);
    }

    @Override
    public String retrieveLanguage() {
        String[] languages = {"de", "en", "es", "fr", "it", "ja", "ko", "pt", "zh", "ru"};
        return languages[MathUtils.random(0, languages.length - 1)];
    }

    @Override
    public String getVersion() {
        return null;
    }

    @Override
    public void showAlertDialog(String title, String message) {

    }

    @Override
    public void twit(String message) {

    }

    @Override
    public void twit(String message, String url) {

    }

    @Override
    public void openReviewPage() {

    }

    @Override
    public void likeOnFB() {

    }
}
