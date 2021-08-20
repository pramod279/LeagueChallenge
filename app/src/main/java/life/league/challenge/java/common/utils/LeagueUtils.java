package life.league.challenge.java.common.utils;

import android.util.Base64;

/**
 * Created by Pramod Selvaraj on 02-07-2021.
 * This class manages the League App Utilities
 */
public class LeagueUtils {
    private static LeagueUtils leagueUtils = null;

    /**
     * Function for fetching instance
     *
     * @return
     */
    public static LeagueUtils getInstance() {
        if (leagueUtils == null) {
            leagueUtils = new LeagueUtils();
            return leagueUtils;
        } else return leagueUtils;
    }

    /**
     * Function for Constructing Auth Using Username & Password For Login API Call
     *
     * @param userName
     * @param password
     * @return
     */
    public String constructAuth(String userName, String password) {
        final String credentials = String.format("%s:%s", userName, password);
        return "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
    }
}