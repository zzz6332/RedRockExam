package com.example.exam.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.com.example.httputilwithhttpurlconnection.image.ImageLoader;
import com.example.exam.R;
import com.example.exam.view.fragment.MainFragment;
import com.example.exam.view.fragment.PlayFragment;
import com.example.exam.view.fragment.PlaySongSmallFragment;
import com.example.exam.viewmodel.HomeViewModel;
import com.example.exam.viewmodel.MusicViewModel;


public class HomeActivity extends AppCompatActivity {
    private TextView tv_top;
    private ImageView iv_flame;
    private ImageView iv_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        HomeViewModel viewModel = new ViewModelProvider(HomeActivity.this).get(HomeViewModel.class);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        final FragmentManager manager = getSupportFragmentManager();
        tv_top = findViewById(R.id.tv_top);
        iv_search = findViewById(R.id.iv_home_right);
        iv_flame = findViewById(R.id.iv_flame);
        final FragmentTransaction transaction = manager.beginTransaction();
        MainFragment fragment = new MainFragment();
        PlaySongSmallFragment playSongSmallFragment = new PlaySongSmallFragment();
        transaction.add(R.id.container_home,fragment,"HomeFragment");
        transaction.add(R.id.home_container_song_small,playSongSmallFragment,"HomeSmallFragment");
        transaction.commit();
        viewModel.inHome.observe(HomeActivity.this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isHome) {
                if (isHome){
                    tv_top.setText("Home");
                    iv_flame.setImageResource(R.drawable.ic_flame_clicked);
                    iv_search.setImageResource(R.drawable.ic_search);
                }
                else {
                    iv_flame.setImageResource(R.drawable.ic_flame);
                    iv_search.setImageResource(R.drawable.ic_no_pic);
                }
            }
        });
        viewModel.isInHome(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
