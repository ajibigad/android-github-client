package com.ajibigad.juno.githubclient.network;

import com.ajibigad.juno.githubclient.model.GithubUser;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Julius on 08/03/2017.
 */
public class GithubResponse {

    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("incomplete_results")
    private boolean incompleteResults;

    @SerializedName("items")
    private List<GithubUser> githubUserList;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<GithubUser> getGithubUserList() {
        return githubUserList;
    }

    public void setGithubUserList(List<GithubUser> githubUserList) {
        this.githubUserList = githubUserList;
    }
}
