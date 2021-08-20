package life.league.challenge.java.common.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Pramod Selvaraj on 01-07-2021.
 * This Class Monitor's Network State Change
 */
public class NetworkMonitor extends BroadcastReceiver {
    private static NetworkMonitor networkMonitor;
    public ConnectivityReceiverListener connectivityReceiverListener = null;

    public static NetworkMonitor getInstance() {
        if (networkMonitor == null) networkMonitor = new NetworkMonitor();
        return networkMonitor;
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ?
                connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (connectivityReceiverListener != null)
            connectivityReceiverListener.onNetworkConnectionChanged(isNetworkAvailable(context));
    }

    public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged(boolean isConnected);
    }
}