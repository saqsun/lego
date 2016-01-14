package com.lego.game;

import com.lego.game.screen.LegoScreen;
import com.lego.mvc.Mediator;
import com.lego.mvc.Notification;
import com.lego.mvc.Observer;

/**
 * Created by sargis on 1/14/16.
 */
public class LegoGameListenerAdapter implements LegoGameListener {
    protected final LegoGame game;

    public LegoGameListenerAdapter(LegoGame game) {
        this.game = game;
    }

    @Override
    public void likeOnFB() {
        game.likeOnFB();
    }

    @Override
    public void showAlertDialog(String title, String message) {
        game.showAlertDialog(title, message);
    }

    @Override
    public String retrieveLanguage() {
        return game.retrieveLanguage();
    }

    @Override
    public String getVersion() {
        return game.getVersion();
    }

    @Override
    public String getLanguage() {
        return game.getLanguage();
    }

    @Override
    public long getAppLaunch() {
        return game.getAppLaunch();
    }

    @Override
    public void addPreferences() {
        game.addPreferences();
    }

    @Override
    public String getGameStartNotificationId() {
        return game.getGameStartNotificationId();
    }

    @Override
    public void setNotificationId(String notificationId) {
        game.setNotificationId(notificationId);
    }

    @Override
    public LegoScreen getScreen() {
        return game.getScreen();
    }

    @Override
    public void setScreen(LegoScreen screen) {
        game.setScreen(screen);
    }

    @Override
    public String getBundleId() {
        return game.getBundleId();
    }

    @Override
    public void twit(String message) {
        game.twit(message);
    }

    @Override
    public void twit(String message, String url) {
        game.twit(message, url);
    }

    @Override
    public void openReviewPage() {
        game.openReviewPage();
    }

    @Override
    public void create() {
        game.create();
    }

    @Override
    public void resize(int width, int height) {
        game.resize(width, height);
    }

    @Override
    public void render() {
        game.render();
    }

    @Override
    public void pause() {
        game.pause();
    }

    @Override
    public void resume() {
        game.resume();
    }

    @Override
    public void dispose() {
        game.dispose();
    }

    @Override
    public void registerObserver(String noteName, Observer observer) {
        game.registerObserver(noteName, observer);
    }

    @Override
    public void notifyObservers(Notification note) {
        game.notifyObservers(note);
    }

    @Override
    public void registerMediator(Mediator mediator) {
        game.registerMediator(mediator);
    }

    @Override
    public Mediator retrieveMediator(String mediatorName) {
        return game.retrieveMediator(mediatorName);
    }

    @Override
    public Mediator removeMediator(String mediatorName) {
        return game.removeMediator(mediatorName);
    }

    @Override
    public boolean hasMediator(String mediatorName) {
        return game.hasMediator(mediatorName);
    }

    @Override
    public void removeObserver(String notificationName, Object notifyContext) {
        game.removeObserver(notificationName, notifyContext);
    }
}
