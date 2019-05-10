package com.example.musicplayer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.musicplayer.R;
import com.example.musicplayer.adapter.BaseAdapter;
import com.example.musicplayer.base.BaseFragment;
import com.example.musicplayer.databinding.FragmentAlbumBinding;
import com.example.musicplayer.model.Album;

public class AlbumFragment extends BaseFragment<FragmentAlbumBinding> implements MediaListener<Album>{

    private BaseAdapter<Album> adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_album;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new BaseAdapter<>(getContext(), R.layout.item_album);
        binding.lvAlbum.setAdapter(adapter);
        adapter.setData(systemData.getAlbums());

        adapter.setListener(this);
    }

    @Override
    public int getTitle() {
        return R.string.album;
    }

    @Override
    public void onItemMediaClick(Album album) {
        app.getService().setArrAlbum(adapter.getData());
        int index = adapter.getData().indexOf(album);
        app.getService().create(index);
    }
}
