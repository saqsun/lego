package com.lego.game.localization;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

/**
 * Created by sargis on 6/11/15.
 */
public class Localizer {
    private I18NBundle bundle;

    private Localizer() {
    }

    public static void adjustFontScale(Label label, float bound) {
        float fontScale = label.getFontScaleX();
        while (label.getPrefWidth() > bound) {
            fontScale -= .02f;
            label.setFontScale(fontScale);
        }
    }

    public void init(String language) {
        bundle = I18NBundle.createBundle(Gdx.files.internal("localization/strings"), new Locale(language));
    }

    public String get(String key, Object... args) {
        return bundle.format(key, args);
    }

    public String get(String key) {
        return bundle.get(key);
    }
}
