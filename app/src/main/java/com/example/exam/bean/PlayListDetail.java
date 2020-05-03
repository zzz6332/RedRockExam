package com.example.exam.bean;

public class PlayListDetail {
    private String song;
    private String songId;
    private String albumId;

    public String getSinger() {
        return singer;
    }

    private String songTime;
    private String singer;
    private int count;
    private String albumName;
    private String pic;

    public String getAlbumName() {
        return albumName;
    }

    public String getPic() {
        return pic;
    }

    public String getSong() {
        return song;
    }

    public String getSongId() {
        return songId;
    }

    public int getCount() {
        return count;
    }

    public int getIsFee() {
        return isFee;
    }

    public String getAlbumId() {
        return albumId;
    }

    public String getSongTime() {
        return songTime;
    }

    public int isFee() {
        return isFee;
    }

    public PlayListDetail(int count,String song, String author, String songId, String albumId,String songTime, int isFee) {
        this.song = song;
        this.count = count;
        this.songId = songId;
        this.albumId = albumId;
        this.songTime = songTime;
        this.isFee = isFee;
    }
    public PlayListDetail(String pic,int count,String song, String author,String albumName,String albumId,String songTime) {
        this.song = song;
        this.pic=pic;
        this.count = count;
        this.singer = author;
        this.albumName= albumName;
        this.albumId = albumId;
        this.songTime = songTime;
    }

    private int isFee;
}
