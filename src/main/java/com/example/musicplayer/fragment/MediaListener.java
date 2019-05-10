package com.example.musicplayer.fragment;

import com.example.musicplayer.adapter.BaseAdapter;
import com.example.musicplayer.model.MP3Media;

public interface MediaListener<T extends MP3Media> extends BaseAdapter.ListItemListener {
    void onItemMediaClick(T t);
}
