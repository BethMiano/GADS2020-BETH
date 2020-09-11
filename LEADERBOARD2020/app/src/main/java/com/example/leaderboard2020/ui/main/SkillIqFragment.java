package com.example.leaderboard2020.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leaderboard2020.retrofit_api.ApiClient;
import com.example.leaderboard2020.interfaces.ApiInterface;
import com.example.leaderboard2020.R;
import com.example.leaderboard2020.adapters.RecyclerAdapterSkills;
import com.example.leaderboard2020.Skills;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillIqFragment extends Fragment {
    List<Skills> LeadersList;
    RecyclerView recyclerView;
    RecyclerAdapterSkills recyclerAdapter;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.skill_iq_fragment, container, false);

        LeadersList = new ArrayList<>();
        recyclerView = (RecyclerView)root.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapterSkills(getActivity(),LeadersList);
        recyclerView.setAdapter(recyclerAdapter);


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Skills>> call = apiService.getSkills();

        call.enqueue(new Callback<List<Skills>>() {
            @Override
            public void onResponse(Call<List<Skills>> call, Response<List<Skills>> response) {
                LeadersList = response.body();
                Log.d("TAG","Response = "+LeadersList);
                recyclerAdapter.setLeadersList(LeadersList);
            }

            @Override
            public void onFailure(Call<List<Skills>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

        return root;
    }
}
