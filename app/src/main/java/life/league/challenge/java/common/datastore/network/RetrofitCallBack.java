package life.league.challenge.java.common.datastore.network;

import org.jetbrains.annotations.NotNull;

import life.league.challenge.java.common.datastore.network.models.APIErrorModel;
import life.league.challenge.java.common.datastore.network.models.DataModel;
import life.league.challenge.java.common.datastore.network.models.HttpStatus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pramod Selvaraj on 01-07-2021.
 * This class is Retrofit Call Back Class for Handling Success & Failure responses
 */
public abstract class RetrofitCallBack<T> implements Callback<DataModel<T>> {
    private static final String TAG = "RetrofitCallBack";

    @Override
    public void onResponse(@NotNull Call<DataModel<T>> call, Response<DataModel<T>> response) {
        if (response.isSuccessful() && response.body() != null) {
            RetrofitCallBack.this.onSuccess(new DataModel<>(response.body().getCode(),
                    response.body().getMessage(), response.body().getData()));
        } else {
            RetrofitCallBack.this.onFailure(new APIErrorModel(HttpStatus.valueOf(response.code())));
        }
    }

    @Override
    public void onFailure(@NotNull Call<DataModel<T>> call, @NotNull Throwable t) {
        if (t instanceof NetworkException) {
            RetrofitCallBack.this.onFailure(new APIErrorModel(HttpStatus
                    .valueOf(HttpStatus.NO_NETWORK)));
        } else if (t instanceof Exception) {
            RetrofitCallBack.this.onFailure(new APIErrorModel(HttpStatus
                    .valueOf(HttpStatus.RETROFIT_FAILURE)));
        }
    }

    /**
     * Invoked when the API Call is Successful
     *
     * @param dataModel
     */
    protected abstract void onSuccess(DataModel<T> dataModel);

    /**
     * Invoked when the API Call has failed
     *
     * @param error
     */
    protected abstract void onFailure(APIErrorModel error);
}