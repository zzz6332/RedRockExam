package com.example.exam.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.exam.bean.Album;
import com.example.exam.R;
;
import com.example.exam.view.adapter.HomeAdapter;
import com.example.exam.view.myview.HomeRecycler;
import com.example.exam.viewmodel.HomeViewModel;

import java.util.List;

public class MainFragment extends Fragment {
    private HomeViewModel viewModel;
    private HomeRecycler recycler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recycler = getActivity().findViewById(R.id.rv_home);
        viewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        final HomeAdapter adapter = new HomeAdapter(getActivity(),null,null);
        recycler.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(manager);
        viewModel.newSongs.observe(getActivity(), new Observer<List<Album>>() {
            @Override
            public void onChanged(List<Album> albums) {
                HomeAdapter adapter = new HomeAdapter(getActivity(), albums,viewModel.recommSongs.getValue());
                recycler.setAdapter(adapter);
            }
        });
        viewModel.recommSongs.observe(getActivity(), new Observer<List<Album>>() {
            @Override
            public void onChanged(List<Album> list) {
                Log.d("---","更新了");
                HomeAdapter adapter1 = new HomeAdapter(getActivity(),viewModel.newSongs.getValue(),list);
                recycler.setAdapter(adapter1);
            }
        });
        viewModel.playListId.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                SongListDetailFragment fragment = new SongListDetailFragment();
                transaction.add(R.id.container_home,fragment,"PlayListDetailFragment");
                transaction.hide(fragmentManager.findFragmentByTag("HomeFragment"));
                transaction.addToBackStack("songList");
                transaction.commit();
            }
        });
        viewModel.getNewSong();

    }
}
