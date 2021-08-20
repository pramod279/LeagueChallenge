package life.league.challenge.java.common.datastore.preferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import life.league.challenge.java.R;

/**
 * Created by Pramod Selvaraj on 01-07-2021.
 * <p>
 * This Object manages the Preference Storage
 * Save & Retrieve Values from the Preference Storage
 * Supported OffersDataModel Types --> String, Integer, Float, Long, Boolean
 * Clear all Preference
 * Clear a particular preference value
 */
public class PreferenceStorage {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor edit;

    public static void init(Context context) {
        init(context, context.getString(R.string.app_name));
    }

    @SuppressLint("ApplySharedPref")
    private static void init(Context context, String preferenceName) {
        sharedPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        edit = sharedPreferences.edit();
        edit.commit();
    }

    /**
     * Function for Saving Integer value to Preference Storage
     *
     * @param key
     * @param value
     */
    public static void saveString(String key, String value) {
        edit.putString(key, value);
        edit.commit();
    }

    /**
     * Function for Retrieving String value from Preference Storage
     *
     * @param key
     * @return
     */
    public static String getString(String key) {
        if (key != null) return sharedPreferences.getString(key, "");
        else return "";
    }

    /**
     * Function for Saving Integer value to Preference Storage
     *
     * @param key
     * @param value
     */
    public static void saveInt(String key, int value) {
        edit.putInt(key, value);
        edit.commit();
    }

    /**
     * Function for Retrieving Integer value from Preference Storage
     *
     * @param key
     * @return
     */
    public static int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    /**
     * Function for Saving Float value to Preference Storage
     *
     * @param key
     * @param value
     */
    public static void saveFloat(String key, float value) {
        edit.putFloat(key, value);
        edit.commit();
    }

    /**
     * Function for Retrieving Float value from Preference Storage
     *
     * @param key
     * @return
     */
    public static float getFloat(String key) {
        return sharedPreferences.getFloat(key, 0f);
    }

    /**
     * Function for Saving Long value to Preference Storage
     *
     * @param key
     * @param value
     */
    public static void saveLong(String key, long value) {
        edit.putLong(key, value);
        edit.commit();
    }

    /**
     * Function for Retrieving Long value from Preference Storage
     *
     * @param key
     * @return
     */
    public static long getLong(String key) {
        return sharedPreferences.getLong(key, 0L);
    }

    /**
     * Function for Saving Boolean value to Preference Storage
     *
     * @param key
     * @param value
     */
    public static void saveBoolean(String key, boolean value) {
        edit.putBoolean(key, value);
        edit.commit();
    }

    /**
     * Function for Retrieving Boolean value from Preference Storage
     *
     * @param key
     * @return
     */
    public static boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    /**
     * Function for Checking if Preference Store Contains any Value based on the Key
     *
     * @param key
     * @return
     */
    public static boolean isPreferenceExist(String key) {
        return sharedPreferences.contains(key);
    }

    /**
     * Function for Removing a Particular Shared Preference Based on The Key
     *
     * @param key
     */
    public static void removePreference(String key) {
        edit.remove(key);
        edit.commit();
    }

    /**
     * Function for Clearing All Shared Preference
     */
    public static void clearAllSharedPreferences() {
        edit.clear();
        edit.commit();
    }
}