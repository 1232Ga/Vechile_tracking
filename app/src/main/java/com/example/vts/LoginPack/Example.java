package com.example.vts.LoginPack;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Example {

    @SerializedName("IsAuthenticated")
    @Expose
    private Boolean isAuthenticated;
    @SerializedName("Token")
    @Expose
    private String token;
    @SerializedName("IsSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("ErrorMessage")
    @Expose
    private Object errorMessage;
    @SerializedName("SuccessMessage")
    @Expose
    private Object successMessage;
    @SerializedName("TokenExpires")
    @Expose
    private String tokenExpires;

    public Boolean getIsAuthenticated() {
        return isAuthenticated;
    }

    public void setIsAuthenticated(Boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(Object successMessage) {
        this.successMessage = successMessage;
    }

    public String getTokenExpires() {
        return tokenExpires;
    }

    public void setTokenExpires(String tokenExpires) {
        this.tokenExpires = tokenExpires;
    }

}