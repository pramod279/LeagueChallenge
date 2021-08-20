package life.league.challenge.java.modules.feeds;

import android.os.Bundle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FeedsActivityTest {

    private FeedsActivity feedsActivityUnderTest;

    @BeforeEach
    void setUp() {
        feedsActivityUnderTest = new FeedsActivity();
    }

    @Test
    void testOnFeedsFetchSuccess() {
        // Setup

        // Run the test
        feedsActivityUnderTest.onFeedsFetchSuccess();

        // Verify the results
    }

    @Test
    void testOnAPIFailure() {
        // Setup

        // Run the test
        feedsActivityUnderTest.onAPIFailure("errorResponseMessage");

        // Verify the results
    }

    @Test
    void testOnCreate() {
        // Setup
        final Bundle savedInstanceState = new Bundle(0);

        // Run the test
        feedsActivityUnderTest.onCreate(savedInstanceState);

        // Verify the results
    }

    @Test
    void testOnResume() {
        // Setup

        // Run the test
        feedsActivityUnderTest.onResume();

        // Verify the results
    }

    @Test
    void testOnPause() {
        // Setup

        // Run the test
        feedsActivityUnderTest.onPause();

        // Verify the results
    }

    @Test
    void testOnDestroy() {
        // Setup

        // Run the test
        feedsActivityUnderTest.onDestroy();

        // Verify the results
    }
}
