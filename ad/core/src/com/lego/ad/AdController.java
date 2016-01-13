package com.lego.ad;

/**
 * Created by sargis on 7/28/15.
 */
public interface AdController {

    void showInterstitial();

    void showVideo();

    void showRewardedVideo();

    void showBanner();

    void hideBanner();

    boolean isRewardedVideoAvailable();

    boolean isInterstitialAdAvailable();

    void setStatusListener(StatusListener statusListener);

    void setRewardedVideoListener(RewardedVideoListener rewardedVideoListener);
}
