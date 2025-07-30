package com.example.vts.Activity.TrackingRepor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetReportPagination {

    @SerializedName("categoryThreeFields")
    @Expose
    private Object categoryThreeFields;
    @SerializedName("categoryOneFields")
    @Expose
    private List<CategoryOneField> categoryOneFields = null;
    @SerializedName("categoryTwoFields")
    @Expose
    private Object categoryTwoFields;

    public Object getCategoryThreeFields() {
        return categoryThreeFields;
    }

    public void setCategoryThreeFields(Object categoryThreeFields) {
        this.categoryThreeFields = categoryThreeFields;
    }

    public List<CategoryOneField> getCategoryOneFields() {
        return categoryOneFields;
    }

    public void setCategoryOneFields(List<CategoryOneField> categoryOneFields) {
        this.categoryOneFields = categoryOneFields;
    }

    public Object getCategoryTwoFields() {
        return categoryTwoFields;
    }

    public void setCategoryTwoFields(Object categoryTwoFields) {
        this.categoryTwoFields = categoryTwoFields;
    }
}
