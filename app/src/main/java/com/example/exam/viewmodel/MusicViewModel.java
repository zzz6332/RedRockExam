package com.example.exam.viewmodel;

import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.com.example.httputilwithhttpurlconnection.threadpool.ThreadPoolUtil;
import com.example.exam.bean.PlayListDetail;
import com.example.exam.model.SongModel;

import java.io.IOException;
import java.io.StringReader;
import java.net.InterfaceAddress;
import java.net.MulticastSocket;
import java.net.URL;
import java.util.List;

public class MusicViewModel extends ViewModel {
    public MutableLiveData<String> pic_url;
    public MutableLiveData<String> mutableLiveData;
    public MutableLiveData<String> song;
    public MutableLiveData<String> singer;
    public MutableLiveData<String> time;
    public MutableLiveData<List<PlayListDetail>> details;
    public MutableLiveData<Integer> position;
    public MutableLiveData<Boolean> haveSong;
    public MutableLiveData<Boolean> isPlaying;
    private SongModel model;
    public MediaPlayer player;
    private boolean flag = true;

    public MusicViewModel() {
        player = new MediaPlayer();
        pic_url = new MutableLiveData<>();
        model = new SongModel(this);
        song = new MutableLiveData<>();
        singer = new MutableLiveData<>();
        time = new MutableLiveData<>();
        haveSong = new MutableLiveData<>();
        haveSong.postValue(false);
        mutableLiveData = new MutableLiveData<>();
        details = new MutableLiveData<>();
        position = new MutableLiveData<>();
        isPlaying = new MutableLiveData<>();
    }
    public void stateChange(){
        if (player.isPlaying()){
            isPlaying.postValue(false);
        }
        else isPlaying.postValue(true);

    }

    private void beginLoop(){
        ThreadPoolUtil util = ThreadPoolUtil.getInstance();
        util.excute(new Runnable() {
            @Override
            public void run() {
                while (flag){
                    try {
                        Thread.sleep(1000);
                        if (player.getDuration() - player.getCurrentPosition() < 1200){
                            playNext();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
    public void play(List<PlayListDetail> details, int count) {
        haveSong.postValue(true);
        this.details.setValue(details);
        this.position.setValue(count-1);
        if (player.isPlaying()) {
            player.pause();
            player.reset();
        }
        model.getSongUrl(details.get(count-1).getSongId(),count);
    }

    public void playNext() {
        if (player.isPlaying()) {
            player.pause();
            player.reset();
        }
        int now_position =position.getValue();
        if (!(now_position + 1 == details.getValue().size()))
            model.getSongUrl(details.getValue().get(now_position + 1).getSongId(), now_position + 2);
             position.postValue(now_position+1);
    }
    public void playLast(){
        if (player.isPlaying()){
            player.pause();
            player.reset();
        }
        int now_position = position.getValue();
        if (!(now_position==0)){
            model.getSongUrl(details.getValue().get(now_position - 1).getSongId(),now_position - 2);
            position.setValue(now_position-1);
        }
        else {
            int size = details.getValue().size();
            model.getSongUrl(details.getValue().get(size-1).getSongId(),size);
            position.setValue(size-1);
        }
    }

    public void start() {
        if (!player.isPlaying()) {
            player.start();
        }
    }

    public void pause() {
        if (player.isPlaying()) {
            player.pause();
        }
    }

    public void stop() {
        if (player.isPlaying()) {
            player.stop();
        }
    }

    public void onUrlBack(String url, String id, int count) {
        model.getSongDetail(id, count);
        if (url == null) {
            Log.d("---", "url为空");
            return;
        }
        try {
            player.setDataSource(url);
            player.prepare();
            player.start();
            beginLoop();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    player.start();
                    beginLoop();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onSongDetailBack(PlayListDetail detail) {
        pic_url.postValue(detail.getPic());
        song.postValue(detail.getSong());
        singer.postValue(detail.getSinger() + " - " + detail.getAlbumName());
        time.postValue(detail.getSongTime());
    }

    public void onSongUrlNull() {
        song.postValue("付费歌曲，无法播放");
    }

    public boolean isPlaying() {
        return player.isPlaying();
    }

    public boolean isLooping() {
        return player.isLooping();
    }

    public void pauseToPlay() {
        if (!player.isPlaying()) {
            player.start();
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        player.stop();
        flag = false;
        player.release();
    }
}
