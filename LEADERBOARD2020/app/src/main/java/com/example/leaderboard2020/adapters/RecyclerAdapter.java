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
import com.example.leaderboard2020.Leaders;
import com.example.leaderboard2020.R;

import java.util.List;
//Adapter for the learning leaders
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyviewHolder> {

    Context context;
    List<Leaders> leadersList;

    public RecyclerAdapter(Context context, List<Leaders> leadersList) {
        this.context = context;
        this.leadersList = leadersList;
    }

    public void setMovieList(List<Leaders> leadersList) {
        this.leadersList = leadersList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_adapter,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.MyviewHolder holder, int position) {
        holder.tvname.setText(leadersList.get(position).getName().toString());
        holder.tvhours.setText(leadersList.get(position).getHours().toString()+" Learning Hours,");
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
        TextView tvname,tvhours, tvcountry;
        ImageView imageView;

        public MyviewHolder(View itemView) {
            super(itemView);
            tvname = (TextView)itemView.findViewById(R.id.tvname);
            tvhours = (TextView)itemView.findViewById(R.id.tvhours);
            tvcountry = (TextView)itemView.findViewById(R.id.tvcountry);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
        }
    }
}