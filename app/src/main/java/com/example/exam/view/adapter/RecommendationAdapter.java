package com.example.exam.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.com.example.httputilwithhttpurlconnection.image.DefaultCache;
import com.com.example.httputilwithhttpurlconnection.image.DiskCache;
import com.com.example.httputilwithhttpurlconnection.image.ImageLoader;
import com.com.example.httputilwithhttpurlconnection.image.MemoryCache;
import com.com.example.httputilwithhttpurlconnection.threadpool.ThreadPoolUtil;
import com.example.exam.bean.Album;
import com.example.exam.R;
import com.example.exam.viewmodel.HomeViewModel;

import java.util.List;

public class RecommendationAdapter extends RecyclerView.Adapter {

    private List<Album> mList;
    private ImageLoader loader;
    private Activity activity;

    public RecommendationAdapter(List<Album> list, Activity activity) {
        this.activity = activity;
        mList = list;
        loader = new ImageLoader(MemoryCache.getInstance(), R.drawable.ic_place_holder, R.drawable.ic_load_fail);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_recomm, parent, false);
        RecommViewHolder holder = new RecommViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final RecommViewHolder myHolder = (RecommViewHolder) holder;
        myHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("------", position + "");
            }
        });
        if (mList != null) {
            if (position == 0) {
                loader.display(myHolder.iv_cover, mList.get(0).getPicUrl());
                loader.display(myHolder.iv_cover2, mList.get(1).getPicUrl());
                if (mList.get(0).getSongName().length() > 12)
                    myHolder.name.setText(mList.get(0).getSongName().substring(0, 11) +"...");
                else
                    myHolder.name.setText(mList.get(0).getSongName());

                if (mList.get(1).getSongName().length() > 12)
                    myHolder.name2.setText(mList.get(1).getSongName().substring(0, 11) +"...");
                else
                    myHolder.name2.setText(mList.get(1).getSongName());

                if (mList.get(0).getAuthorName().length() > 12)
                    myHolder.author.setText(mList.get(0).getAuthorName().substring(0, 11) +"...");
                else
                    myHolder.author.setText(mList.get(0).getAuthorName());

                if (mList.get(1).getAuthorName().length() > 12)
                    myHolder.author2.setText(mList.get(1).getAuthorName().substring(0, 11) +"...");
                else
                    myHolder.author2.setText(mList.get(1).getAuthorName());
            } else if (position == 1) {
                loader.display(myHolder.iv_cover, mList.get(2).getPicUrl());
                loader.display(myHolder.iv_cover2, mList.get(3).getPicUrl());
                if (mList.get(2).getSongName().length() > 12)
                    myHolder.name.setText(mList.get(2).getSongName().substring(0,11) +"...");
                else
                    myHolder.name.setText(mList.get(2).getSongName());

                if (mList.get(3).getSongName().length() > 12)
                    myHolder.name2.setText(mList.get(3).getSongName().substring(0, 11) +"...");
                else
                    myHolder.name2.setText(mList.get(3).getSongName());

                if (mList.get(2).getAuthorName().length() > 12)
                    myHolder.author.setText(mList.get(2).getAuthorName().substring(0, 11) +"...");
                else
                    myHolder.author.setText(mList.get(2).getAuthorName());

                if (mList.get(3).getAuthorName().length() > 12)
                    myHolder.author2.setText(mList.get(3).getAuthorName().substring(0, 11) +"...");
                else
                    myHolder.author2.setText(mList.get(3).getAuthorName());
            } else {
                loader.display(myHolder.iv_cover, mList.get(position * 2).getPicUrl());
                loader.display(myHolder.iv_cover2, mList.get(position * 2 + 1).getPicUrl());
                if (mList.get(position*2).getSongName().length() > 12)
                    myHolder.name.setText(mList.get(position*2).getSongName().substring(0,11) +"...");
                else
                    myHolder.name.setText(mList.get(position*2).getSongName());

                if (mList.get(position*2+1).getSongName().length() > 12)
                    myHolder.name2.setText(mList.get(position*2+1).getSongName().substring(0,11) +"...");
                else
                    myHolder.name2.setText(mList.get(position*2+1).getSongName());

                if (mList.get(position*2).getAuthorName().length() > 12)
                    myHolder.author.setText(mList.get(position*2).getAuthorName().substring(0,11)+"...");
                else
                    myHolder.author.setText(mList.get(position*2).getAuthorName());

                if (mList.get(position*2+1).getAuthorName().length() > 12)
                    myHolder.author2.setText(mList.get(position*2+1).getAuthorName().substring(0, 11) +"...");
                else
                    myHolder.author2.setText(mList.get(position*2+1).getAuthorName());
            }
            myHolder.iv_cover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final HomeViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
                    if (position == 0) {
                        viewModel.getPlayListDetail(mList.get(0).getSongListId(), mList.get(0));
                    } else if (position == 1) {
                        viewModel.getPlayListDetail(mList.get(2).getSongListId(), mList.get(2));
                    } else if (position > 1) {
                        viewModel.getPlayListDetail(mList.get(position * 2).getSongListId(), mList.get(position * 2));
                    }
                    viewModel.isInHome(false);
                    viewModel.isInList(true);
                }
            });
            myHolder.iv_cover2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final HomeViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HomeViewModel.class);
                    if (position == 0) {
                        viewModel.getPlayListDetail(mList.get(1).getSongListId(), mList.get(1));
                    } else if (position == 1) {
                        viewModel.getPlayListDetail(mList.get(3).getSongListId(), mList.get(3));
                    } else if (position > 1) {
                        viewModel.getPlayListDetail(mList.get(position * 2 + 1).getSongListId(), mList.get(position * 2 + 1));
                    }
                    viewModel.isInHome(false);
                    viewModel.isInList(true);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 3;
        else return mList.size() / 2;

    }

    public static class RecommViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView iv_cover;
        ImageView iv_cover2;
        TextView name;
        TextView name2;
        TextView author;
        TextView author2;

        public RecommViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.view_recomm_item);
            iv_cover = itemView.findViewById(R.id.iv_recomm_pic);
            iv_cover2 = itemView.findViewById(R.id.iv_recomm_pic_2);
            name = itemView.findViewById(R.id.tv_recomm_song_name);
            name2 = itemView.findViewById(R.id.tv_recomm_song_name_2);
            author = itemView.findViewById(R.id.tv_recomm_author);
            author2 = itemView.findViewById(R.id.tv_recomm_author_2);
        }
    }

}
