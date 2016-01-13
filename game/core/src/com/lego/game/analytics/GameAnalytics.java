package com.lego.game.analytics;

import java.util.List;

/**
 * Created by sargis on 7/21/15.
 */
public interface GameAnalytics {

    void configureAvailableCustomDimensions01(List<String> customDimensions);

    void configureAvailableCustomDimensions02(List<String> customDimensions);

    void configureAvailableCustomDimensions03(List<String> customDimensions);

    void configureAvailableResourceCurrencies(List<String> resourceCurrencies);

    void configureAvailableResourceItemTypes(List<String> resourceItemTypes);

    void configureBuild(String build);

    void configureSdkVersion(String wrapperSdkVersion);

    void configureEngineVersion(String engineVersion);

    void init();

    void addBusinessEvent(String currency, int amount, String itemType, String itemId,
                          String cartType, String receipt);

    void addBusinessEvent(String currency, int amount, String itemType, String itemId,
                          String cartType, boolean autoFetchReceipt);

    void addResourceEvent(GameAnalyticsResourceFlowType flowType, String currency, float amount,
                          String itemType, String itemId);

    void addProgressionEvent(GameAnalyticsProgressionStatus progressionStatus, String progression01,
                             String progression02, String progression03);

    void addProgressionEvent(GameAnalyticsProgressionStatus progressionStatus, String progression01,
                             String progression02, String progression03, int score);

    void addDesignEvent(String eventId);

    void addDesignEvent(String eventId, double value);

    void addErrorEvent(GameAnalyticsErrorSeverity severity, String message);

    void setEnabledInfoLog(boolean flag);

    void setEnabledVerboseLog(boolean flag);

    void setCustomDimension01(String dimension01);

    void setCustomDimension02(String dimension02);

    void setCustomDimension03(String dimension03);

    void setFacebookId(String facebookId);

    void setGender(String gender);

    void setBirthYear(int birthYear);
}
