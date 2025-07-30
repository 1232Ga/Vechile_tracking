package com.example.vts.Activity.CurrentSummaryReport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetReportPagination {

    @SerializedName("categoryThreeFields")
    @Expose
    private CategoryThreeFields categoryThreeFields;
    @SerializedName("categoryOneFields")
    @Expose
    private Object categoryOneFields;
    @SerializedName("categoryTwoFields")
    @Expose
    private Object categoryTwoFields;

    public CategoryThreeFields getCategoryThreeFields() {
        return categoryThreeFields;
    }

    public void setCategoryThreeFields(CategoryThreeFields categoryThreeFields) {
        this.categoryThreeFields = categoryThreeFields;
    }

    public Object getCategoryOneFields() {
        return categoryOneFields;
    }

    public void setCategoryOneFields(Object categoryOneFields) {
        this.categoryOneFields = categoryOneFields;
    }

    public Object getCategoryTwoFields() {
        return categoryTwoFields;
    }

    public void setCategoryTwoFields(Object categoryTwoFields) {
        this.categoryTwoFields = categoryTwoFields;
    }

}
