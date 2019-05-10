package com.example.musicplayer.activities;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.view.View;

import com.example.musicplayer.App;
import com.example.musicplayer.R;
import com.example.musicplayer.adapter.BaseAdapter;
import com.example.musicplayer.adapter.PagerAdapter;
import com.example.musicplayer.base.BaseActivity;
import com.example.musicplayer.dao.SystemData;
import com.example.musicplayer.databinding.ActivityMainBinding;
import com.example.musicplayer.fragment.AlbumArtistFragment;
import com.example.musicplayer.fragment.AlbumFragment;
import com.example.musicplayer.fragment.ArtistFragment;
import com.example.musicplayer.fragment.MediaListener;
import com.example.musicplayer.fragment.MusicFragment;
import com.example.musicplayer.model.Album;
import com.example.musicplayer.model.Music;
import com.example.musicplayer.service.MP3Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingDeque;

public class MainActivity extends BaseActivity<ActivityMainBinding>
        implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener, View.OnFocusChangeListener, MediaListener<Music>{

    private MusicFragment fmMusic = new MusicFragment();
    private AlbumFragment fmAlbum = new AlbumFragment();
    private ArtistFragment fmArtist = new ArtistFragment();
    private PagerAdapter adapter;

    private ArrayList<Music> dataSearch;
    private ArrayList<Music> arrayList;
    private SystemData systemData;
    private BaseAdapter<Music> musicAdapter;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MP3Service.MP3Binder binder = (MP3Service.MP3Binder) service;
            App app = (App) getApplicationContext();
            app.setService(binder.getService());
            binding.playView.registerState();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private final String[] PERMISSION = {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private boolean checkPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            for (String p : PERMISSION){
                if (checkSelfPermission(p) != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(PERMISSION, 0);
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (checkPermission()){
            initAct();
        }else {
            finish();
        }
    }

    @Override
    protected void initAct() {

        if (checkPermission() == false){
            return;
        }

        Intent intent = new Intent(this, MP3Service.class);
        bindService(intent, connection, BIND_AUTO_CREATE);

        setSupportActionBar(binding.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navView.setNavigationItemSelectedListener(this);

        adapter = new PagerAdapter(this,getSupportFragmentManager(), fmMusic, fmAlbum, fmArtist);
        binding.pager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.pager);

        // ArrayList<Music>
        systemData = new SystemData(this);
        arrayList = systemData.getSongs();
        musicAdapter = new BaseAdapter<>(this, R.layout.item_music);
        musicAdapter.setListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }



    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_music:
                binding.pager.setCurrentItem(0);
                break;
            case R.id.nav_album:
                binding.pager.setCurrentItem(1);
                break;
            case R.id.nav_artist:
                binding.pager.setCurrentItem(2);
                break;
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setOnQueryTextFocusChangeListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        //Log.d("Ket qua", newText);
        binding.lvSearch.setVisibility(View.VISIBLE);
        dataSearch = new ArrayList<>();
        if (newText != null){
            for (Music ms : arrayList){
                String ms1 = ms.getTitle().toLowerCase();
                String ms2 = newText.toLowerCase();
                if (ms1.indexOf(ms2) != -1){
                    dataSearch.add(ms);
                }
            }
        }
        binding.lvSearch.setAdapter(musicAdapter);
        musicAdapter.setData(dataSearch);
        musicAdapter.notifyDataSetChanged();
        //filter(newText.trim());
        return false;
    }
   /* // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        dataSearch = new ArrayList<>();
        dataSearch.clear();
        if (charText.length() == 0) {
            dataSearch.addAll(arrayList);
        } else {
            for (Music ms : arrayList) {
                if (ms.getTitle().toLowerCase(Locale.getDefault()).contains(charText)) {
                    dataSearch.add(ms);
                }
            }
        }
        binding.lvSearch.setAdapter(musicAdapter);
        musicAdapter.notifyDataSetChanged();
    }*/

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus){
            binding.lvSearch.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemMediaClick(Music music) {
        App app = (App) getApplicationContext();
        int index = musicAdapter.getData().indexOf(music);
        app.getService().setArrMusic(musicAdapter.getData());
        app.getService().create(index);
    }

}
