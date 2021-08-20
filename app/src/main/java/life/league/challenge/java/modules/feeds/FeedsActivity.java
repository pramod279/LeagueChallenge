package life.league.challenge.java.modules.feeds;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;

import life.league.challenge.java.R;
import life.league.challenge.java.common.datastore.preferences.PreferenceConstants;
import life.league.challenge.java.common.datastore.preferences.PreferenceStorage;
import life.league.challenge.java.modules.feeds.adapters.FeedsAdapter;
import life.league.challenge.java.modules.feeds.core.FeedsContract;
import life.league.challenge.java.modules.feeds.core.FeedsPresenter;
import life.league.challenge.java.modules.feeds.viewmodel.FeedsViewModel;

/**
 * Created by Pramod Selvaraj on 01-07-2021.
 * Social Media Feeds Activity
 */
public class FeedsActivity extends AppCompatActivity implements FeedsContract.FeedsView {
    private static final String TAG = "FeedsActivity";
    private FeedsViewModel mFeedsViewModel;
    private FeedsPresenter mActivityPresenter;
    private ShimmerFrameLayout mShimmerLayout;
    private RecyclerView mRVUserFeeds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeds);
        /*Make the Contents Of Status Bar To Black As Toolbar Color Is White*/
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        initViews();
        /*Function for Fetching User Feeds*/
        fetchUserFeeds();
    }

    /**
     * Function for Initialising Views
     */
    private void initViews() {
        /*Get a new or existing ViewModel from the ViewModelProvider*/
        mFeedsViewModel = new ViewModelProvider(this).get(FeedsViewModel.class);
        mActivityPresenter = new FeedsPresenter(this, mFeedsViewModel);
        mShimmerLayout = findViewById(R.id.shimmer_layout);

        mRVUserFeeds = findViewById(R.id.rv_user_feeds);
        final FeedsAdapter adapter = new FeedsAdapter(new FeedsAdapter.WordDiff());
        mRVUserFeeds.setAdapter(adapter);
        mRVUserFeeds.setLayoutManager(new LinearLayoutManager(this));

        // Add an observer on the LiveData returned by getAllFeeds.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        // Update the cached copy of the feeds in the adapter.
        mFeedsViewModel.getAllFeeds().observe(this, adapter::submitList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startShimmer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopShimmer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFeedsViewModel.deleteFeeds();
    }

    /**
     * Function for Fetching User Feeds
     * Call Login If No Authentication Token Available
     * Call Fetch User Feeds
     */
    private void fetchUserFeeds() {
        if (mActivityPresenter != null) {
            if (PreferenceStorage.getString(PreferenceConstants.API_KEY).isEmpty()) {
                mActivityPresenter.login("Hello", "World");
            } else {
                mActivityPresenter.fetchFriendsFeeds();
            }
        }
    }

    @Override
    public void onFeedsFetchSuccess() {
        stopShimmer();
        mRVUserFeeds.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAPIFailure(String errorResponseMessage) {
        stopShimmer();
        Toast.makeText(this, errorResponseMessage, Toast.LENGTH_SHORT).show();
    }

    /**
     * Start Shimmer Animation
     */
    private void startShimmer() {
        mShimmerLayout.startShimmer();
    }

    /**
     * Stop Shimmer Animation
     */
    private void stopShimmer() {
        mShimmerLayout.stopShimmer();
        mShimmerLayout.setVisibility(View.GONE);
    }
}