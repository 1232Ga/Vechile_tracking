package com.example.vts.Activity.CurrentSummaryReport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentSummarygetset {

    @SerializedName("IsSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("ErrorMessage")
    @Expose
    private Object errorMessage;
    @SerializedName("OperationData")
    @Expose
    private Object operationData;
    @SerializedName("SuccessMessage")
    @Expose
    private Object successMessage;
    @SerializedName("ID")
    @Expose
    private Object id;
    @SerializedName("ResultData")
    @Expose
    private ResultData resultData;

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

    public Object getOperationData() {
        return operationData;
    }

    public void setOperationData(Object operationData) {
        this.operationData = operationData;
    }

    public Object getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(Object successMessage) {
        this.successMessage = successMessage;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public ResultData getResultData() {
        return resultData;
    }

    public void setResultData(ResultData resultData) {
        this.resultData = resultData;
    }
}
