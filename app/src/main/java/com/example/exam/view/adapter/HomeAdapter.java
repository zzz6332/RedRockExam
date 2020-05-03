package com.example.exam.view.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exam.bean.Album;
import com.example.exam.R;

import com.example.exam.view.myview.HomeBottomRecycler;
import com.example.exam.view.myview.NewSongRecycler;

import java.util.ArrayList;
import java.util.List;


public class HomeAdapter extends RecyclerView.Adapter {
    List<Album> new_songs = new ArrayList<>();
    List<Album> recomm_songs = new ArrayList<>();

    public HomeAdapter(Activity activity, List<Album> newSongList,List<Album> recommList) {
        this.activity = activity;
        new_songs = newSongList;
        recomm_songs = recommList;
    }

    private Activity activity;
    private static final int NEW_SONG = 2;
    private static final int RECOM_SONG = 3;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == NEW_SONG) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_new_song, parent, false);
            NewSongViewHolder holder = new NewSongViewHolder(view);
            return holder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_recommendations, parent, false);
            RecommendationViewHolder holder = new RecommendationViewHolder(view);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NewSongViewHolder) {
            NewSongViewHolder myHolder = (NewSongViewHolder) holder;
            NewSongAdapter adapter = new NewSongAdapter(new_songs,activity);
            LinearLayoutManager manager = new LinearLayoutManager(activity);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            myHolder.recycler.setAdapter(adapter);
            myHolder.recycler.setLayoutManager(manager);
        } else {
            RecommendationViewHolder myHolder = (RecommendationViewHolder) holder;
            RecommendationAdapter adapter = new RecommendationAdapter(recomm_songs,activity);
            LinearLayoutManager manager = new LinearLayoutManager(activity);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            myHolder.recyclerView.setLayoutManager(manager);
            myHolder.recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public static class NewSongViewHolder extends RecyclerView.ViewHolder {
        NewSongRecycler recycler;

        public NewSongViewHolder(@NonNull View itemView) {
            super(itemView);
            recycler = itemView.findViewById(R.id.rv_new_song);
        }
    }

    public static class RecommendationViewHolder extends RecyclerView.ViewHolder {
        HomeBottomRecycler recyclerView;

        public RecommendationViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.rv_recomm);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return NEW_SONG;
        } else return RECOM_SONG;
    }
}
