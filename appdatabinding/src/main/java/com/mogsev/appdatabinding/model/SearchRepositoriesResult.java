package com.mogsev.appdatabinding.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public class SearchRepositoriesResult {

    @Expose
    @SerializedName("total_count")
    private int totalCount;

    @Expose
    @SerializedName("items")
    private List<Repository> items;

    public int getTotalCount() {
        return totalCount;
    }

    public List<Repository> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "SearchRepositoriesResult{" +
                "totalCount=" + totalCount +
                ", items=" + items +
                '}';
    }
}
