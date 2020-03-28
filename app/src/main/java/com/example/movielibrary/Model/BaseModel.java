package com.example.movielibrary.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseModel<T> {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<T> results;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> result) {
        this.results = result;
    }
}
