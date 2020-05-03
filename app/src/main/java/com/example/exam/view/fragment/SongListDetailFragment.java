package com.example.exam.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.com.example.httputilwithhttpurlconnection.image.ImageLoader;
import com.com.example.httputilwithhttpurlconnection.image.MemoryCache;
import com.example.exam.bean.Album;
import com.example.exam.bean.PlayListDetail;
import com.example.exam.R;
import com.example.exam.view.adapter.PlayListDetailAdaptaer;
import com.example.exam.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class SongListDetailFragment extends Fragment {
    private RecyclerView rv;
    private ImageView iv;
    private TextView tv_list_name;
    private TextView tv_author;
    private TextView tv_count;
    HomeViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_song_list_detail,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Activity activity = getActivity();
        viewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        rv = activity.findViewById(R.id.rv_play_list_detail);
        iv = activity.findViewById(R.id.iv_detail_pic);
        tv_list_name = activity.findViewById(R.id.tv_detail_name);
        tv_author = activity.findViewById(R.id.tv_detail_author);
        tv_count = activity.findViewById(R.id.tv_detail_tracks);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        PlaySongSmallFragment fragment = new PlaySongSmallFragment();
        transaction.add(R.id.container_playlist_small,fragment,"PlayListFragment");
        transaction.commit();
        viewModel.albumMutableLiveData.observe(getActivity(), new Observer<Album>() {
            @Override
            public void onChanged(Album album) {
                ImageLoader loader = new ImageLoader(MemoryCache.getInstance(),R.drawable.ic_place_holder,R.drawable.ic_load_fail);
                loader.display(iv,album.getPicUrl());
                tv_list_name.setText(album.getSongName());
                tv_author.setText(album.getAuthorName());
                tv_count.setText(album.getTracks() + " tracks");
            }
        });
        viewModel.playListDetailMutableLiveData.observe(getActivity(), new Observer<List<PlayListDetail>>() {
            @Override
            public void onChanged(List<PlayListDetail> listDetails) {
                //数据改变时设置适配器
                PlayListDetailAdaptaer adaptaer = new PlayListDetailAdaptaer(listDetails,getActivity());
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                rv.setAdapter(adaptaer);
                rv.setLayoutManager(manager);
            }
        });
        viewModel.inList.observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    TextView tv_top = activity.findViewById(R.id.tv_top);
                    tv_top.setText("PlayList");
                }
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.isInList(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.isInHome(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.isInList(true);
    }
}
