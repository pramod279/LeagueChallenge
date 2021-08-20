package life.league.challenge.java.common.datastore.network;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import life.league.challenge.java.common.receivers.NetworkMonitor;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Pramod Selvaraj on 01-07-2021.
 * This is the Network Interceptor for Monitoring Network Changes
 */
class NetworkInterceptor implements Interceptor {
    private final Context mContext;

    NetworkInterceptor(Context context) {
        mContext = context;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        if (!NetworkMonitor.getInstance().isNetworkAvailable(mContext))
            throw new NetworkException();

        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }
}