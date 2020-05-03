package com.example.exam.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.com.example.httputilwithhttpurlconnection.threadpool.ThreadPoolUtil;
import com.example.exam.bean.Album;

import com.example.exam.bean.PlayListDetail;
import com.example.exam.model.HomeModel;
import com.example.exam.model.SongModel;

import java.util.ArrayList;
import java.util.List;


public class HomeViewModel extends ViewModel {
    private ThreadPoolUtil threadPool;
    public MutableLiveData<Integer> image = new MutableLiveData<>();
    public MutableLiveData<List<Album>> newSongs = new MutableLiveData<>();
    public MutableLiveData<List<Album>> recommSongs = new MutableLiveData<>();
    public MutableLiveData<Album> albumMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> playListId = new MutableLiveData<>();
    public MutableLiveData<List<PlayListDetail>> playListDetailMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> inHome;
    public MutableLiveData<Boolean> inPlayPage;
    public MutableLiveData<Boolean> inList;
    private HomeModel model;
    private SongModel songModel;

    public HomeViewModel() {
        model = new HomeModel(this);
        songModel = new SongModel(this);
        inList = new MutableLiveData<>();
        inHome = new MutableLiveData<>();
        inPlayPage = new MutableLiveData<>();    }
    public void isInHome(Boolean b){
        inHome.setValue(b);
    }
    public void isInPlayPage(Boolean b){
        inPlayPage.setValue(b);
    }
    public void getNewSong() {
        model.getSongData();
    }

    public void getRecomm() {
        model.getRecomm();
    }

    public void getPlayListDetail(final String id, final Album album) {
        playListId.setValue(id);
        songModel.getSongListDetail(id,album);
    }
    public void isInList(Boolean b){
        inList.setValue(b);
    }
    public void onNewSongsDataBack(List<Album> list) {
        newSongs.postValue(list);
        model.getRecomm();
    }

    public void onRecommDataBack(List<Album> list) {
        recommSongs.postValue(list);
    }

    public void onPlayListDataBack(List<PlayListDetail> listDetails,Album album) {
        List<PlayListDetail> list = new ArrayList<>();
        list.addAll(listDetails);
        playListDetailMutableLiveData.postValue(list);
        albumMutableLiveData.postValue(album);
    }

    public void onPlayListDetailClicked() {

    }
}
