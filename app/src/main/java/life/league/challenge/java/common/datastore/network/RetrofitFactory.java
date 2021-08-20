package life.league.challenge.java.common.datastore.network;

import android.annotation.SuppressLint;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import life.league.challenge.java.BuildConfig;
import life.league.challenge.java.LeagueApp;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pramod Selvaraj on 01-07-2021.
 * This is the Retrofit Factory Class Which Performs all The API Request Calls
 */
abstract class RetrofitFactory {
    private static final int CONNECT_TIME_OUT = 1;
    private static final int READ_TIME_OUT = 60; //Default 30
    private static final int WRITE_TIME_OUT = 60; //Default 15

    /**
     * Retrofit Instance Without Headers
     *
     * @param mBaseURL
     * @return
     */
    static Retrofit getRetrofit(String mBaseURL) {
        return getRetrofitClient(mBaseURL, null);
    }

    /**
     * Retrofit Instance With Headers
     *
     * @param headers
     * @return
     */
    static Retrofit getRetrofit(HashMap<String, Object> headers) {
        return getRetrofitClient(BuildConfig.BASE_URL, headers);
    }

    /**
     * Retrofit Instance With Custom URL & Headers
     *
     * @param mBaseURL
     * @param headers
     * @return
     */
    static Retrofit getRetrofit(String mBaseURL, HashMap<String, Object> headers) {
        return getRetrofitClient(mBaseURL, headers);
    }

    /**
     * Function for Initialising Retrofit Client
     *
     * @param BASE_URL
     * @param headers
     * @return
     */
    private static Retrofit getRetrofitClient(String BASE_URL, HashMap<String, Object> headers) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getUnsafeOkHttpClient(headers))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Return Ok HTTP Client by Ignoring SSL Certification
     * Accept Any SSL Certificate & Ignore the SSL Handshake
     *
     * @return
     */
    @SuppressLint("TrustAllX509TrustManager")
    private static OkHttpClient getUnsafeOkHttpClient(HashMap<String, Object> headers) {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
                                                       String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
                                                       String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier((hostname, session) -> true);

            // No Network Connection Interceptor
            builder.addInterceptor(new NetworkInterceptor(LeagueApp.getInstance()));
            // User Access Token Expired Interceptor
            builder.addInterceptor(new AuthInterceptor(headers));

            // Logging HTTP Responses In Debug Mode
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(loggingInterceptor);
            }

            // HTTP Request Connection Timeout
            builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.MINUTES);
            builder.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS);
            builder.writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS);
            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}