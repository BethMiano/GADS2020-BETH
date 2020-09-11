package com.example.leaderboard2020.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.leaderboard2020.R;
import com.example.leaderboard2020.Skills;

import java.util.List;

//Adapter for the Skill IQ leaders
public class RecyclerAdapterSkills extends RecyclerView.Adapter<RecyclerAdapterSkills.MyviewHolder> {

    Context context;
    List<Skills> leadersList;

    public RecyclerAdapterSkills(Context context, List<Skills> leadersList) {
        this.context = context;
        this.leadersList = leadersList;
    }

    public void setLeadersList(List<Skills> leadersList) {
        this.leadersList = leadersList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerAdapterSkills.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_adapter_skills,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterSkills.MyviewHolder holder, int position) {
        holder.tvname.setText(leadersList.get(position).getName().toString());
        holder.tvscores.setText(leadersList.get(position).getScores().toString()+" Skill IQ Score,");
        holder.tvcountry.setText(leadersList.get(position).getCountry().toString());
        Glide.with(context).load(leadersList.get(position).getBadgeUrl()).apply(RequestOptions.centerCropTransform()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(leadersList != null){
            return leadersList.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView tvname,tvscores, tvcountry;
        ImageView imageView;

        public MyviewHolder(View itemView) {
            super(itemView);
            tvname = (TextView)itemView.findViewById(R.id.tvname);
            tvscores = (TextView)itemView.findViewById(R.id.tvscores);
            tvcountry = (TextView)itemView.findViewById(R.id.tvcountry);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
        }
    }
}