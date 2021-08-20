package life.league.challenge.java.common.datastore.network;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Pramod Selvaraj on 01-07-2021.
 * This is the Authorization Interceptor Class To Check if User Token Has Expired & Renew The Token
 */
public class AuthInterceptor implements Interceptor {
    private static final String TAG = "AuthInterceptor";
    private final HashMap<String, Object> mHeaders;

    AuthInterceptor(HashMap<String, Object> headers) {
        mHeaders = headers;
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request mainRequest = chain.request();
        /*Add Request Headers*/
        Request.Builder requestBuilder = mainRequest.newBuilder();
        if (mHeaders != null) for (Object object : mHeaders.entrySet()) {
            Map.Entry pair = (Map.Entry) object;
            requestBuilder.addHeader(pair.getKey().toString(), pair.getValue().toString());
        }
        return chain.proceed(requestBuilder.build());
    }
}