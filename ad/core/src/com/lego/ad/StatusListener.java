package com.lego.ad;

/**
 * Created by sargis on 7/29/15.
 */
public interface StatusListener {
    void onShow();

    void onClick();

    void onHide();

    void onFailedToShow();

    void onAvailable();

    void onFailedToFetch();

    void onAudioStarted();

    void onAudioFinished();
}
