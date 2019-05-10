package com.example.musicplayer.view;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.example.musicplayer.App;
import com.example.musicplayer.activities.MainActivity;
import com.example.musicplayer.databinding.UiPlayViewBinding;

public class MP3PlayView extends FrameLayout {
    private UiPlayViewBinding binding;
    private App app;

    public MP3PlayView(Context context) {
        super(context);
        init();
    }

    public MP3PlayView( Context context,  AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MP3PlayView( Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MP3PlayView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        binding = UiPlayViewBinding.inflate(LayoutInflater.from(getContext()));
        addView(binding.getRoot());
        app = (App) getContext().getApplicationContext();
        setVisibility(GONE);
    }

    public void registerState(){

        MainActivity act = (MainActivity) getContext();
        app.getService().getIsLife().observe(act, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean){
                    setVisibility(VISIBLE);
                }else {
                    setVisibility(GONE);
                }
            }
        });

        app.getService().getName().observe(act, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                binding.setName(s);
            }
        });

        app.getService().getArtists().observe(act, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                binding.setArtists(s);
            }
        });

        app.getService().getIsPlaying().observe(act, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                binding.setIsPlaying(aBoolean);
            }
        });
    }
}
