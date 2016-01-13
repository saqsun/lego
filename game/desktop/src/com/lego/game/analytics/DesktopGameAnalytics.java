package com.lego.game.analytics;

import java.util.List;

/**
 * Created by sargis on 12/25/15.
 */
public class DesktopGameAnalytics implements GameAnalytics {
    @Override
    public void configureAvailableCustomDimensions01(List<String> customDimensions) {
        
    }

    @Override
    public void configureAvailableCustomDimensions02(List<String> customDimensions) {

    }

    @Override
    public void configureAvailableCustomDimensions03(List<String> customDimensions) {

    }

    @Override
    public void configureAvailableResourceCurrencies(List<String> resourceCurrencies) {

    }

    @Override
    public void configureAvailableResourceItemTypes(List<String> resourceItemTypes) {

    }

    @Override
    public void configureBuild(String build) {

    }

    @Override
    public void configureSdkVersion(String wrapperSdkVersion) {

    }

    @Override
    public void configureEngineVersion(String engineVersion) {

    }

    @Override
    public void init() {

    }

    @Override
    public void addBusinessEvent(String currency, int amount, String itemType, String itemId, String cartType, String receipt) {

    }

    @Override
    public void addBusinessEvent(String currency, int amount, String itemType, String itemId, String cartType, boolean autoFetchReceipt) {

    }

    @Override
    public void addResourceEvent(GameAnalyticsResourceFlowType flowType, String currency, float amount, String itemType, String itemId) {

    }

    @Override
    public void addProgressionEvent(GameAnalyticsProgressionStatus progressionStatus, String progression01, String progression02, String progression03) {

    }

    @Override
    public void addProgressionEvent(GameAnalyticsProgressionStatus progressionStatus, String progression01, String progression02, String progression03, int score) {

    }

    @Override
    public void addDesignEvent(String eventId) {

    }

    @Override
    public void addDesignEvent(String eventId, double value) {

    }

    @Override
    public void addErrorEvent(GameAnalyticsErrorSeverity severity, String message) {

    }

    @Override
    public void setEnabledInfoLog(boolean flag) {

    }

    @Override
    public void setEnabledVerboseLog(boolean flag) {

    }

    @Override
    public void setCustomDimension01(String dimension01) {

    }

    @Override
    public void setCustomDimension02(String dimension02) {

    }

    @Override
    public void setCustomDimension03(String dimension03) {

    }

    @Override
    public void setFacebookId(String facebookId) {

    }

    @Override
    public void setGender(String gender) {

    }

    @Override
    public void setBirthYear(int birthYear) {

    }
}
