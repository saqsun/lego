package com.lego.ad.heyzap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;
import com.lego.ad.AdController;
import com.lego.ad.RewardedVideoListener;
import com.lego.ad.StatusListener;
import org.robovm.apple.foundation.NSError;
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIViewController;
import org.robovm.pods.heyzap.*;

/**
 * Created by sargis on 7/28/15.
 */
public class IOSHeyzapAdController implements AdController {
    private final StatusListenerDelegate nativeStatusListenerDelegate;
    private final RewardedVideoListenerDelegate incentiveResultListener;
    private final InterstitialAdHideWorkaroundTask interstitialAdHideWorkaroundTask;
    private UIViewController rootViewController;
    private HZBannerAdViewController bannerAdViewController;
    private StatusListener statusListener;
    private RewardedVideoListener rewardedVideoListener;

    public IOSHeyzapAdController(String publisherId) {

        //
        HeyzapAds.start(publisherId);
        HeyzapAds.setDebugLevel(HZDebugLevel.Verbose);
        HZInterstitialAd.fetch();
        HZVideoAd.fetch();
        HZIncentivizedAd.fetch();

        //
        nativeStatusListenerDelegate = new StatusListenerDelegate();
        HZInterstitialAd.setDelegate(nativeStatusListenerDelegate);
        HZVideoAd.setDelegate(nativeStatusListenerDelegate);
        incentiveResultListener = new RewardedVideoListenerDelegate();
        HZIncentivizedAd.setDelegate(incentiveResultListener);
        interstitialAdHideWorkaroundTask = new InterstitialAdHideWorkaroundTask();
    }

    @Override
    public void showInterstitial() {
        HZInterstitialAd.show();
        HZInterstitialAd.fetch();
    }

    @Override
    public void showVideo() {
        if (HZVideoAd.isAvailable()) {
            HZVideoAd.show();
        }
        HZVideoAd.fetch();
    }

    @Override
    public void showRewardedVideo() {
        if (HZIncentivizedAd.isAvailable()) {
            HZIncentivizedAd.show();
        }
        HZIncentivizedAd.fetch();
    }

    @Override
    public void showBanner() {
        rootViewController = UIApplication.getSharedApplication().getKeyWindow().getRootViewController();
        if (bannerAdViewController == null) {
            bannerAdViewController = new HZBannerAdViewController();
            rootViewController.addChildViewController(bannerAdViewController);
        }
        rootViewController.getView().addSubview(bannerAdViewController.getView());
    }

    @Override
    public void hideBanner() {
        if (bannerAdViewController == null) {
            return;
        }
        bannerAdViewController.getView().removeFromSuperview();
    }

    @Override
    public boolean isRewardedVideoAvailable() {
        boolean isAvailable = HZIncentivizedAd.isAvailable();
        if (!isAvailable) {
            HZIncentivizedAd.fetch();
        }
        return isAvailable;
    }

    @Override
    public boolean isInterstitialAdAvailable() {
        boolean isAvailable = HZInterstitialAd.isAvailable();
        if (!isAvailable) {
            HZInterstitialAd.fetch();
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

    private class StatusListenerDelegate extends NSObject implements HZAdsDelegate {
        private final String TAG = StatusListenerDelegate.class.getCanonicalName();

        @Override
        public void didShowAd(String s) {
            Gdx.app.log(TAG, "didShowAd");
            if (statusListener != null) {
                statusListener.onShow();
            }
        }

        @Override
        public void didFailToShowAd(String s, NSError nsError) {
            Gdx.app.log(TAG, "didFailToShowAd");
            if (statusListener != null) {
                statusListener.onFailedToShow();
            }
        }

        @Override
        public void didReceiveAd(String s) {
            Gdx.app.log(TAG, "didReceiveAd : " + s);
            if (statusListener != null) {
                statusListener.onAvailable();
            }
        }

        @Override
        public void didFailToReceiveAd(String s) {
            Gdx.app.log(TAG, "didFailToReceiveAd");
            if (statusListener != null) {
                statusListener.onFailedToFetch();
            }
        }

        @Override
        public void didClickAd(String s) {
            Gdx.app.log(TAG, "didClickAd");
            if (statusListener != null) {
                statusListener.onClick();
            }
        }

        @Override
        public void didHideAd(String s) {
            Gdx.app.log(TAG, "didHideAd");
            if (statusListener != null) {
                statusListener.onHide();
            }
        }

        @Override
        public void willStartAudio() {
            Gdx.app.log(TAG, "willStartAudio");
            if (statusListener != null) {
                statusListener.onAudioStarted();
            }
        }

        @Override
        public void didFinishAudio() {
            Gdx.app.log(TAG, "didFinishAudio");
            if (statusListener != null) {
                statusListener.onAudioFinished();
            }
        }
    }

    private class RewardedVideoListenerDelegate extends NSObject implements HZIncentivizedAdDelegate {
        private final String TAG = RewardedVideoListenerDelegate.class.getCanonicalName();

        @Override
        public void didCompleteAd(String s) {
            if (rewardedVideoListener != null) {
                rewardedVideoListener.onComplete();
            }
            //Timer.schedule(interstitialAdHideWorkaroundTask, 2);
        }

        @Override
        public void didFailToCompleteAd(String s) {
            if (rewardedVideoListener != null) {
                rewardedVideoListener.onIncomplete();
            }
        }

        @Override
        public void didShowAd(String s) {
            Gdx.app.log(TAG, "didShowAd");
            if (statusListener != null) {
                statusListener.onShow();
            }
        }

        @Override
        public void didFailToShowAd(String s, NSError nsError) {
            Gdx.app.log(TAG, "didFailToShowAd");
            if (statusListener != null) {
                statusListener.onFailedToShow();
            }
        }

        @Override
        public void didReceiveAd(String s) {
            Gdx.app.log(TAG, "didReceiveAd : " + s);
            if (statusListener != null) {
                statusListener.onAvailable();
            }
        }

        @Override
        public void didFailToReceiveAd(String s) {
            Gdx.app.log(TAG, "didFailToReceiveAd");
            if (statusListener != null) {
                statusListener.onFailedToFetch();
            }
        }

        @Override
        public void didClickAd(String s) {
            Gdx.app.log(TAG, "didClickAd");
            if (statusListener != null) {
                statusListener.onClick();
            }
        }

        @Override
        public void didHideAd(String s) {
            Gdx.app.log(TAG, "didHideAd");
            if (statusListener != null) {
                statusListener.onHide();
            }
            interstitialAdHideWorkaroundTask.cancel();
        }

        @Override
        public void willStartAudio() {
            Gdx.app.log(TAG, "willStartAudio");
            if (statusListener != null) {
                statusListener.onAudioStarted();
            }
        }

        @Override
        public void didFinishAudio() {
            Gdx.app.log(TAG, "didFinishAudio");
            if (statusListener != null) {
                statusListener.onAudioFinished();
            }
        }
    }

    private class InterstitialAdHideWorkaroundTask extends Timer.Task {
        @Override
        public void run() {
            if (statusListener != null) {
                statusListener.onHide();
            }
        }
    }
}
