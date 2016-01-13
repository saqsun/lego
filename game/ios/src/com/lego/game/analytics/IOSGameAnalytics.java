package com.lego.game.analytics;

import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSNumber;

import java.util.List;

/**
 * Created by sargis on 7/21/15.
 */
public class IOSGameAnalytics implements GameAnalytics {
    private final String gameKey;
    private final String secretKey;

    public IOSGameAnalytics(String gameKey, String secretKey) {
        this.gameKey = gameKey;
        this.secretKey = secretKey;
    }

    private static org.robovm.pods.gameanalytics.GAResourceFlowType getNativeFlowType(GameAnalyticsResourceFlowType flowType) {
        org.robovm.pods.gameanalytics.GAResourceFlowType nativeFlowType =
                org.robovm.pods.gameanalytics.GAResourceFlowType.ink;
        switch (flowType) {
            case Sink:
                nativeFlowType = org.robovm.pods.gameanalytics.GAResourceFlowType.ink;
                break;
            case Source:
                nativeFlowType = org.robovm.pods.gameanalytics.GAResourceFlowType.ource;
                break;
        }
        return nativeFlowType;
    }

    private static org.robovm.pods.gameanalytics.GAErrorSeverity getNativeSeverity(GameAnalyticsErrorSeverity severity) {
        org.robovm.pods.gameanalytics.GAErrorSeverity nativeErrorSeverity =
                org.robovm.pods.gameanalytics.GAErrorSeverity.Critical;
        switch (severity) {
            case Critical:
                nativeErrorSeverity = org.robovm.pods.gameanalytics.GAErrorSeverity.Critical;
                break;
            case Debug:
                nativeErrorSeverity = org.robovm.pods.gameanalytics.GAErrorSeverity.Debug;
                break;
            case Error:
                nativeErrorSeverity = org.robovm.pods.gameanalytics.GAErrorSeverity.Error;
                break;
            case Info:
                nativeErrorSeverity = org.robovm.pods.gameanalytics.GAErrorSeverity.Info;
                break;
            case Warning:
                nativeErrorSeverity = org.robovm.pods.gameanalytics.GAErrorSeverity.Warning;
                break;
        }
        return nativeErrorSeverity;
    }

    private static org.robovm.pods.gameanalytics.GAProgressionStatus getNativeProgressionStatus(GameAnalyticsProgressionStatus progressionStatus) {
        org.robovm.pods.gameanalytics.GAProgressionStatus nativeProgressionStatus =
                org.robovm.pods.gameanalytics.GAProgressionStatus.Start;
        switch (progressionStatus) {
            case Start:
                nativeProgressionStatus = org.robovm.pods.gameanalytics.GAProgressionStatus.Start;
                break;
            case Complete:
                nativeProgressionStatus = org.robovm.pods.gameanalytics.GAProgressionStatus.Complete;
                break;
            case Fail:
                nativeProgressionStatus = org.robovm.pods.gameanalytics.GAProgressionStatus.Fail;
                break;
        }
        return nativeProgressionStatus;
    }

    @Override
    public void configureAvailableCustomDimensions01(List<String> customDimensions) {
        org.robovm.pods.gameanalytics.GameAnalytics.
                configureAvailableCustomDimensions01(NSArray.fromStrings(customDimensions));
    }

    @Override
    public void configureAvailableCustomDimensions02(List<String> customDimensions) {
        org.robovm.pods.gameanalytics.GameAnalytics.
                configureAvailableCustomDimensions02(NSArray.fromStrings(customDimensions));
    }

    @Override
    public void configureAvailableCustomDimensions03(List<String> customDimensions) {
        org.robovm.pods.gameanalytics.GameAnalytics.
                configureAvailableCustomDimensions03(NSArray.fromStrings(customDimensions));
    }

    @Override
    public void configureAvailableResourceCurrencies(List<String> resourceCurrencies) {
        org.robovm.pods.gameanalytics.GameAnalytics.
                configureAvailableResourceCurrencies(NSArray.fromStrings(resourceCurrencies));
    }

    @Override
    public void configureAvailableResourceItemTypes(List<String> resourceItemTypes) {
        org.robovm.pods.gameanalytics.GameAnalytics.
                configureAvailableResourceItemTypes(NSArray.fromStrings(resourceItemTypes));
    }

    @Override
    public void configureBuild(String build) {
        org.robovm.pods.gameanalytics.GameAnalytics.
                configureBuild(build);
    }

    @Override
    public void configureSdkVersion(String wrapperSdkVersion) {
        org.robovm.pods.gameanalytics.GameAnalytics.
                configureSdkVersion(wrapperSdkVersion);
    }

    @Override
    public void configureEngineVersion(String engineVersion) {
        org.robovm.pods.gameanalytics.GameAnalytics.
                configureEngineVersion(engineVersion);
    }

    @Override
    public void init() {
        org.robovm.pods.gameanalytics.GameAnalytics.init(gameKey, secretKey);
    }

    @Override
    public void addBusinessEvent(String currency, int amount, String itemType,
                                 String itemId, String cartType, String receipt) {
        org.robovm.pods.gameanalytics.GameAnalytics.
                addBusinessEvent(currency, amount, itemType, itemId, cartType, receipt);
    }

    @Override
    public void addBusinessEvent(String currency, int amount, String itemType,
                                 String itemId, String cartType, boolean autoFetchReceipt) {
        org.robovm.pods.gameanalytics.GameAnalytics.
                addBusinessEvent(currency, amount, itemType, itemId, cartType, autoFetchReceipt);
    }

    @Override
    public void addResourceEvent(GameAnalyticsResourceFlowType flowType, String currency, float amount,
                                 String itemType, String itemId) {
        org.robovm.pods.gameanalytics.GameAnalytics.
                addResourceEvent(getNativeFlowType(flowType), currency, NSNumber.valueOf(amount), itemType, itemId);
    }

    @Override
    public void addProgressionEvent(GameAnalyticsProgressionStatus progressionStatus, String progression01,
                                    String progression02, String progression03) {

        org.robovm.pods.gameanalytics.GameAnalytics.
                addProgressionEvent(getNativeProgressionStatus(progressionStatus), progression01, progression02, progression03);

    }

    @Override
    public void addProgressionEvent(GameAnalyticsProgressionStatus progressionStatus, String progression01,
                                    String progression02, String progression03, int score) {
        org.robovm.pods.gameanalytics.GameAnalytics.
                addProgressionEvent(getNativeProgressionStatus(progressionStatus), progression01, progression02, progression03, score);
    }

    @Override
    public void addDesignEvent(String eventId) {
        org.robovm.pods.gameanalytics.GameAnalytics.
                addDesignEvent(eventId);
    }

    @Override
    public void addDesignEvent(String eventId, double value) {
        org.robovm.pods.gameanalytics.GameAnalytics.
                addDesignEvent(eventId, NSNumber.valueOf(value));
    }

    @Override
    public void addErrorEvent(GameAnalyticsErrorSeverity severity, String message) {
        org.robovm.pods.gameanalytics.GameAnalytics.
                addErrorEvent(getNativeSeverity(severity), message);
    }

    @Override
    public void setEnabledInfoLog(boolean flag) {
        org.robovm.pods.gameanalytics.GameAnalytics.
                setEnabledInfoLog(flag);
    }

    @Override
    public void setEnabledVerboseLog(boolean flag) {
        org.robovm.pods.gameanalytics.GameAnalytics.
                setEnabledVerboseLog(flag);
    }

    @Override
    public void setCustomDimension01(String dimension01) {
        org.robovm.pods.gameanalytics.GameAnalytics.
                setCustomDimension01(dimension01);
    }

    @Override
    public void setCustomDimension02(String dimension02) {
        org.robovm.pods.gameanalytics.GameAnalytics.
                setCustomDimension02(dimension02);
    }

    @Override
    public void setCustomDimension03(String dimension03) {
        org.robovm.pods.gameanalytics.GameAnalytics.
                setCustomDimension03(dimension03);
    }

    @Override
    public void setFacebookId(String facebookId) {
        org.robovm.pods.gameanalytics.GameAnalytics.
                setFacebookId(facebookId);
    }

    @Override
    public void setGender(String gender) {
        org.robovm.pods.gameanalytics.GameAnalytics.
                setGender(gender);
    }

    @Override
    public void setBirthYear(int birthYear) {
        org.robovm.pods.gameanalytics.GameAnalytics.
                setBirthYear(birthYear);
    }
}
