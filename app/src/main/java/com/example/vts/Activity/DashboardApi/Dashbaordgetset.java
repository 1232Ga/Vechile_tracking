package com.example.vts.Activity.DashboardApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Dashbaordgetset {
    @SerializedName("IsSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;
    @SerializedName("OperationData")
    @Expose
    private Object operationData;
    @SerializedName("SuccessMessage")
    @Expose
    private String successMessage;
    @SerializedName("ID")
    @Expose
    private String id;
    @SerializedName("ResultData")
    @Expose
    private ResultData resultData;
    @SerializedName("ReturnString")
    @Expose
    private Object returnString;

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getOperationData() {
        return operationData;
    }

    public void setOperationData(Object operationData) {
        this.operationData = operationData;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ResultData getResultData() {
        return resultData;
    }

    public void setResultData(ResultData resultData) {
        this.resultData = resultData;
    }

    public Object getReturnString() {
        return returnString;
    }

    public void setReturnString(Object returnString) {
        this.returnString = returnString;
    }

}
