package com.lego.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.lego.game.analytics.GameAnalytics;
import com.lego.game.analytics.GameAnalyticsErrorSeverity;
import com.lego.game.assets.LegoAssetsResolver;
import com.lego.game.screen.LegoScreen;
import com.lego.game.screen.SplashScreen;
import com.lego.game.utils.FPSRenderer;
import com.lego.mvc.*;
import com.lego.mvc.Runnable;
import com.lego.mvc.patterns.observer.LegoObserver;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by sargis on 12/20/15.
 */
public abstract class LegoGame implements ApplicationListener, View {
    private static final String TAG = LegoGame.class.getCanonicalName();
    private static final String LANGUAGE_ZH_HANS = "zh-hans";
    private static final String LANGUAGE_ZH_HANT = "zh-hant";
    private static final String LANGUAGE_ZH_CN = "zh-cn";
    private static final String LANGUAGE_ZH_TW = "zh-tw";
    private static final String LANGUAGE_ZH_SG = "zh-sg";
    private static final String LANGUAGE_ZH_HK = "zh-hk";
    private static final String LANGUAGE_ZH_MO = "zh-mo";
    @Inject
    public Facade facade;
    @Inject
    public Injector injector;
    @Inject
    public LegoAssetsResolver legoAssetsResolver;
    @Inject
    public GameAnalytics gameAnalytics;
    protected final String storeId;
    protected final String bundleId;
    protected String gameGeneralPreferencesName;
    protected String appLaunchPreferencesKey;
    protected boolean allAssetsAreLoaded;
    protected FPSRenderer fpsRenderer;
    protected Array<Preferences> preferencesList;
    protected Preferences gameGeneralPreferences;
    protected long appLaunch;
    protected String notificationId;
    private ObjectMap<String, Array<Observer>> observerMap;
    private ObjectMap<String, Mediator> mediatorMap;

    public LegoGame(String bundleId, String storeId) {
        this.bundleId = bundleId;
        this.storeId = storeId;
        this.mediatorMap = new ObjectMap<>();
        this.observerMap = new ObjectMap<>();
        preferencesList = new Array<>();
        gameGeneralPreferencesName = bundleId;
        appLaunchPreferencesKey = bundleId + ".app_launch";
    }

    protected static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("URLEncoder.encode() failed for " + s);
        }
    }

    public abstract String getVersion();

    public String getLanguage() {
        String language = retrieveLanguage();
        try {
            switch (language) {
                case LANGUAGE_ZH_HANS:
                case LANGUAGE_ZH_SG:
                    language = LANGUAGE_ZH_CN;
                    break;
                case LANGUAGE_ZH_HANT:
                case LANGUAGE_ZH_HK:
                case LANGUAGE_ZH_MO:
                    language = LANGUAGE_ZH_TW;
                    break;
                default:
                    language = language.split("-")[0];
                    break;
            }
        } catch (Exception e) {
            language = "en";
        }
        return language;
    }

    //TODO: change this fucking name, it is not self explanatory
    public long getAppLaunch() {
        return appLaunch;
    }

    @Override
    public void create() {
        try {
            createGame();
        } catch (Exception e) {
            trackCriticalErrorEvent(e);
        }
    }

    protected void createGame() {
        gameAnalytics.init();
        allAssetsAreLoaded = false;
        fpsRenderer = new FPSRenderer();
        gameGeneralPreferences = Gdx.app.getPreferences(gameGeneralPreferencesName);
        appLaunch = gameGeneralPreferences.getLong(appLaunchPreferencesKey, 0);
        ++appLaunch;
        gameGeneralPreferences.putLong(appLaunchPreferencesKey, appLaunch);
        addPreferences();
        setScreen(new SplashScreen());
        legoAssetsResolver.init();
        legoAssetsResolver.load();
    }

    public void addPreferences() {
        preferencesList.add(gameGeneralPreferences);
    }

    protected void renderGame() {
        if (!allAssetsAreLoaded) {
            if (legoAssetsResolver.update()) {
                allAssetsAreLoaded = true;
                initGame();
            }
        }
        fpsRenderer.render();
    }

    protected void initGame() {
        registerProxies();
        registerCommands();
        initScreens();
    }

    @Override
    public void render() {
        try {
            renderGame();
        } catch (Exception e) {
            trackCriticalErrorEvent(e);
        }
    }

    @Override
    public void dispose() {
        if (getScreen() != null) {
            getScreen().hide();
        }
        legoAssetsResolver.dispose();
        fpsRenderer.dispose();
    }

    @Override
    public void pause() {
        for (Preferences preferences : preferencesList) {
            preferences.flush();
        }
    }

    @Override
    public void notifyObservers(Notification note) {
        Array<Observer> observersRef = observerMap.get(note.getName());
        if (observersRef != null) {

            // Copy observers from reference array to working array,
            // since the reference array may change during the
            //notification loop
            Object[] observers = observersRef.toArray();

            // Notify Observers from the working array
            for (int i = 0; i < observers.length; i++) {
                Observer observer = (Observer) observers[i];
                observer.notifyObserver(note);
            }
        }
    }

    @Override
    public void removeObserver(String notificationName, Object notifyContext) {
        // the observer list for the notification under inspection
        Array<Observer> observers = observerMap.get(notificationName);

        if (observers != null) {
            // find the observer for the notifyContext
            for (int i = 0; i < observers.size; i++) {
                Observer observer = observers.get(i);
                if (observer.compareNotifyContext(notifyContext))
                    observers.removeValue(observer, true);
            }

            // Also, when a PureNotification's PureObserver list length falls to
            // zero, delete the notification key from the observer map
            if (observers.size == 0)
                observerMap.remove(notificationName);
        }
    }

    @Override
    public void registerMediator(final Mediator mediator) {
        if (this.mediatorMap.containsKey(mediator.getMediatorName()))
            return;

        // Register the PureMediator for retrieval by NAME
        this.mediatorMap.put(mediator.getMediatorName(), mediator);

        // Get PureNotification interests, if any.
        String[] noteInterests = mediator.listNotificationInterests();
        if (noteInterests.length != 0) {
            // Create java style pureRunnable ref to mediator.handleNotification
            Runnable pureRunnable = new Runnable() {
                public void run(Notification notification) {
                    mediator.handleNotification(notification);
                }
            };

            // Create PureObserver
            Observer observer = new LegoObserver(pureRunnable, mediator);

            // Register PureMediator as PureObserver for its list of PureNotification
            // interests
            for (int i = 0; i < noteInterests.length; i++)
                registerObserver(noteInterests[i], observer);
        }

        // alert the mediator that it has been registered
        mediator.onRegister();
    }

    @Override
    public void registerObserver(String notificationName, Observer observer) {
        if (this.observerMap.get(notificationName) == null)
            this.observerMap.put(notificationName, new Array<Observer>());

        Array<Observer> observers = this.observerMap.get(notificationName);
        observers.add(observer);
    }

    @Override
    public Mediator removeMediator(String mediatorName) {
        // Retrieve the named mediator
        Mediator mediator = mediatorMap.get(mediatorName);

        if (mediator != null) {
            // for every notification this mediator is interested in...
            String[] interests = mediator.listNotificationInterests();
            for (int i = 0; i < interests.length; i++) {
                // remove the observer linking the mediator
                // to the notification interest
                removeObserver(interests[i], mediator);
            }

            // remove the mediator from the map
            mediatorMap.remove(mediatorName);

            // alert the mediator that it has been removed
            mediator.onRemove();
        }

        return mediator;
    }

    @Override
    public Mediator retrieveMediator(String mediatorName) {
        return this.mediatorMap.get(mediatorName);
    }

    @Override
    public boolean hasMediator(String mediatorName) {
        return mediatorMap.containsKey(mediatorName);
    }

    private void trackCriticalErrorEvent(Object object) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        if (object instanceof Exception) {
            ((Exception) object).printStackTrace(pw);
        } else {
            ((Throwable) object).printStackTrace(pw);
        }
        pw.flush();
        Gdx.app.error(TAG, "Error : " + sw.toString());
        addCriticalErrorEvent(sw.toString());
    }

    protected abstract void registerCommands();

    protected abstract void registerProxies();

    public String getGameStartNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    protected void addCriticalErrorEvent(String string) {
        gameAnalytics.addErrorEvent(GameAnalyticsErrorSeverity.Critical, string);
    }

    protected abstract void initScreens();

    protected abstract String retrieveLanguage();

    public abstract void showAlertDialog(String title, String message);

    public abstract LegoScreen getScreen();

    public abstract void setScreen(LegoScreen screen);

    public String getBundleId() {
        return bundleId;
    }

    public abstract void twit(String message);

    public abstract void twit(String message, String url);

    public abstract void openReviewPage();

    public abstract void likeOnFB();
}
