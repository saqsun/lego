package com.lego.ad.heyzap;

import com.badlogic.gdx.Gdx;
import org.robovm.apple.foundation.NSError;
import org.robovm.apple.uikit.UIViewController;
import org.robovm.objc.block.VoidBlock1;
import org.robovm.pods.heyzap.HZBannerAd;
import org.robovm.pods.heyzap.HZBannerAdOptions;
import org.robovm.pods.heyzap.HZBannerPosition;

public class HZBannerAdViewController extends UIViewController {

    private final HZBannerAdRequestSuccess bannerAdRequestSuccess;
    private final HZBannerAdRequestFailure bannerAdRequestFailure;

    public HZBannerAdViewController() {
        HZBannerAdOptions bannerAdOptions = new HZBannerAdOptions();
        bannerAdRequestSuccess = new HZBannerAdRequestSuccess();
        bannerAdRequestFailure = new HZBannerAdRequestFailure();
        HZBannerAd.requestBanner(bannerAdOptions, bannerAdRequestSuccess, bannerAdRequestFailure);
        HZBannerAd.placeBanner(
                getView(),
                HZBannerPosition.Bottom,
                bannerAdOptions,
                bannerAdRequestSuccess,
                bannerAdRequestFailure);
    }

    private class HZBannerAdRequestSuccess implements VoidBlock1<HZBannerAd> {
        private final String TAG = HZBannerAdRequestSuccess.class.getCanonicalName();

        @Override
        public void invoke(HZBannerAd hzBannerAd) {
            Gdx.app.log(TAG, "success : " + hzBannerAd.getMediatedNetwork());
        }
    }

    private class HZBannerAdRequestFailure implements VoidBlock1<NSError> {
        private final String TAG = HZBannerAdRequestFailure.class.getCanonicalName();

        @Override
        public void invoke(NSError nsError) {
            Gdx.app.log(TAG, "failure " + nsError);
        }
    }
}
