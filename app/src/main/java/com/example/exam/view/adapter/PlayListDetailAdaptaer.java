package com.example.exam.view.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exam.bean.PlayListDetail;
import com.example.exam.R;
import com.example.exam.viewmodel.HomeViewModel;
import com.example.exam.viewmodel.MusicViewModel;

import java.util.List;

public class PlayListDetailAdaptaer extends RecyclerView.Adapter {
    Activity activity;
    List<PlayListDetail> listDetails;
    public PlayListDetailAdaptaer(List<PlayListDetail> listDetails,Activity activity){
        this.activity = activity;
        this.listDetails = listDetails;
    }
    public static class PlayListViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView tv_count;
        TextView tv_song_name;
        TextView tv_song_time;
        public PlayListViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.view_detail_song);
            tv_count = itemView.findViewById(R.id.tv_count);
            tv_song_name =itemView.findViewById(R.id.tv_detail_song_name);
            tv_song_time = itemView.findViewById(R.id.tv_detail_song_time);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_song_list_detail,parent,false);
        PlayListViewHolder holder = new PlayListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final PlayListViewHolder viewHolder = (PlayListViewHolder)holder;
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeViewModel  homeViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
                MusicViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(MusicViewModel.class);
              //  viewModel.play(listDetails.get(position).getSongId(),listDetails.get(position).getCount());
                viewModel.play(listDetails,position+1);
                // MusicUtil util = new MusicUtil();
                // util.play(listDetails.get(position).getSongId());
                homeViewModel.isInHome(false);
            }
        });
        viewHolder.tv_count.setText(listDetails.get(position).getCount()+"");
        viewHolder.tv_song_name.setText(listDetails.get(position).getSong());
        viewHolder.tv_song_time.setText(listDetails.get(position).getSongTime()+"");
    }

    @Override
    public int getItemCount() {
        return listDetails.size();
    }

}
