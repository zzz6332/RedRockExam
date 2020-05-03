package com.example.exam.bean;

public class Album {
      String picUrl;
      String songName;
      String tracks;
      String songListId;
      public String getPicUrl() {
            return picUrl;
      }

      public String getSongName() {
            return songName;
      }

      public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
      }

      public void setSongName(String songName) {
            this.songName = songName;
      }

      public void setAuthorName(String authorName) {
            this.authorName = authorName;
      }

      public String getTracks() {
            return tracks;
      }

      public String getSongListId() {
            return songListId;
      }

      public Album(String picUrl, String songName, String authorName, String tracks) {
            this.picUrl = picUrl;
            this.songName = songName;
            this.authorName = authorName;
            this.tracks = tracks;
      }
      public Album(String picUrl, String songName, String authorName, String tracks, String songListId) {
            this.picUrl = picUrl;
            this.songName = songName;
            this.authorName = authorName;
            this.tracks = tracks;
            this.songListId = songListId;
      }

      public String getAuthorName() {
            return authorName;
      }

      String authorName;
}
