package com.lego.game;

import com.lego.game.assets.SimpleGameAssetsResolver;
import org.robovm.apple.foundation.*;
import org.robovm.apple.social.SLComposeViewController;
import org.robovm.apple.social.SLServiceType;
import org.robovm.apple.uikit.*;

/**
 * Created by sargis on 8/5/15.
 */
public abstract class IOSGame extends DirectedGame {
    private final NSString version;
    private UIAlertView uiAlertView;

    public IOSGame(String id, String storeId, SimpleGameAssetsResolver simpleGameAssetsResolver) {
        super(id, storeId, simpleGameAssetsResolver);
        NSDictionary nsDictionary = NSBundle.getMainBundle().getInfoDictionary();
        version = (NSString) nsDictionary.get("CFBundleShortVersionString");
    }

    @Override
    public String getVersion() {
        return version.toString();
    }

    @Override
    public void showAlertDialog(String title, String message) {
        if (FoundationVersionNumber.getVersion() < FoundationVersionNumber.Version_iOS_8_0) {
            // iOS 7 or earlier.
            showAlertForiOS7orEarlier(title, message);
        } else {
            // iOS 8 or later.
            showAlertForiOS8orLater(title, message);
        }
    }

    private void showAlertForiOS8orLater(String title, String message) {
        UIAlertController uiAlertController = new UIAlertController(title, message, UIAlertControllerStyle.Alert);
        UIAlertAction okButton = new UIAlertAction("OK", UIAlertActionStyle.Default, null);
        uiAlertController.addAction(okButton);
        UIViewController rootViewController = UIApplication.getSharedApplication().getKeyWindow().getRootViewController();
        rootViewController.presentViewController(uiAlertController, true, null);
    }

    private void showAlertForiOS7orEarlier(String title, String message) {
        if (uiAlertView == null) {
            uiAlertView = new UIAlertView(title, message, null, "OK");
        }
        uiAlertView.show();
    }

    @Override
    protected String retrieveLanguage() {
        String language = NSLocale.getPreferredLanguages().get(0);
        language = language.toLowerCase();
        return language;
    }

    @Override
    public void twit(String message) {
        twit(message, "https://itunes.apple.com/app/id" + storeId);
    }

    @Override
    public void twit(String message, String url) {
        if (SLComposeViewController.isAvailable(SLServiceType.Twitter)) {
            SLComposeViewController tweetSheet = new SLComposeViewController(SLServiceType.Twitter);
            tweetSheet.setInitialText(message + " " + url);
            UIWindow keyWindow = UIApplication.getSharedApplication().getKeyWindow();
            if (keyWindow != null) {
                keyWindow.getRootViewController().presentViewController(tweetSheet, true, null);
            }
        } else {
            String tweetUrl =
                    String.format("https://twitter.com/intent/tweet?text=%s&url=%s",
                            urlEncode(message), urlEncode(url));
            openURL("", tweetUrl);
        }
    }

    @Override
    public void openReviewPage() {
        openURL("itms-apps://itunes.apple.com/WebObjects/MZStore.woa/wa/viewContentsUserReviews?type=Purple+Software&id=" + storeId,
                "https://itunes.apple.com/app/id" + storeId);
    }

    @Override
    public void likeOnFB() {

    }

    private void openURL(String url, String fallbackURL) {
        UIApplication uiApplication = UIApplication.getSharedApplication();
        NSURL nsURL = new NSURL(url);
        if (uiApplication.canOpenURL(nsURL)) {
            uiApplication.openURL(nsURL);
        } else {
            uiApplication.openURL(new NSURL(fallbackURL));
        }
    }
}
