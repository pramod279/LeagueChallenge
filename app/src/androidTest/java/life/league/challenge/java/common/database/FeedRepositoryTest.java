package life.league.challenge.java.common.database;

import static org.mockito.MockitoAnnotations.openMocks;

import android.app.Application;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import life.league.challenge.java.common.database.table.Post;
import life.league.challenge.java.common.database.table.User;

class FeedRepositoryTest {

    @Mock
    private Application mockApplication;

    private FeedRepository feedRepositoryUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this);
        feedRepositoryUnderTest = new FeedRepository(mockApplication);
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
        feedRepositoryUnderTest.insertUser(user);

        // Verify the results
    }

    @Test
    void testInsertFeed() {
        // Setup
        final Post post = new Post(0, 0, "title", "description");

        // Run the test
        feedRepositoryUnderTest.insertFeed(post);

        // Verify the results
    }

    @Test
    void testDeleteFeed() {
        // Setup

        // Run the test
        feedRepositoryUnderTest.deleteFeed();

        // Verify the results
    }
}
