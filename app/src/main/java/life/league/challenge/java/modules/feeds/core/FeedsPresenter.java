package life.league.challenge.java.modules.feeds.core;

import java.util.ArrayList;
import java.util.List;

import life.league.challenge.java.common.api.APIFactory;
import life.league.challenge.java.common.api.OnRequestCallback;
import life.league.challenge.java.common.database.table.Post;
import life.league.challenge.java.common.database.table.User;
import life.league.challenge.java.common.datastore.network.APIConstants;
import life.league.challenge.java.common.datastore.preferences.PreferenceConstants;
import life.league.challenge.java.common.datastore.preferences.PreferenceStorage;
import life.league.challenge.java.common.utils.LeagueUtils;
import life.league.challenge.java.modules.feeds.viewmodel.FeedsViewModel;
import life.league.challenge.java.modules.models.Account;
import life.league.challenge.java.modules.models.PostsAlbums;
import life.league.challenge.java.modules.models.Users;

/**
 * Created by Pramod Selvaraj on 02-07-2021.
 * Social Media Feeds Presenter
 */
public class FeedsPresenter implements FeedsContract.ActivityPresenter {
    private static final String TAG = "FeedsPresenter";
    private final FeedsContract.FeedsView mFeedsView;
    private final FeedsViewModel mFeedsViewModel;

    public FeedsPresenter(FeedsContract.FeedsView activityView, FeedsViewModel feedsViewModel) {
        this.mFeedsView = activityView;
        this.mFeedsViewModel = feedsViewModel;
    }

    @Override
    public void login(String userName, String password) {
        final String auth = LeagueUtils.getInstance().constructAuth(userName, password);
        APIFactory.getInstance().login(auth, new OnRequestCallback<Account>() {
            @Override
            public void onAPISuccess(Account response) {
                /*Save Login Token To Shared Preference For Other API Calls*/
                PreferenceStorage.saveString(PreferenceConstants.API_KEY, response.getApiKey());
                /*Login Success, Fetch My Friends List*/
                fetchFriendsFeeds();
            }

            @Override
            public void onAPIFailure(String errorMessage) {
                if (mFeedsView != null) mFeedsView.onAPIFailure(errorMessage);
            }
        });
    }

    @Override
    public void fetchFriendsFeeds() {
        /*Fetch The Entire List Of My Friends*/
        fetchUsersList();
    }

    @Override
    public void fetchUsersList() {
        APIFactory.getInstance().users(new OnRequestCallback<List<Users>>() {
            @Override
            public void onAPISuccess(List<Users> usersList) {
                /*Function for Processing My Friends List*/
                if (usersList != null && usersList.size() > 0) {
                    processMyFriendsList(usersList);
                } else if (mFeedsView != null) {
                    mFeedsView.onAPIFailure(APIConstants.USERS_FAILURE_MSG);
                }
            }

            @Override
            public void onAPIFailure(String errorMessage) {
                if (mFeedsView != null) mFeedsView.onAPIFailure(errorMessage);
            }
        });
    }

    /**
     * Function for Processing My Friends List
     *
     * @param usersList
     */
    private void processMyFriendsList(List<Users> usersList) {
        /*Fetch My Users List Ids For Fetching Their Posts*/
        List<Integer> userIds = new ArrayList<>();
        for (int i = 0; i < usersList.size(); i++) {
            userIds.add(usersList.get(i).id);
            mFeedsViewModel.insertUser(new User(usersList.get(i).id, usersList
                    .get(i).avatar.thumbnail, usersList.get(i).username));
        }
        /*Call Fetch Posts API For Retrieving My Friends Posts*/
        fetchFriendsPosts(userIds);
    }

    @Override
    public void fetchFriendsPosts(List<Integer> userIds) {
        APIFactory.getInstance().posts(userIds, new OnRequestCallback<List<PostsAlbums>>() {
            @Override
            public void onAPISuccess(List<PostsAlbums> userPosts) {
                /*Function for Processing My Friends Posts*/
                if (userPosts != null && userPosts.size() > 0) {
                    processMyFriendsPosts(userPosts);
                } else if (mFeedsView != null) {
                    mFeedsView.onAPIFailure(APIConstants.POSTS_FAILURE_MSG);
                }
            }

            @Override
            public void onAPIFailure(String errorMessage) {
                if (mFeedsView != null) mFeedsView.onAPIFailure(errorMessage);
            }
        });
    }

    /**
     * Function for Processing My Friends Posts
     *
     * @param userPosts
     */
    private void processMyFriendsPosts(List<PostsAlbums> userPosts) {
        for (int i = 0; i < userPosts.size(); i++) {
            mFeedsViewModel.insertFeed(new Post(userPosts.get(i).id, userPosts.get(i)
                    .userId, userPosts.get(i).title, userPosts.get(i).body));
        }
        /*User Feeds Constructed & Ready To Be Displayed*/
        if (mFeedsView != null) mFeedsView.onFeedsFetchSuccess();
    }
}