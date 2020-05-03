package com.example.exam.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.com.example.httputilwithhttpurlconnection.image.ImageLoader;
import com.com.example.httputilwithhttpurlconnection.image.MemoryCache;
import com.example.exam.R;
import com.example.exam.viewmodel.HomeViewModel;
import com.example.exam.viewmodel.MusicViewModel;

public class PlayFragment extends Fragment {
    private ImageView iv_pic;
    private ImageView iv_next;
    private ImageView iv_loop;
    private ImageView iv_ordered;
    private ImageView iv_play;
    private ImageView iv_last;
    private AppCompatSeekBar seek_bar;
    private HomeViewModel homeViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Activity activity = getActivity();
        iv_pic = activity.findViewById(R.id.iv_play_pic);
        iv_last = activity.findViewById(R.id.iv_play_last);
        iv_loop = activity.findViewById(R.id.iv_play_repeat);
        iv_next = activity.findViewById(R.id.iv_play_next);
        iv_ordered = activity.findViewById(R.id.iv_play_oreded);
        iv_play = activity.findViewById(R.id.iv_play_play);
        seek_bar = activity.findViewById(R.id.seek_bar_play);
        final MusicViewModel viewModel = new ViewModelProvider(getActivity()).get(MusicViewModel.class);
        homeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        homeViewModel.isInHome(false);
        homeViewModel.isInList(false);
        homeViewModel.isInPlayPage(true);
        viewModel.song.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                TextView textView = getActivity().findViewById(R.id.tv_top);
                textView.setText(s);
            }
        });
        viewModel.pic_url.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                ImageLoader loader = new ImageLoader(MemoryCache.getInstance(), R.drawable.ic_place_holder, R.drawable.ic_load_fail);
                loader.display(iv_pic, s);
            }
        });
        viewModel.isPlaying.observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isPlaying) {
                if (isPlaying) {
                    viewModel.pauseToPlay();
                    iv_play.setImageResource(R.drawable.ic_play_blue);
                } else {
                    viewModel.pause();
                    iv_play.setImageResource(R.drawable.ic_pause);
                }

            }
        });
        iv_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.stateChange();
            }
        });
        iv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.playNext();
            }
        });
        iv_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.playLast();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

