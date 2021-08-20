package life.league.challenge.java;

import android.app.Application;

import life.league.challenge.java.common.datastore.preferences.PreferenceStorage;

/**
 * Created by Pramod Selvaraj on 01-07-2021.
 * League Application Class
 */
public class LeagueApp extends Application {
    private static LeagueApp mInstance;

    public static LeagueApp getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        components();
    }

    /**
     * League App Components
     */
    private void components() {
        /*Initialise Preference Storage*/
        PreferenceStorage.init(this);
    }
}