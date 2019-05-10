package com.example.musicplayer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.musicplayer.R;
import com.example.musicplayer.adapter.BaseAdapter;
import com.example.musicplayer.base.BaseFragment;
import com.example.musicplayer.dao.SystemData;
import com.example.musicplayer.databinding.FragmentArtistBinding;
import com.example.musicplayer.model.Artist;

public class ArtistFragment extends BaseFragment<FragmentArtistBinding> implements MediaListener<Artist> {

    private BaseAdapter<Artist> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_artist;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new BaseAdapter<>(getContext(), R.layout.item_artist);
        binding.lvArtist.setAdapter(adapter);
        adapter.setData(systemData.getArtist());
    }

    @Override
    public int getTitle() {
        return R.string.artist;
    }

    @Override
    public void onItemMediaClick(Artist artist) {

    }
}
