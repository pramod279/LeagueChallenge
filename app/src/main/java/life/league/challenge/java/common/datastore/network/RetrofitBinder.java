package life.league.challenge.java.common.datastore.network;

import java.util.HashMap;

import life.league.challenge.java.BuildConfig;
import life.league.challenge.java.common.api.APIServices;
import life.league.challenge.java.common.utils.Timber;

/**
 * Created by Pramod Selvaraj on 01-07-2021.
 * This class binds the Retrofit Class With The API Service.
 */
public class RetrofitBinder extends RetrofitFactory {
    /**
     * Retrofit Service Without Headers
     *
     * @return
     */
    public static APIServices getServiceInstance() {
        return getRetrofit(BuildConfig.BASE_URL).create(APIServices.class);
    }

    /**
     * Retrofit Service With Custom URL & Without Headers
     *
     * @return
     */
    public static APIServices getServiceInstance(String URL) {
        return getRetrofit(URL).create(APIServices.class);
    }

    /**
     * Retrofit Service With Headers
     *
     * @param headers
     * @return
     */
    public static APIServices getServiceInstance(HashMap<String, Object> headers) {
        Timber.loggerD("RetrofitBinder", "Header:" + headers.toString());
        return getRetrofit(headers).create(APIServices.class);
    }

    /**
     * Retrofit Service With Custom URL & Headers
     *
     * @param URL
     * @param headers
     * @return
     */
    public static APIServices getServiceInstance(String URL, HashMap<String, Object> headers) {
        Timber.loggerD("RetrofitBinder", "Header:" + headers.toString());
        return getRetrofit(URL, headers).create(APIServices.class);
    }
}