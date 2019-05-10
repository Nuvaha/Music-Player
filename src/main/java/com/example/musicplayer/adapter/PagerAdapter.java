package com.example.musicplayer.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.musicplayer.base.BaseFragment;
import com.example.musicplayer.fragment.AlbumFragment;

public class PagerAdapter extends FragmentPagerAdapter {
    private BaseFragment[] arrFm;
    private Context context;

    public PagerAdapter(Context context, FragmentManager fm, BaseFragment... arrFm) {
        super(fm);
        this.arrFm = arrFm;
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        return arrFm[i];
    }

    @Override
    public int getCount() {
        return arrFm.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getString(arrFm[position].getTitle());
    }
}
