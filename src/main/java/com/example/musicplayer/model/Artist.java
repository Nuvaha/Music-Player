package com.example.musicplayer.model;

import android.provider.MediaStore;

public class Artist extends MP3Media {
    @FieldInfo(fieldName = MediaStore.Audio.Artists.ARTIST)
    private String artist;
    @FieldInfo(fieldName =  MediaStore.Audio.Artists.NUMBER_OF_ALBUMS)
    private String numberAlbum;
    @FieldInfo(fieldName =  MediaStore.Audio.Artists.NUMBER_OF_TRACKS)
    private String numberSong;

    public String getArtist() {
        return artist;
    }

    public String getNumberAlbum() {
        return numberAlbum;
    }

    public String getNumberSong() {
        return numberSong;
    }
}
