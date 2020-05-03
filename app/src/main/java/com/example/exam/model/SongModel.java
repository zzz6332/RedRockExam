package com.example.exam.model;

import android.util.Log;
import android.widget.LinearLayout;

import androidx.lifecycle.ViewModel;

import com.com.example.httputilwithhttpurlconnection.httpurlconnection.HttpCallBackListener;
import com.com.example.httputilwithhttpurlconnection.httpurlconnection.HttpUtil;
import com.example.exam.bean.Album;
import com.example.exam.bean.PlayListDetail;
import com.example.exam.viewmodel.HomeViewModel;
import com.example.exam.viewmodel.MusicViewModel;

import java.util.ArrayList;
import java.util.List;

public class SongModel {
    HomeViewModel viewModel;
    MusicViewModel musicViewModel;
    private List<PlayListDetail> playLists = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    private List<Integer> fees = new ArrayList<>();
    private List<String> dts= new ArrayList<>();
    private List<String> ids = new ArrayList<>();
    private List<String> pics = new ArrayList<>();
    private final String songListDetailUrl = "http://47.99.165.194/playlist/detail";
    private final String songIdUrl = "http://47.99.165.194/song/url";
    private final String songDetailUrl = "http://47.99.165.194/song/detail";
    public SongModel(HomeViewModel viewModel) {
        this.viewModel = viewModel;
    }
    public SongModel(MusicViewModel viewModel) {
        this.musicViewModel = viewModel;
    }
    public void getSongUrl(final String id, final int position){
        List<String> list = new ArrayList<>();
        list.add("url");
        HttpUtil.sendHttpWithUrlConnection(songIdUrl + "?id=" + id, list, new HttpCallBackListener() {
            @Override
            public void OnFinish(List<String> list) {
                String url = list.get(0).substring(8,list.get(0).length()-1);
                if (!url.equals("null")){
                    musicViewModel.onUrlBack(list.get(0).substring(8,list.get(0).length()-1),id,position);
                }
                else {
                 musicViewModel.onSongUrlNull();
                }
            }

            @Override
            public void OnError() {
             int i ;
            }
        });

    }
    public void getSongDetail(String id, final int position){
        names.clear();
        pics.clear();
        ids.clear();
        List<String> list = new ArrayList<>();
        list.add("picUrl");
        list.add("name");

        list.add("id");
        list.add("dt");
        HttpUtil.sendHttpWithUrlConnection(songDetailUrl + "?ids=" + id, list, new HttpCallBackListener() {
            @Override
            public void OnFinish(List<String> list) {
                while (list.get(0).contains("picUrl")){
                    String result = "";
                    for (int i = 0; i < list.get(0).length() ; i++) {
                        if (list.get(0).charAt(i) !='\\' && list.get(0).charAt(i) != ' '){
                            result = result + list.get(0).charAt(i);
                        }
                    }
                pics.add(result);
                list.remove(0);
            }
                while (list.get(0).contains("name")){
                    names.add(list.get(0));
                    list.remove(0);
                }

                while (list.get(0).contains("id")){
                    ids.add(list.get(0));
                    list.remove(0);
                }
                while (list.get(0).contains("dt")){
                    int minute = Integer.parseInt(list.get(0).substring(7,list.get(0).length()-1))/60;
                    int second = Integer.parseInt(list.get(0).substring(7,list.get(0).length()-1))%60;
                    dts.add(minute+":"+second);
                    list.remove(0);
                    if (list.size()==0) break;
                }
               PlayListDetail detail = new PlayListDetail(pics.get(0).substring(10,pics.get(0).length()-1),position,names.get(0).substring(7,names.get(0).length()-1),names.get(1).substring(7,names.get(1).length()-1),names.get(2).substring(7,names.get(2).length()-1),ids.get(2).substring(6,ids.get(2).length()-1),dts.get(0));
                musicViewModel.onSongDetailBack(detail);
            }

            @Override
            public void OnError() {

            }
        });
    }


    public void getSongListDetail(String id, final Album album){
        List<String> list = new ArrayList<>();
        list.add("name");
        list.add("fee");
        list.add("id");
        list.add("dt");
        HttpUtil.sendHttpWithUrlConnection(songListDetailUrl + "?id=" + id, list, new HttpCallBackListener() {
            @Override
            public void OnFinish(List<String> list) {
                while (list.get(0).contains("name")){
                    names.add(list.get(0));
                    list.remove(0);
                }
                while (list.get(0).contains("fee")){
                    fees.add(Integer.parseInt(list.get(0).substring(8,list.get(0).length()-1)));
                    list.remove(0);
                }
                while (list.get(0).contains("id")){
                    ids.add(list.get(0));
                    list.remove(0);
                }
                while (list.get(0).contains("dt")){
                    int minute = Integer.parseInt(list.get(0).substring(7,list.get(0).length()-1))/1000/60;
                    int second = Integer.parseInt(list.get(0).substring(7,list.get(0).length()-1))%60;
                    String real_m = minute+"";
                    String real_s = second+"";
                    if (minute<10){
                        real_m= "0"+minute;
                    }
                    if (second<10){
                        real_s= "0"+second;
                    }
                    dts.add(real_m+":"+real_s);
                    list.remove(0);
                    if (list.size()==0) break;
                }
                for (int i = 0; i < dts.size(); i++) {
                    String song_name = names.get(3*i).substring(8,names.get(3*i).length()-1);
                    PlayListDetail detail = new PlayListDetail(i+1,names.get(3*i).substring(9,names.get(3*i).length()-1),names.get(3*(i+1)-1).substring(8,names.get(3*(i+1)-1).length()-1),ids.get(i*3).substring(7,ids.get(i*3).length()-1),ids.get(2+3*i).substring(7,ids.get(2+3*i).length()-1),dts.get(i),fees.get(i*2));
                    playLists.add(detail);
                }
                viewModel.onPlayListDataBack(playLists,album);
                playLists.clear();
                names.clear();
                fees.clear();
                ids.clear();
                dts.clear();
            }

            @Override
            public void OnError() {

            }
        });
    }
}
