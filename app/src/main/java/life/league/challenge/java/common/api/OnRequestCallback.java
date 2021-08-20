package life.league.challenge.java.common.api;

/**
 * Created by Pramod Selvaraj on 01-07-2021.
 * API Call Response Callbacks
 */
public interface OnRequestCallback<T> {
    /**
     * API Success Callbacks
     *
     * @param response
     */
    void onAPISuccess(T response);

    /**
     * API Failure Callbacks
     *
     * @param errorMessage
     */
    void onAPIFailure(String errorMessage);
}