package life.league.challenge.java.common.datastore.network;

/**
 * Created by Pramod Selvaraj on 01-07-2021.
 * This class contains the API Constants
 */
public class APIConstants {
    /****************API Header Parameters*****************/
    public static final String HEADER_AUTH = "Authorization";
    public static final String HEADER_ACCESS_TOKEN = "x-access-token";

    /********************API End Points********************/
    public static final String LOGIN = "login";
    public static final String USERS = "users";
    public static final String POSTS = "posts";
    public static final String ALBUMS = "albums";
    public static final String PHOTOS = "photos";

    /******************API Error Messages******************/
    public static final String LOGIN_FAILURE_MSG = "Failed to Login";
    public static final String USERS_FAILURE_MSG = "Failed to Retrieve Users";
    public static final String POSTS_FAILURE_MSG = "Failed to Retrieve Posts";
    public static final String ALBUMS_FAILURE_MSG = "Failed to Retrieve Albums";
    public static final String PHOTOS_FAILURE_MSG = "Failed to Retrieve Photos";
}