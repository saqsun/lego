package com.lego.ad.heyzap;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.heyzap.sdk.ads.*;
import com.lego.ad.AdController;
import com.lego.ad.RewardedVideoListener;
import com.lego.ad.StatusListener;

/**
 * Created by sargis on 7/28/15.
 */
public class AndroidHeyzapAdController implements AdController {
    private final Activity activity;
    private final ViewGroup mainView;
    private BannerAdView bannerAdView;
    private RewardedVideoListener rewardedVideoListener;
    private StatusListener statusListener;

    public AndroidHeyzapAdController(Activity activity, ViewGroup mainView, String publisherId) {
        this.activity = activity;
        this.mainView = mainView;
        StatusListenerDelegate statusListener = new StatusListenerDelegate();
        HeyzapAds.start(publisherId, activity);
        InterstitialAd.setOnStatusListener(statusListener);
        VideoAd.fetch();
        VideoAd.setOnStatusListener(statusListener);
        IncentivizedAd.fetch();
        IncentivizedAd.setOnIncentiveResultListener(new RewardedVideoListenerDelegate());
        IncentivizedAd.setOnStatusListener(statusListener);
    }

    @Override
    public void showInterstitial() {
        InterstitialAd.display(activity);
    }

    @Override
    public void showVideo() {
        if (VideoAd.isAvailable()) {
            VideoAd.display(activity);
        }
        VideoAd.fetch();
    }

    @Override
    public void showRewardedVideo() {
        if (IncentivizedAd.isAvailable()) {
            IncentivizedAd.display(activity);
        }
        IncentivizedAd.fetch();
    }

    public void onCreate() {
        bannerAdView = new BannerAdView(activity);
        // Load the banner ad.
        bannerAdView.load();
    }

    @Override
    public void showBanner() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (bannerAdView.getParent() == null) {
                    RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);
                    adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    adParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    mainView.addView(bannerAdView, adParams);
                }
                bannerAdView.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void hideBanner() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bannerAdView.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public boolean isRewardedVideoAvailable() {
        boolean isAvailable = IncentivizedAd.isAvailable();
        if (!isAvailable) {
            IncentivizedAd.fetch();
        }
        return isAvailable;
    }

    @Override
    public boolean isInterstitialAdAvailable() {
        boolean isAvailable = InterstitialAd.isAvailable();
        if (!isAvailable) {
            InterstitialAd.fetch();
        }
        return isAvailable;
    }


    @Override
    public void setStatusListener(StatusListener statusListener) {
        this.statusListener = statusListener;
    }

    @Override
    public void setRewardedVideoListener(RewardedVideoListener rewardedVideoListener) {
        this.rewardedVideoListener = rewardedVideoListener;
    }

    public void onDestroy() {
        bannerAdView.destroy();
    }

    private class StatusListenerDelegate implements HeyzapAds.OnStatusListener {

        @Override
        public void onShow(String s) {
            if (statusListener != null) {
                statusListener.onShow();
            }
        }

        @Override
        public void onClick(String s) {
            if (statusListener != null) {
                statusListener.onClick();
            }
        }

        @Override
        public void onHide(String s) {
            if (statusListener != null) {
                statusListener.onHide();
            }
        }

        @Override
        public void onFailedToShow(String s) {
            if (statusListener != null) {
                statusListener.onFailedToShow();
            }
        }

        @Override
        public void onAvailable(String s) {
            if (statusListener != null) {
                statusListener.onAvailable();
            }
        }

        @Override
        public void onFailedToFetch(String s) {
            if (statusListener != null) {
                statusListener.onFailedToFetch();
            }
        }

        @Override
        public void onAudioStarted() {
            if (statusListener != null) {
                statusListener.onAudioStarted();
            }
        }

        @Override
        public void onAudioFinished() {
            if (statusListener != null) {
                statusListener.onAudioFinished();
            }
        }
    }

    private class RewardedVideoListenerDelegate implements HeyzapAds.OnIncentiveResultListener {

        @Override
        public void onComplete(String s) {
            if (rewardedVideoListener != null) {
                rewardedVideoListener.onComplete();
            }
        }

        @Override
        public void onIncomplete(String s) {
            if (rewardedVideoListener != null) {
                rewardedVideoListener.onIncomplete();
            }
        }
    }
}
