package com.example.vts.Activity.RoleDetepack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Deletegetset {
    @SerializedName("IsSuccess")
    @Expose
    private String isSuccess;
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
    private String resultData;

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
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

    public String getResultData() {
        return resultData;
    }

    public void setResultData(String resultData) {
        this.resultData = resultData;
    }
}
