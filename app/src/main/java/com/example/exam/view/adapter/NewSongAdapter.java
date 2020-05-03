package com.example.exam.view.adapter;

import android.app.Activity;
import android.app.AlarmManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.com.example.httputilwithhttpurlconnection.image.DefaultCache;
import com.com.example.httputilwithhttpurlconnection.image.DiskCache;
import com.com.example.httputilwithhttpurlconnection.image.ImageLoader;
import com.com.example.httputilwithhttpurlconnection.image.MemoryCache;
import com.example.exam.bean.Album;
import com.example.exam.R;

import java.util.List;

public class NewSongAdapter extends RecyclerView.Adapter{

    private List<Album> mList;
    private Activity activity;
    private ImageLoader loader;

    public NewSongAdapter(List<Album> list,Activity activity) {
        loader = new ImageLoader(new DiskCache(activity),R.drawable.ic_place_holder,R.drawable.ic_load_fail);
        mList = list;
        this.activity = activity;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_new_song,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder myViewholder = (NewSongAdapter.MyViewHolder)holder;
        if (mList==null){
        }
        else {
            Log.d("---","加载图片！");
            loader.display(myViewholder.image,mList.get(position).getPicUrl());
            myViewholder.tv_song_name.setText(mList.get(position).getSongName());
            myViewholder.tv_tracks.setText(mList.get(position).getTracks() + " Tracks");
        }
    }

    @Override
    public int getItemCount() {
        if (mList==null)
        return 5;
        else return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView tv_song_name;
        TextView tv_tracks;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_new_song);
            tv_song_name = itemView.findViewById(R.id.tv_song_name);
            tv_tracks = itemView.findViewById(R.id.tv_tracks);

        }
    }
}
