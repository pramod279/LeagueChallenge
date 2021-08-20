package life.league.challenge.java.modules.feeds.viewmodel;

import static org.mockito.MockitoAnnotations.openMocks;

import android.app.Application;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import life.league.challenge.java.common.database.table.Post;
import life.league.challenge.java.common.database.table.User;

class FeedsViewModelTest {

    @Mock
    private Application mockApplication;

    private FeedsViewModel feedsViewModelUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this);
        feedsViewModelUnderTest = new FeedsViewModel(mockApplication);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    void testInsertUser() {
        // Setup
        final User user = new User(0, "avatar", "username");

        // Run the test
        feedsViewModelUnderTest.insertUser(user);

        // Verify the results
    }

    @Test
    void testInsertFeed() {
        // Setup
        final Post post = new Post(0, 0, "title", "description");

        // Run the test
        feedsViewModelUnderTest.insertFeed(post);

        // Verify the results
    }

    @Test
    void testDeleteFeeds() {
        // Setup

        // Run the test
        feedsViewModelUnderTest.deleteFeeds();

        // Verify the results
    }
}
