package asiantech.quocnp.pronuciation_alarm;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.orm.SugarApp;

import org.androidannotations.annotations.EApplication;

import java.util.Locale;

import asiantech.quocnp.pronuciation_alarm.activity.BaseActivity;


/**
 * Created by tientun on 3/4/15.
 *
 */
@EApplication
public class App extends SugarApp {
    private static App sInstance = null;

    private BaseActivity mCurrentActivity = null;

    /**
     * Get instance of app
     *
     * @return app
     */
    public static synchronized App getInstance() {
        if (sInstance == null) {
            sInstance = new App();
        }
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = new Locale("en");
        res.updateConfiguration(conf, dm);

        //Setup universal image loader
        ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .memoryCache(new WeakMemoryCache())
                .build();
        ImageLoader.getInstance().init(imageLoaderConfiguration);

       /* //Setup Api client
        ApiConfig apiConfig = ApiConfig.builder(getApplicationContext())
                .baseUrl(getResources().getString(R.string.url_base))
                .build();
        ApiClient.getInstance().init(apiConfig);*/

        /*//Setup google analytic
        AnalyticsTrackers.initialize(this);
        AnalyticsTrackers.getInstance().get(AnalyticsTrackers.Target.APP);*/
    }

    /**
     * Get current activity
     *
     * @return current activity
     */
    public BaseActivity getCurrentActivity() {
        return mCurrentActivity;
    }

    /**
     * Save current activity
     *
     * @param currentActivity visible activity
     */
    public void setCurrentActivity(BaseActivity currentActivity) {
        mCurrentActivity = currentActivity;
    }
/*

    */
/**
     * Get google analytic tracker
     *
     * @return Tracker
     *//*


    public synchronized Tracker getGoogleAnalyticsTracker() {
        AnalyticsTrackers analyticsTrackers = AnalyticsTrackers.getInstance();
        return analyticsTrackers.get(AnalyticsTrackers.Target.APP);
    }

    */
/***
     * Tracking screen view
     *
     * @param screenName screen name to be displayed on GA dashboard
     *//*

    public void trackScreenView(String screenName) {
        Tracker tracker = getGoogleAnalyticsTracker();

        // Set screen name.
        tracker.setScreenName(screenName);

        // Send a screen view.
        tracker.send(new HitBuilders.ScreenViewBuilder().build());

        GoogleAnalytics.getInstance(this).dispatchLocalHits();
    }

    */
/***
     * Tracking exception
     *
     * @param e exception to be tracked
     *//*

    public void trackException(Exception e) {
        if (e != null) {
            Tracker tracker = getGoogleAnalyticsTracker();

            tracker.send(new HitBuilders.ExceptionBuilder()
                            .setDescription(
                                    new StandardExceptionParser(this, null)
                                            .getDescription(Thread.currentThread().getName(), e))
                            .setFatal(false)
                            .build()
            );
        }
    }

    */
/***
     * Tracking event
     *
     * @param resId res id
     *//*

    public void trackEvent(Context context, int resId) {
        Tracker tracker = getGoogleAnalyticsTracker();

        // Build and send an Event.
        tracker.send(new HitBuilders.EventBuilder().setCategory(context.getString(R.string.text_category)).setAction(context.getString(R.string.text_action)).setLabel(context.getString(resId)).build());
    }

    */
/**
     * [Custom dimension setting]
     * This is method custom Dimensions GA
     *
     * @param screenName screen name to be displayed on GA dashboard
     *//*

    public void customDimensions(String screenName, String uuId) {
        Tracker tracker = getGoogleAnalyticsTracker();
        // Set screen name.
        tracker.setScreenName(screenName);
        // Send the custom dimension value with a screen view.
        // Note that the value only needs to be sent once.
        tracker.send(new HitBuilders.ScreenViewBuilder()
                        //application implementation type]
                        .setCustomDimension(4, "ネイティブ")
                        .setCustomDimension(5, String.valueOf(getVersionCode()))//version code
                        .setCustomDimension(6, "SP") //Subsite
                        .setCustomDimension(8, uuId)//UID site
                        .build()
        );
    }
*/

    /**
     * This is get version app
     *
     * @return Version Code
     *//*
    private int getVersionCode() {
        return BuildConfig.VERSION_CODE;
    }*/
}
