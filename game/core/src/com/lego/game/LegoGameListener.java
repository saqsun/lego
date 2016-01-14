package com.lego.game;

import com.badlogic.gdx.ApplicationListener;
import com.lego.game.screen.LegoScreen;
import com.lego.mvc.View;

/**
 * Created by sargis on 1/14/16.
 */
public interface LegoGameListener extends ApplicationListener, View {
    String getVersion();

    String getLanguage();

    //TODO: change this fucking name, it is not self explanatory
    long getAppLaunch();

    void addPreferences();

    String getGameStartNotificationId();

    void setNotificationId(String notificationId);

    LegoScreen getScreen();

    void setScreen(LegoScreen screen);

    String getBundleId();

    void twit(String message);

    void twit(String message, String url);

    void openReviewPage();

    void likeOnFB();

    void showAlertDialog(String title, String message);

    String retrieveLanguage();

}
