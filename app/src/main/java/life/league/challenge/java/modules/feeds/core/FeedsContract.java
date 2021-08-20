package life.league.challenge.java.modules.feeds.core;

import java.util.List;

/**
 * Created by Pramod Selvaraj on 02-07-2021.
 * Social Media Feeds Contract
 */
public interface FeedsContract {

    interface FeedsView {
        /**
         * User Feeds Success Callback
         */
        void onFeedsFetchSuccess();

        /**
         * API Failure Callback
         *
         * @param errorResponseMessage: API Error response
         */
        void onAPIFailure(String errorResponseMessage);
    }

    interface ActivityPresenter {
        /**
         * Function for Login User & Fetching Auth Token
         *
         * @param userName
         * @param password
         */
        void login(String userName, String password);

        /**
         * Function for Fetching My Friends Posts
         */
        void fetchFriendsFeeds();

        /**
         * Function for Fetching My Friends List
         */
        void fetchUsersList();

        /**
         * Function for Fetching The Posts By My Friends
         *
         * @param userIds
         */
        void fetchFriendsPosts(List<Integer> userIds);
    }
}