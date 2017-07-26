package com.litedoid.orachat.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pagination
{
    @SerializedName("current_page")
    @Expose
    private int currentPage;

    @SerializedName("per_page")
    @Expose
    private int perPage;

    @SerializedName("page_count")
    @Expose
    private int pageCount;

    @SerializedName("total_count")
    @Expose
    private int totalCount;

}