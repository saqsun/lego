package com.lego.game;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import com.badlogic.gdx.Gdx;
import com.lego.game.assets.SimpleGameAssetsResolver;

import java.util.List;
import java.util.Locale;

/**
 * Created by sargis on 8/5/15.
 */
public abstract class AndroidGame extends DirectedGame {

    private final Activity activity;

    public AndroidGame(String id, String storeId, SimpleGameAssetsResolver simpleGameAssetsResolver, Activity activity) {
        super(id, storeId, simpleGameAssetsResolver);
        this.activity = activity;
    }

    @Override
    public String getVersion() {
        String version = "";
        try {
            PackageInfo pInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    @Override
    public void showAlertDialog(final String title, final String message) {
        pause();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(activity)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(title)
                        .setMessage(message)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                resume();
                            }

                        })
                        .show();
            }
        });

    }

    @Override
    protected void createGame() {
        super.createGame();
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    protected String retrieveLanguage() {
        String language = Locale.getDefault().getLanguage();
        language = language.toLowerCase();
        return language;
    }

    @Override
    public void likeOnFB() {

    }

    @Override
    public void twit(String message) {
        twit(message, "https://play.google.com/store/apps/details?id=" + bundleId);
    }

    @Override
    public void twit(String message, String url) {
        // Create intent using ACTION_VIEW and a normal Twitter url:
        String tweetUrl =
                String.format("https://twitter.com/intent/tweet?text=%s&url=%s",
                        urlEncode(message), urlEncode(url));
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl));

        // Narrow down to official Twitter app, if available:
        List<ResolveInfo> matches = activity.getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo info : matches) {
            if (info.activityInfo.packageName.toLowerCase().startsWith("com.twitter")) {
                intent.setPackage(info.activityInfo.packageName);
            }
        }
        activity.startActivity(intent);
    }

    @Override
    public void openReviewPage() {
        openURL("market://details?id=" + bundleId,
                "https://play.google.com/store/apps/details?id=" + bundleId);

    }


    private void openURL(String url, String fallbackURL) {
        try {
            startActivity(url);
        } catch (Exception e) {
            startActivity(fallbackURL);
        }
    }

    private void startActivity(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
    }
}
