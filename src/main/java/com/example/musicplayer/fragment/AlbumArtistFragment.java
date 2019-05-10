package com.example.musicplayer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.musicplayer.R;
import com.example.musicplayer.adapter.BaseAdapter;
import com.example.musicplayer.base.BaseFragment;
import com.example.musicplayer.databinding.FragmentAlbumBinding;
import com.example.musicplayer.model.Music;

public class AlbumArtistFragment extends BaseFragment<FragmentAlbumBinding> {

    private BaseAdapter<Music> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.album_artist;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new BaseAdapter<>(getContext(), R.layout.item_album_artist);
    }

    @Override
    public int getTitle() {
        return 0;
    }
}
