package life.league.challenge.java.common.utils;

import android.util.Log;

import life.league.challenge.java.BuildConfig;

/**
 * Created by Pramod Selvaraj on 01-07-2021.
 * This is the Timber Helper Extension For Logging
 */
public class Timber {
    /**
     * Debug Log
     */
    public static void loggerD(String tag, String message) {
        if (BuildConfig.DEBUG) Log.d(tag, message);
    }

    /**
     * Error Log
     */
    public static void loggerE(String tag, String message) {
        if (BuildConfig.DEBUG) Log.e(tag, message);
    }

    /**
     * Info Log
     */
    public static void loggerI(String tag, String message) {
        if (BuildConfig.DEBUG) Log.i(tag, message);
    }

    /**
     * Verbose Log
     */
    public static void loggerV(String tag, String message) {
        if (BuildConfig.DEBUG) Log.v(tag, message);
    }
}