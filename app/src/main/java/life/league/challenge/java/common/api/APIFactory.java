package life.league.challenge.java.common.api;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

import life.league.challenge.java.BuildConfig;
import life.league.challenge.java.common.datastore.network.APIConstants;
import life.league.challenge.java.common.datastore.network.RetrofitBinder;
import life.league.challenge.java.common.datastore.preferences.PreferenceConstants;
import life.league.challenge.java.common.datastore.preferences.PreferenceStorage;
import life.league.challenge.java.modules.models.Account;
import life.league.challenge.java.modules.models.Photos;
import life.league.challenge.java.modules.models.PostsAlbums;
import life.league.challenge.java.modules.models.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pramod Selvaraj on 01-07-2021.
 * API Factory Class For All API Transactions for League Challenge Application
 */
public class APIFactory {
    private static final String TAG = "APIFactory";
    private static APIFactory instance;

    public static APIFactory getInstance() {
        if (instance == null) instance = new APIFactory();
        return instance;
    }

    /**
     * Function for Setting Common API Headers
     */
    private HashMap<String, Object> setAPIHeaders() {
        HashMap<String, Object> headers = new HashMap<>();
        /*Language Header*/
        headers.put(APIConstants.HEADER_ACCESS_TOKEN, PreferenceStorage.getString(PreferenceConstants.API_KEY));
        return headers;
    }

    /**
     * Function for Calling Login API & Fetch Auth Token
     *
     * @param mAuth
     * @param listener
     */
    public void login(String mAuth, final OnRequestCallback<Account> listener) {
        Call<Account> apiCall = RetrofitBinder.getServiceInstance(BuildConfig.BASE_URL).login(mAuth);
        apiCall.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(@NotNull Call<Account> call, @NotNull Response<Account> response) {
                listener.onAPISuccess(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<Account> call, @NotNull Throwable t) {
                listener.onAPIFailure(APIConstants.LOGIN_FAILURE_MSG);
            }
        });
    }

    /**
     * Function for Fetching The Users List API
     *
     * @param listener
     */
    public void users(final OnRequestCallback<List<Users>> listener) {
        Call<List<Users>> apiCall = RetrofitBinder.getServiceInstance
                (BuildConfig.BASE_URL, setAPIHeaders()).users();
        apiCall.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(@NotNull Call<List<Users>> call, @NotNull Response<List<Users>> response) {
                listener.onAPISuccess(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<List<Users>> call, @NotNull Throwable t) {
                listener.onAPIFailure(APIConstants.USERS_FAILURE_MSG);
            }
        });
    }

    /**
     * Function for Fetching The Posts List API
     *
     * @param userIds
     * @param listener
     */
    public void posts(List<Integer> userIds, final OnRequestCallback<List<PostsAlbums>> listener) {
        Call<List<PostsAlbums>> apiCall = RetrofitBinder.getServiceInstance
                (BuildConfig.BASE_URL, setAPIHeaders()).posts(userIds);
        apiCall.enqueue(new Callback<List<PostsAlbums>>() {
            @Override
            public void onResponse(@NotNull Call<List<PostsAlbums>> call,
                                   @NotNull Response<List<PostsAlbums>> response) {
                listener.onAPISuccess(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<List<PostsAlbums>> call, @NotNull Throwable t) {
                listener.onAPIFailure(APIConstants.POSTS_FAILURE_MSG);
            }
        });
    }

    /**
     * Function for Fetching The Albums List API
     *
     * @param userId
     * @param listener
     */
    public void albums(int userId, final OnRequestCallback<List<PostsAlbums>> listener) {
        Call<List<PostsAlbums>> apiCall = RetrofitBinder.getServiceInstance
                (BuildConfig.BASE_URL, setAPIHeaders()).albums(userId);
        apiCall.enqueue(new Callback<List<PostsAlbums>>() {
            @Override
            public void onResponse(@NotNull Call<List<PostsAlbums>> call, @NotNull Response<List<PostsAlbums>> response) {
                listener.onAPISuccess(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<List<PostsAlbums>> call, @NotNull Throwable t) {
                listener.onAPIFailure(APIConstants.ALBUMS_FAILURE_MSG);
            }
        });
    }

    /**
     * Function for Fetching The Photos List API
     *
     * @param albumId
     * @param listener
     */
    public void photos(int albumId, final OnRequestCallback<List<Photos>> listener) {
        Call<List<Photos>> apiCall = RetrofitBinder.getServiceInstance
                (BuildConfig.BASE_URL, setAPIHeaders()).photos(albumId);
        apiCall.enqueue(new Callback<List<Photos>>() {
            @Override
            public void onResponse(@NotNull Call<List<Photos>> call, @NotNull Response<List<Photos>> response) {
                listener.onAPISuccess(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<List<Photos>> call, @NotNull Throwable t) {
                listener.onAPIFailure(APIConstants.PHOTOS_FAILURE_MSG);
            }
        });
    }
}