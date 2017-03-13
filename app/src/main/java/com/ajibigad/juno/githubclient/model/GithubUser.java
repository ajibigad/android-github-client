package com.ajibigad.juno.githubclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Julius on 07/03/2017.
 */
public class GithubUser {

//    Username
//    Profile photo
//    Github profile URL
//    Button to share their profile, and it should launch a share intent and the content of the share should be “Check out this awesome developer @<github username>, <github profile url>.”
//    Clicking on the Github url should launch the browser and link to their Github page;

    private long id;

    @SerializedName("login")
    private String username;

    @SerializedName("avatar_url")
    private String profileImageUrl;

    @SerializedName("html_url")
    private String profileURL;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }
}
