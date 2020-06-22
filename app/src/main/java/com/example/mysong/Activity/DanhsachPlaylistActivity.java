package com.example.mysong.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.mysong.Adapter.PlaylistListAdapter;
import com.example.mysong.Model.Playlist;
import com.example.mysong.R;
import com.example.mysong.Service.APIService;
import com.example.mysong.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachPlaylistActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerViewPlaylistList;
    PlaylistListAdapter playlistListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsach_playlist);
        link();
        init();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Playlist>> callback = dataService.GetPlaylistList();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> playlistArrayList = (ArrayList<Playlist>) response.body();
                playlistListAdapter = new PlaylistListAdapter(DanhsachPlaylistActivity.this, playlistArrayList);
                recyclerViewPlaylistList.setLayoutManager(new GridLayoutManager(DanhsachPlaylistActivity.this, 2));
                recyclerViewPlaylistList.setAdapter(playlistListAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Playlists");
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void link() {
        toolbar = findViewById(R.id.toolbarplaylistlist);
        recyclerViewPlaylistList = findViewById(R.id.recycleviewplaylistlist);

    }
}
