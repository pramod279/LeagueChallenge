package life.league.challenge.java.modules.feeds.core;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;

import life.league.challenge.java.common.database.table.Post;
import life.league.challenge.java.common.database.table.User;
import life.league.challenge.java.modules.feeds.viewmodel.FeedsViewModel;

class FeedsPresenterTest {

    @Mock
    private FeedsViewModel mockFeedsViewModel;

    private FeedsPresenter feedsPresenterUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this);
        feedsPresenterUnderTest = new FeedsPresenter(null, mockFeedsViewModel);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    void testLogin() {
        // Setup

        // Run the test
        feedsPresenterUnderTest.login("userName", "password");

        // Verify the results
        verify(mockFeedsViewModel).insertUser(any(User.class));
        verify(mockFeedsViewModel).insertFeed(any(Post.class));
    }

    @Test
    void testFetchFriendsFeeds() {
        // Setup

        // Run the test
        feedsPresenterUnderTest.fetchFriendsFeeds();

        // Verify the results
        verify(mockFeedsViewModel).insertUser(any(User.class));
        verify(mockFeedsViewModel).insertFeed(any(Post.class));
    }

    @Test
    void testFetchUsersList() {
        // Setup

        // Run the test
        feedsPresenterUnderTest.fetchUsersList();

        // Verify the results
        verify(mockFeedsViewModel).insertUser(any(User.class));
        verify(mockFeedsViewModel).insertFeed(any(Post.class));
    }

    @Test
    void testFetchFriendsPosts() {
        // Setup

        // Run the test
        feedsPresenterUnderTest.fetchFriendsPosts(Arrays.asList(0));

        // Verify the results
        verify(mockFeedsViewModel).insertFeed(any(Post.class));
    }
}
