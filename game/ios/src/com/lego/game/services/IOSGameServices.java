package com.lego.game.services;

import com.badlogic.gdx.Gdx;
import org.robovm.apple.foundation.NSError;
import org.robovm.apple.gamekit.GKAchievement;
import org.robovm.apple.gamekit.GKLeaderboard;
import org.robovm.apple.uikit.UIAlertView;
import org.robovm.apple.uikit.UIApplication;

import java.util.ArrayList;

/**
 * Created by sargis on 2/26/15.
 */
public class IOSGameServices implements GameServices, GameCenterListener {
    private static final String TAG = IOSGameServices.class.getCanonicalName();
    private GameCenterManager gcManager;
    private boolean isSignedIn;
    private GameServicesListener gameServicesListener;
    private UIAlertView uiAlertView;

    public IOSGameServices() {
        isSignedIn = false;
    }


    @Override
    public void login(boolean userInitiatedSignIn) {
        if (isSignedIn) {
            return;
        }
        if (gcManager == null) {
            gcManager = new GameCenterManager(UIApplication.getSharedApplication().getKeyWindow(), this);
        }
        gcManager.login();
    }

    @Override
    public void logout() {

    }

    @Override
    public void submitScore(String leaderBoardId, long score) {
        gcManager.reportScore(leaderBoardId, score);
    }

    @Override
    public void showLeaderBoard(String identifier) {
        if (!isSignedIn) {
            showNotSignedInDialog("Game Center", "You are not signed in.", "Okay");
            return;
        }
        gcManager.showLeaderboardView(identifier);
    }

    private void showNotSignedInDialog(String title, String message, String cancelButtonTitle) {
        uiAlertView = new UIAlertView(title, message, null, cancelButtonTitle);
        uiAlertView.show();
    }

    @Override
    public void showLeaderBoards() {
        //TODO show multiple leaderboards in iOS
    }

    @Override
    public void unlockAchievement(String achievementId) {
        gcManager.reportAchievement(achievementId);
    }

    @Override
    public void incrementAchievement(String achievementId, double incrementAmount) {
        gcManager.reportAchievement(achievementId, incrementAmount);
    }

    @Override
    public void showAchievements() {
        if (!isSignedIn) {
            showNotSignedInDialog("Game Center", "You are not signed in.", "Okay");
            return;
        }
        gcManager.showAchievementsView();
    }

    @Override
    public boolean isSignedIn() {
        return isSignedIn;
    }

    @Override
    public void savedGamesLoad(String snapshotName, boolean createIfMissing) {

    }

    @Override
    public void savedGamesUpdate(String snapshotName, byte[] data, boolean createIfMissing) {

    }

    @Override
    public void setListener(GameServicesListener gameServicesListener) {
        this.gameServicesListener = gameServicesListener;
    }

    @Override
    public boolean isSavedGamesLoadDone() {
        return false;
    }

    @Override
    public void playerLoginCompleted() {
        Gdx.app.log(TAG, "Sing in success");
        isSignedIn = true;
        if (gameServicesListener != null) {
            gameServicesListener.onSignInSucceeded();
        }
    }

    @Override
    public void playerLoginFailed(NSError nsError) {
        Gdx.app.log(TAG, "Sing in Fail");
        isSignedIn = false;
        if (gameServicesListener != null) {
            gameServicesListener.onSignInFailed();
        }
    }

    @Override
    public void achievementReportCompleted() {

    }

    @Override
    public void achievementReportFailed(NSError nsError) {

    }

    @Override
    public void achievementsLoadCompleted(ArrayList<GKAchievement> arrayList) {

    }

    @Override
    public void achievementsLoadFailed(NSError nsError) {

    }

    @Override
    public void achievementsResetCompleted() {

    }

    @Override
    public void achievementsResetFailed(NSError nsError) {

    }

    @Override
    public void scoreReportCompleted() {

    }

    @Override
    public void scoreReportFailed(NSError nsError) {

    }

    @Override
    public void leaderboardsLoadCompleted(ArrayList<GKLeaderboard> arrayList) {

    }

    @Override
    public void leaderboardsLoadFailed(NSError nsError) {

    }

    @Override
    public void leaderboardViewDismissed() {

    }

    @Override
    public void achievementViewDismissed() {

    }
}
