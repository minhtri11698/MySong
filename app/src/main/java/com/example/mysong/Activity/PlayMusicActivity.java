package com.example.mysong.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.mysong.Adapter.ViewpagerPlayMusicList;
import com.example.mysong.Fragment.FragmentListSongPlay;
import com.example.mysong.Model.Song;
import com.example.mysong.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PlayMusicActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView txtsongtimer, txttotaltimesong;
    SeekBar songtime;
    AppCompatImageButton playbtn, prebtn, nextbtn, loopbtn, randbtn;
    ViewPager viewPagerPlay;
    FragmentListSongPlay fragmentListSongPlay;
    MediaPlayer mediaPlayer;
    public static ArrayList<Song> songPlayArrayList = new ArrayList<>();
    public static ViewpagerPlayMusicList viewpagerPlayMusicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        GetDataFromIntent();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        init();
        eventClick();

    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewpagerPlayMusicList.getItem(1) != null){
                    if (songPlayArrayList.size() > 0){
                        handler.removeCallbacks(this);
                    }
                }
            }
        }, 500);
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    playbtn.setImageResource(R.drawable.playbutton);
                } else {
                    mediaPlayer.start();
                    playbtn.setImageResource(R.drawable.pause);
                }
            }
        });
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        songPlayArrayList.clear();
        if (intent != null){
            if (intent.hasExtra("songid")){
                Song song = intent.getParcelableExtra("songid");
                songPlayArrayList.add(song);
            }
            if (intent.hasExtra("allsong")){
                ArrayList<Song> songArrayLists = intent.getParcelableArrayListExtra("allsong");
                songPlayArrayList = songArrayLists;
            }
        }
    }

    private void init() {
        toolbar = findViewById(R.id.playmusictoolbar);
        txtsongtimer = findViewById(R.id.songtimmer);
        txttotaltimesong = findViewById(R.id.totalsongtime);
        songtime = findViewById(R.id.seekbarsong);
        playbtn = findViewById(R.id.playbutton);
        prebtn = findViewById(R.id.previousbutton);
        nextbtn = findViewById(R.id.nextbutton);
        loopbtn = findViewById(R.id.loopbutton);
        randbtn = findViewById(R.id.shufflebutton);
        viewPagerPlay = findViewById(R.id.playmusicviewpager);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitleTextColor(Color.WHITE);
        fragmentListSongPlay = new FragmentListSongPlay();
        viewpagerPlayMusicList = new ViewpagerPlayMusicList(getSupportFragmentManager());
        viewpagerPlayMusicList.AddFragment(fragmentListSongPlay);
        viewPagerPlay.setAdapter(viewpagerPlayMusicList);
        if (songPlayArrayList.size() > 0){
            getSupportActionBar().setTitle(songPlayArrayList.get(0).getSongName());
            new PlaySong().execute(songPlayArrayList.get(0).getSongLink());
            playbtn.setImageResource(R.drawable.pause);
        }
    }

    @SuppressLint("StaticFieldLeak")
    class PlaySong extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(s);
            } catch (IOException e) {
                e.printStackTrace();
            }

            mediaPlayer.start();
            TimeSong();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txttotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        songtime.setMax(mediaPlayer.getDuration());
    }
}
