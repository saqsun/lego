package com.lego.game.analytics;

import android.app.Activity;
import com.gameanalytics.sdk.GAPlatform;
import com.gameanalytics.sdk.StringVector;

import java.util.List;

/**
 * Created by sargis on 7/21/15.
 */
public class AndroidGameAnalytics implements GameAnalytics {
    private static final String GOOGLE_PLAY_STORE = "google_play";

    static {
        System.loadLibrary("GameAnalytics");
    }

    private final Activity activity;
    private final String gameKey;
    private final String secretKey;
    private final String signature;

    public AndroidGameAnalytics(Activity activity, String gameKey, String secretKey, String signature) {
        this.activity = activity;
        this.gameKey = gameKey;
        this.secretKey = secretKey;
        this.signature = signature;
    }

    private static com.gameanalytics.sdk.GAResourceFlowType getNativeFlowType(GameAnalyticsResourceFlowType flowType) {
        com.gameanalytics.sdk.GAResourceFlowType nativeFlowType =
                com.gameanalytics.sdk.GAResourceFlowType.GAResourceFlowTypeSink;
        switch (flowType) {
            case Sink:
                nativeFlowType = com.gameanalytics.sdk.GAResourceFlowType.GAResourceFlowTypeSink;
                break;
            case Source:
                nativeFlowType = com.gameanalytics.sdk.GAResourceFlowType.GAResourceFlowTypeSource;
                break;
        }
        return nativeFlowType;
    }

    private static com.gameanalytics.sdk.GAProgressionStatus getNativeProgressionStatus(GameAnalyticsProgressionStatus progressionStatus) {
        com.gameanalytics.sdk.GAProgressionStatus nativeProgressionStatus =
                com.gameanalytics.sdk.GAProgressionStatus.GAProgressionStatusStart;
        switch (progressionStatus) {
            case Start:
                nativeProgressionStatus = com.gameanalytics.sdk.GAProgressionStatus.GAProgressionStatusStart;
                break;
            case Complete:
                nativeProgressionStatus = com.gameanalytics.sdk.GAProgressionStatus.GAProgressionStatusComplete;
                break;
            case Fail:
                nativeProgressionStatus = com.gameanalytics.sdk.GAProgressionStatus.GAProgressionStatusFail;
                break;
        }
        return nativeProgressionStatus;
    }

    private static com.gameanalytics.sdk.GAErrorSeverity getNativeSeverity(GameAnalyticsErrorSeverity severity) {
        com.gameanalytics.sdk.GAErrorSeverity nativeErrorSeverity =
                com.gameanalytics.sdk.GAErrorSeverity.GAErrorSeverityCritical;
        switch (severity) {
            case Critical:
                nativeErrorSeverity = com.gameanalytics.sdk.GAErrorSeverity.GAErrorSeverityCritical;
                break;
            case Debug:
                nativeErrorSeverity = com.gameanalytics.sdk.GAErrorSeverity.GAErrorSeverityDebug;
                break;
            case Error:
                nativeErrorSeverity = com.gameanalytics.sdk.GAErrorSeverity.GAErrorSeverityError;
                break;
            case Info:
                nativeErrorSeverity = com.gameanalytics.sdk.GAErrorSeverity.GAErrorSeverityInfo;
                break;
            case Warning:
                nativeErrorSeverity = com.gameanalytics.sdk.GAErrorSeverity.GAErrorSeverityWarning;
                break;
        }
        return nativeErrorSeverity;
    }

    private static StringVector toStringVector(List<?> customDimensions) {
        StringVector stringVector = new StringVector();
        for (Object customDimension : customDimensions) {
            stringVector.add((String) customDimension);
        }
        return stringVector;
    }

    @Override
    public void configureAvailableCustomDimensions01(List<String> customDimensions) {
        com.gameanalytics.sdk.GameAnalytics.configureAvailableCustomDimensions01(toStringVector(customDimensions));
    }

    @Override
    public void configureAvailableCustomDimensions02(List<String> customDimensions) {
        com.gameanalytics.sdk.GameAnalytics.configureAvailableCustomDimensions02(toStringVector(customDimensions));
    }

    @Override
    public void configureAvailableCustomDimensions03(List<String> customDimensions) {
        com.gameanalytics.sdk.GameAnalytics.configureAvailableCustomDimensions03(toStringVector(customDimensions));
    }

    @Override
    public void configureAvailableResourceCurrencies(List<String> resourceCurrencies) {
        com.gameanalytics.sdk.GameAnalytics.configureAvailableResourceCurrencies(toStringVector(resourceCurrencies));
    }

    @Override
    public void configureAvailableResourceItemTypes(List<String> resourceItemTypes) {
        com.gameanalytics.sdk.GameAnalytics.configureAvailableResourceItemTypes(toStringVector(resourceItemTypes));
    }

    @Override
    public void configureBuild(String build) {
        com.gameanalytics.sdk.GameAnalytics.configureBuild(build);
    }

    @Override
    public void configureSdkVersion(String wrapperSdkVersion) {
        com.gameanalytics.sdk.GameAnalytics.configureSdkWrapperVersion(wrapperSdkVersion);
    }

    @Override
    public void configureEngineVersion(String engineVersion) {
        com.gameanalytics.sdk.GameAnalytics.configureGameEngineVersion(engineVersion);
    }

    @Override
    public void init() {
        GAPlatform.initializeWithActivity(activity);
        com.gameanalytics.sdk.GameAnalytics.initializeWithGameKey(gameKey, secretKey);
    }

    @Override
    public void addBusinessEvent(String currency, int amount, String itemType, String itemId, String cartType, String receipt) {
        com.gameanalytics.sdk.GameAnalytics.addBusinessEventWithCurrency(currency, amount, itemType, itemId, cartType,
                receipt, GOOGLE_PLAY_STORE, signature);
    }

    @Override
    public void addBusinessEvent(String currency, int amount, String itemType, String itemId, String cartType, boolean autoFetchReceipt) {
        com.gameanalytics.sdk.GameAnalytics.addBusinessEventWithCurrency(currency, amount, itemType, itemId, cartType);
    }

    @Override
    public void addResourceEvent(GameAnalyticsResourceFlowType flowType, String currency, float amount, String itemType, String itemId) {
        com.gameanalytics.sdk.GameAnalytics.addResourceEventWithFlowType(getNativeFlowType(flowType), currency, amount, itemType, itemId);
    }

    @Override
    public void addProgressionEvent(GameAnalyticsProgressionStatus progressionStatus, String progression01, String progression02, String progression03) {
        if (progression02 == null) {
            progression02 = "";
        }
        if (progression03 == null) {
            progression03 = "";
        }
        com.gameanalytics.sdk.GameAnalytics.addProgressionEventWithProgressionStatus(getNativeProgressionStatus(progressionStatus), progression01, progression02, progression03);
    }

    @Override
    public void addProgressionEvent(GameAnalyticsProgressionStatus progressionStatus, String progression01, String progression02, String progression03, int score) {
        if (progression02 == null) {
            progression02 = "";
        }
        if (progression03 == null) {
            progression03 = "";
        }
        com.gameanalytics.sdk.GameAnalytics.addProgressionEventWithProgressionStatus(getNativeProgressionStatus(progressionStatus), progression01, progression02, progression03, score);
    }

    @Override
    public void addDesignEvent(String eventId) {
        com.gameanalytics.sdk.GameAnalytics.addDesignEventWithEventId(eventId);
    }

    @Override
    public void addDesignEvent(String eventId, double value) {
        com.gameanalytics.sdk.GameAnalytics.addDesignEventWithEventId(eventId, value);
    }

    @Override
    public void addErrorEvent(GameAnalyticsErrorSeverity severity, String message) {
        com.gameanalytics.sdk.GameAnalytics.addErrorEventWithSeverity(getNativeSeverity(severity), message);
    }

    @Override
    public void setEnabledInfoLog(boolean flag) {
        com.gameanalytics.sdk.GameAnalytics.setEnabledInfoLog(flag);
    }

    @Override
    public void setEnabledVerboseLog(boolean flag) {
        com.gameanalytics.sdk.GameAnalytics.setEnabledVerboseLog(flag);
    }

    @Override
    public void setCustomDimension01(String dimension01) {
        com.gameanalytics.sdk.GameAnalytics.setCustomDimension01(dimension01);
    }

    @Override
    public void setCustomDimension02(String dimension02) {
        com.gameanalytics.sdk.GameAnalytics.setCustomDimension02(dimension02);
    }

    @Override
    public void setCustomDimension03(String dimension03) {
        com.gameanalytics.sdk.GameAnalytics.setCustomDimension02(dimension03);
    }

    @Override
    public void setFacebookId(String facebookId) {
        com.gameanalytics.sdk.GameAnalytics.setFacebookId(facebookId);
    }

    @Override
    public void setGender(String gender) {
        com.gameanalytics.sdk.GameAnalytics.setGender(gender);
    }

    @Override
    public void setBirthYear(int birthYear) {
        com.gameanalytics.sdk.GameAnalytics.setBirthYear(birthYear);
    }
}
