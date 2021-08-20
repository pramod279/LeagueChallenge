package life.league.challenge.java.modules.models;

import com.google.gson.annotations.SerializedName;

public class Account {
    @SerializedName("api_key")
    private String mApiKey;

    public String getApiKey() {
        return mApiKey;
    }
}