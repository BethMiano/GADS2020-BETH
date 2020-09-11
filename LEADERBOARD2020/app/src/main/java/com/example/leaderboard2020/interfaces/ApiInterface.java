package com.example.leaderboard2020.interfaces;
import com.example.leaderboard2020.Leaders;
import com.example.leaderboard2020.Skills;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("api/hours")
    Call<List<Leaders>> getLeaders();

    @GET("api/skilliq")
    Call<List<Skills>> getSkills();
}