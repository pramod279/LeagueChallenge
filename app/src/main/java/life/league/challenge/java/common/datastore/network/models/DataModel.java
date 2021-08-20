package life.league.challenge.java.common.datastore.network.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Pramod Selvaraj on 01-07-2021.
 * This Model Class is Used as A Base Network Model for All Retrofit Calls
 */
public class DataModel<T> implements Serializable {
    /**
     * The server response code.
     */
    @SerializedName("code")
    private int code;

    /**
     * The server response message.
     */
    @SerializedName("message")
    private String message;

    /**
     * The server response message.
     */
    @SerializedName("data")
    private T data;

    /**
     * Returns API Request Body
     *
     * @param code
     * @param message
     * @param data
     */
    public DataModel(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}