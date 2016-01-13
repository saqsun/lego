package com.lego.game.utils;

/**
 * Created by sargis on 5/1/15.
 */
public interface ResolutionDescriptor {
    void init();

    int getOriginWith();

    int getOriginHeight();

    Fraction getAspectRatio();

    String getResolution();

    int getHeight();

    int getWith();
}
