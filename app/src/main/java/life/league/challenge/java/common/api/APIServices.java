package life.league.challenge.java.common.api;

import java.util.List;

import life.league.challenge.java.common.datastore.network.APIConstants;
import life.league.challenge.java.modules.models.Account;
import life.league.challenge.java.modules.models.Photos;
import life.league.challenge.java.modules.models.PostsAlbums;
import life.league.challenge.java.modules.models.Users;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Pramod Selvaraj on 01-07-2021.
 * API Services for League Challenge Application
 */
public interface APIServices {
    /**
     * Login & Fetch Authorization Token
     *
     * @param credentials
     * @return
     */
    @GET(APIConstants.LOGIN)
    Call<Account> login(@Header(APIConstants.HEADER_AUTH) String credentials);

    /**
     * Fetch Users List
     *
     * @return
     */
    @GET(APIConstants.USERS)
    Call<List<Users>> users();

    /**
     * Fetch Posts List
     *
     * @param userIds
     * @return
     */
    @GET(APIConstants.POSTS)
    Call<List<PostsAlbums>> posts(@Query("userId") List<Integer> userIds);

    /**
     * Fetch Albums List
     *
     * @param userId
     * @return
     */
    @GET(APIConstants.ALBUMS)
    Call<List<PostsAlbums>> albums(@Query("userId") int userId);

    /**
     * Fetch Photos List
     *
     * @param albumId
     * @return
     */
    @GET(APIConstants.PHOTOS)
    Call<List<Photos>> photos(@Query("albumId") int albumId);
}