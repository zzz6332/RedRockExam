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

import com.example.exam.R;
import com.example.exam.view.activity.HomeActivity;
import com.example.exam.viewmodel.HomeViewModel;
import com.example.exam.viewmodel.MusicViewModel;

public class PlaySongSmallFragment extends Fragment {
    ImageView iv_play;
    ImageView iv_dots;
    TextView tv_song;
    TextView tv_singer;
    MusicViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_song_small,container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Activity activity = getActivity();
        iv_play = activity.findViewById(R.id.iv_small_play);
        iv_dots = activity.findViewById(R.id.iv_small_dots);
        tv_song = activity.findViewById(R.id.tv_play_song_small_song_name);
        tv_singer = activity.findViewById(R.id.tv_play_song_small_singer);
        viewModel = new ViewModelProvider(getActivity()).get(MusicViewModel.class);
        viewModel.song.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                      tv_song.setText(s);
            }
        });
        viewModel.singer.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tv_singer.setText(s);
            }
        });
        iv_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               viewModel.stateChange();
            }
        });
        viewModel.isPlaying.observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isPlaying) {
                if (isPlaying){
                    iv_play.setImageResource(R.drawable.ic_play_blue);
                    viewModel.pauseToPlay();

                }
                else {
                     iv_play.setImageResource(R.drawable.ic_pause);
                    viewModel.pause();
                }
            }
        });
        activity.findViewById(R.id.iv_small_dots).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicViewModel viewModel = new ViewModelProvider(getActivity()).get(MusicViewModel.class);
                if (viewModel.haveSong.getValue()){
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    activity.findViewById(R.id.home_container_song_small).setVisibility(View.GONE);
                    PlayFragment playFragment = new PlayFragment();
                    HomeViewModel homeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
                    homeViewModel.isInHome(false);
                    transaction.add(R.id.container_home,playFragment,"PlayFragment");
                    transaction.addToBackStack("PlayFragment");
                    Fragment fragment = manager.findFragmentByTag("PlayListDetailFragment");
                    transaction.hide(fragment);
                    transaction.commit();
                }
            }
        });
    }
}
