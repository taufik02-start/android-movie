package com.example.movielibrary.Model;

import com.google.gson.annotations.SerializedName;

public class Movie extends BaseModel<MovieData> {
    @SerializedName("total_results")
    private  int total_results;
    @SerializedName("total_pages")
    private  int total_pages;

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }
}
