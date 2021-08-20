package life.league.challenge.java.common.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import life.league.challenge.java.common.database.table.Post;
import life.league.challenge.java.common.database.table.User;
import life.league.challenge.java.common.datastore.database.LeagueDatabase;
import life.league.challenge.java.modules.models.UserFeed;

/**
 * Created by Pramod Selvaraj on 03-07-2021.
 * This class Manages The League App Repository
 */
public class FeedRepository {
    private final FeedsDao mFeedDao;
    private final LiveData<List<UserFeed>> mAllFeeds;

    public FeedRepository(Application application) {
        LeagueDatabase db = LeagueDatabase.getDatabase(application);
        mFeedDao = db.feedsDao();
        mAllFeeds = mFeedDao.getAllFeeds();
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insertUser(User user) {
        LeagueDatabase.databaseWriteExecutor.execute(() -> mFeedDao.insertOrUpdateUser(user));
    }

    public void insertFeed(Post post) {
        LeagueDatabase.databaseWriteExecutor.execute(() -> mFeedDao.insertOrUpdateFeed(post));
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<UserFeed>> getAllFeeds() {
        return mAllFeeds;
    }

    public void deleteFeed() {
        LeagueDatabase.databaseWriteExecutor.execute(mFeedDao::deleteAll);
    }
}