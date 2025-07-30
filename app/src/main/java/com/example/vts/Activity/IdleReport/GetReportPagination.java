package com.example.vts.Activity.IdleReport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetReportPagination {
    @SerializedName("categoryThreeFields")
    @Expose
    private Object categoryThreeFields;
    @SerializedName("categoryOneFields")
    @Expose
    private Object categoryOneFields;
    @SerializedName("categoryTwoFields")
    @Expose
    private List<CategoryTwoField> categoryTwoFields = null;

    public Object getCategoryThreeFields() {
        return categoryThreeFields;
    }

    public void setCategoryThreeFields(Object categoryThreeFields) {
        this.categoryThreeFields = categoryThreeFields;
    }

    public Object getCategoryOneFields() {
        return categoryOneFields;
    }

    public void setCategoryOneFields(Object categoryOneFields) {
        this.categoryOneFields = categoryOneFields;
    }

    public List<CategoryTwoField> getCategoryTwoFields() {
        return categoryTwoFields;
    }

    public void setCategoryTwoFields(List<CategoryTwoField> categoryTwoFields) {
        this.categoryTwoFields = categoryTwoFields;
    }
}
