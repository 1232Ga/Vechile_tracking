package com.example.vts.Activity.AddVechicle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddVechiclegetset {
    @SerializedName("IsSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;
    @SerializedName("OperationData")
    @Expose
    private String operationData;
    @SerializedName("SuccessMessage")
    @Expose
    private String successMessage;
    @SerializedName("ID")
    @Expose
    private String id;
    @SerializedName("ResultData")
    @Expose
    private Object resultData;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getOperationData() {
        return operationData;
    }

    public void setOperationData(String operationData) {
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

    public Object getResultData() {
        return resultData;
    }

    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }
}
