package com.example.mysong.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mysong.Adapter.SongListAdapter;
import com.example.mysong.Model.MType;
import com.example.mysong.Model.Playlist;
import com.example.mysong.Model.Song;
import com.example.mysong.R;
import com.example.mysong.Service.APIService;
import com.example.mysong.Service.DataService;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongListActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton floatingActionButton;
    Toolbar toolbar;
    RecyclerView recyclerViewSongList;
    ImageView songlistimg;
    Playlist playlist;
    MType mType;
    ArrayList<Song> songArrayLists;
    SongListAdapter songListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        LinkID();
        init();
        DataIntent();
    }

    public void getDataType(String typeID){
        DataService dataService = APIService.getService();
        Call<List<Song>> callback = dataService.GetSongListOnType(typeID);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songArrayLists = (ArrayList<Song>) response.body();
                songListAdapter = new SongListAdapter(SongListActivity.this, songArrayLists);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SongListActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewSongList.setLayoutManager(linearLayoutManager);
                recyclerViewSongList.setAdapter(songListAdapter);
                playallClickEvent();
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void getDataPlaylist(String idPlaylist) {
        DataService dataService = APIService.getService();
        Call<List<Song>> callback = dataService.GetSongOnPlaylist(idPlaylist);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songArrayLists = (ArrayList<Song>) response.body();
                songListAdapter = new SongListAdapter(SongListActivity.this, songArrayLists);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SongListActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewSongList.setLayoutManager(linearLayoutManager);
                recyclerViewSongList.setAdapter(songListAdapter);
                playallClickEvent();
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void setValueInView(String playlistName, String playlistIcon) {
        collapsingToolbarLayout.setTitle(playlistName);
        try {
            URL url = new URL(playlistIcon);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(playlistIcon).into(songlistimg);
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        floatingActionButton.setEnabled(false);
    }

    private void LinkID() {
        coordinatorLayout = findViewById(R.id.coodlayoutsonglist);
        collapsingToolbarLayout = findViewById(R.id.collaplayout);
        floatingActionButton = findViewById(R.id.floatplaybutton);
        toolbar = findViewById(R.id.toolbarsong);
        recyclerViewSongList = findViewById(R.id.reclerviewsong);
        songlistimg = findViewById(R.id.songlistimageview);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null){
            if(intent.hasExtra("itemplaylist")){
                playlist = (Playlist) intent.getSerializableExtra("itemplaylist");
                setValueInView(playlist.getPlaylistName(), playlist.getPlaylistIcon());
                getDataPlaylist(playlist.getIdPlaylist());
            }

            if (intent.hasExtra("idtype")){
                mType = (MType) intent.getSerializableExtra("idtype");
                setValueInView(mType.getTypeName(), mType.getTypePhoto());
                getDataType(mType.getIdType());
            }
        }
    }
    private void playallClickEvent(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SongListActivity.this, PlayMusicActivity.class);
                intent.putExtra("allsong", songArrayLists);
                startActivity(intent);
            }
        });
    }
}
