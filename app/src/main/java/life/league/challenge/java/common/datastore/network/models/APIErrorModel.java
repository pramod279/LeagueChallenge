package life.league.challenge.java.common.datastore.network.models;

/**
 * Created by Pramod Selvaraj on 01-07-2021.
 * This Model Class is Used to Handle API Error Case
 */
public class APIErrorModel {
    private final int statusCode;
    private final String errorResponse;

    /**
     * Returns HTTP Status Code & Error Message
     *
     * @param statusCode
     * @param errorResponse
     */
    public APIErrorModel(int statusCode, String errorResponse) {
        this.statusCode = statusCode;
        this.errorResponse = errorResponse;
    }

    /**
     * Returns HTTP Status Code & Error Message
     *
     * @param httpStatus
     */
    public APIErrorModel(HttpStatus httpStatus) {
        this.statusCode = httpStatus.code;
        this.errorResponse = httpStatus.reasonPhrase;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorResponse() {
        return errorResponse;
    }
}