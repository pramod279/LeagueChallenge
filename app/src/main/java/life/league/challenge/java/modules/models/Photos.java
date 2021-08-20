package life.league.challenge.java.modules.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photos {
    @SerializedName("albumId")
    @Expose
    private Integer albumId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("thumbnailUrl")
    @Expose
    private String thumbnailUrl;
}