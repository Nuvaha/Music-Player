package com.example.musicplayer;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class AppBinding {

    @BindingAdapter("thumb")
    public static void setThumb(ImageView im, String img){
        Glide.with(im)
                .load(img)
                .into(im);
    }
}
