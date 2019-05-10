package com.example.musicplayer.model;

import android.provider.MediaStore;

public class Album extends MP3Media {
    @FieldInfo(fieldName = MediaStore.Audio.Albums.ALBUM)
    private String name;
    @FieldInfo(fieldName = MediaStore.Audio.Albums.ARTIST)
    private String artist;
    @FieldInfo(fieldName = MediaStore.Audio.Albums.ALBUM_ART)
    private String image;
    @FieldInfo(fieldName = MediaStore.Audio.Albums.NUMBER_OF_SONGS)
    private String numberSong;

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getImage() {
        return image;
    }

    public String getNumberSong() {
        return numberSong;
    }
}
