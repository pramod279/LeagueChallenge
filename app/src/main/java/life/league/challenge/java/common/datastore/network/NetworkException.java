package life.league.challenge.java.common.datastore.network;

import java.io.IOException;

/**
 * Created by Pramod Selvaraj on 01-07-2021.
 * This is the "No Internet Connection Exception"
 */
public class NetworkException extends IOException {

    @Override
    public String getMessage() {
        return "No network available, please check your WiFi or Data connection...";
    }
}