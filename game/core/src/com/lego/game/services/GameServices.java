package com.lego.game.services;

public interface GameServices {
    void login(boolean userInitiatedSignIn);

    void logout();

    void submitScore(String leaderBoardId, long score);

    void showLeaderBoard(String identifier);

    void showLeaderBoards();

    void unlockAchievement(String achievementId);

    void incrementAchievement(String achievementId, double incrementAmount);

    void showAchievements();

    boolean isSignedIn();

    void savedGamesLoad(String snapshotName, boolean createIfMissing);

    void savedGamesUpdate(String snapshotName, byte[] data, boolean createIfMissing);

    void setListener(GameServicesListener gameServicesListener);

    boolean isSavedGamesLoadDone();
}
