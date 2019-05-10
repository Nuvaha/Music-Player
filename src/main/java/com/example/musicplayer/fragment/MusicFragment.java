package com.example.musicplayer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.musicplayer.R;
import com.example.musicplayer.adapter.BaseAdapter;
import com.example.musicplayer.base.BaseFragment;
import com.example.musicplayer.databinding.FragmentMusicBinding;
import com.example.musicplayer.model.Music;

import java.util.List;

public class MusicFragment extends BaseFragment<FragmentMusicBinding> implements MediaListener<Music>{

    private BaseAdapter<Music> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_music;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new BaseAdapter<>(getContext(), R.layout.item_music);
        binding.lvSong.setAdapter(adapter);
        adapter.setData(systemData.getSongs());

        adapter.setListener(this);
    }

    @Override
    public int getTitle() {
        return R.string.music;
    }


    @Override
    public void onItemMediaClick(Music music) {
        app.getService().setArrMusic(adapter.getData());
        int index = adapter.getData().indexOf(music);//nếu không có trả về -1
        app.getService().create(index);
    }
}
