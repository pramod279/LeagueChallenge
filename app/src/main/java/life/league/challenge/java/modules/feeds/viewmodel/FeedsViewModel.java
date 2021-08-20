package life.league.challenge.java.modules.feeds.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import life.league.challenge.java.common.database.FeedRepository;
import life.league.challenge.java.common.database.table.Post;
import life.league.challenge.java.common.database.table.User;
import life.league.challenge.java.modules.models.UserFeed;

/**
 * View Model to keep a reference to the feeds repository and
 * an up-to-date list of all the feeds.
 */
public class FeedsViewModel extends AndroidViewModel {
    private final LiveData<List<UserFeed>> mAllFeeds;
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private final FeedRepository mRepository;

    public FeedsViewModel(Application application) {
        super(application);
        mRepository = new FeedRepository(application);
        mAllFeeds = mRepository.getAllFeeds();
    }

    public void insertUser(User user) {
        mRepository.insertUser(user);
    }

    public void insertFeed(Post post) {
        mRepository.insertFeed(post);
    }

    public LiveData<List<UserFeed>> getAllFeeds() {
        return mAllFeeds;
    }

    public void deleteFeeds() {
        mRepository.deleteFeed();
    }
}