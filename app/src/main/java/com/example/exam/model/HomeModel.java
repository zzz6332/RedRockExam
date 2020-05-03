package com.example.exam.model;

import android.app.Activity;
import android.util.Log;

import com.example.exam.bean.Album;
import com.com.example.httputilwithhttpurlconnection.httpurlconnection.HttpCallBackListener;
import com.com.example.httputilwithhttpurlconnection.httpurlconnection.HttpUtil;
import com.example.exam.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeModel {
    final List<Album> newAlbums = new ArrayList<>();
    final List<Album> recommAlbums = new ArrayList<>();
    List<String> picUrls = new ArrayList<>();
    List<String> names = new ArrayList<>();
    List<String> sizes = new ArrayList<>();
    List<String> coverImgUrls = new ArrayList<>();
    List<String> recommNames = new ArrayList<>();
    List<String> nickNames = new ArrayList<>();
    List<String> trackCounts = new ArrayList<>();
    List<String> ids = new ArrayList<>();
    private final HomeViewModel viewModel;
    private final String newSongUrl = "http://47.99.165.194/album/newest";
    private final String recommUrl = "http://47.99.165.194/top/playlist?cap=华语&limit=14";


    public HomeModel(HomeViewModel viewModel){
        this.viewModel = viewModel;
    }
    public void getSongData(){
        List<String> list = new ArrayList<>();
        list.add("picUrl");
        list.add("name");
        list.add("size");
        HttpUtil.sendHttpWithUrlConnection(newSongUrl, list, new HttpCallBackListener() {
            @Override
            public void OnFinish(List<String> list) {
                while (list.get(0).contains("picUrl")){
                    picUrls.add(list.get(0));
                    list.remove(0);
                    Log.d("---","1");

                }
                while (list.get(0).contains("name")){
                    names.add(list.get(0));
                    list.remove(0);
                    Log.d("---","2");

                }
                while (list.get(0).contains("size")){
                    sizes.add(list.get(0));
                    list.remove(0);
                    Log.d("---","3");

                    if (list.size()==0)break;
                }
                for (int i = 0; i < 6; i++) {
                    Log.d("---",picUrls.get(i));
                    Album albums = new Album(picUrls.get(i*3).substring(9),names.get(i*3).substring(7,names.get(i*3).length()-1),names.get(i*3+1).substring(7,names.get(i*3+1).length()-1),sizes.get(i).substring(7,sizes.get(i).length()-1));
                    newAlbums.add(albums);

                }
                viewModel.onNewSongsDataBack(newAlbums);
            }
                @Override
            public void OnError() {
            }
        });
            }
            public void getRecomm(){
             List<String> need_list = new ArrayList<>();
             need_list.add("name");
             need_list.add("nick");
             need_list.add("coverImgUrl");
             need_list.add("id");
             need_list.add("trackCount");
             HttpUtil.sendHttpWithUrlConnection(recommUrl, need_list, new HttpCallBackListener() {
                 @Override
                 public void OnFinish(List<String> list) {
                     while (list.get(0).contains("name")){
                         recommNames.add(list.get(0));
                         list.remove(0);
                     }
                     while (list.get(0).contains("nick")){
                         nickNames.add(list.get(0));
                         list.remove(0);
                     }
                     while (list.get(0).contains("coverImgUrl")){
                         coverImgUrls.add(list.get(0));
                         list.remove(0);
                     }
                     while (list.get(0).contains("id")){
                         ids.add(list.get(0));
                         list.remove(0);
                     }
                     while (list.get(0).contains("trackCount")){
                         trackCounts.add(list.get(0));
                         list.remove(0);
                         if (list.size()==0)break;
                     }
                     for (int i = 0; i < 14; i++) {
                         Album album = new Album(coverImgUrls.get(i).substring(16),recommNames.get(i).substring(9,recommNames.get(i).length()-1),nickNames.get(i).substring(9,nickNames.get(i).length()-1),trackCounts.get(i).substring(15,trackCounts.get(i).length()-1),ids.get(i).substring(7,ids.get(i).length()-1));
                         recommAlbums.add(album);
                     }
                     viewModel.onRecommDataBack(recommAlbums);
                 }

                 @Override
                 public void OnError() {

                 }
             });
            }
    }

