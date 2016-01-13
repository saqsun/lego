package com.lego.game.screen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

/**
 * Created by sargis on 12/20/15.
 */
public interface LegoScreen extends Screen {
    //TODO: @xcho add screenshot method!
    InputProcessor getInputProcessor();
}
