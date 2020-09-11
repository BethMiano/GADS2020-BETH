package com.example.leaderboard2020;

import com.google.gson.annotations.SerializedName;

//Skills model

public class Skills {
    @SerializedName("name")
    private String name;

    @SerializedName("score")
    private String scores;

    @SerializedName("country")
    private String country;

    @SerializedName("badgeUrl")
    private String badgeUrl;

    public Skills(String name, String scores, String country, String badgeUrl) {
        this.name = name;
        this.scores = scores;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }
}
