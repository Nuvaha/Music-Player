package com.example.musicplayer.model;


import android.provider.MediaStore;

public class Music extends MP3Media{
    @FieldInfo(fieldName = MediaStore.Audio.AudioColumns.TITLE)
     private String title;
    @FieldInfo(fieldName = MediaStore.Audio.AudioColumns.DATA)
     private String data;
    @FieldInfo(fieldName = MediaStore.Audio.AudioColumns.DURATION)
     private long duration;
    @FieldInfo(fieldName = MediaStore.Audio.AudioColumns.ARTIST)
    private String artist;
    @FieldInfo(fieldName = MediaStore.Audio.AudioColumns.ALBUM)
    private String album;

    public String getTitle() {
        return title;
    }

    public String getData() {
        return data;
    }

    public long getDuration() {
        return duration;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }
}
