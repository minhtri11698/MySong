package com.example.mysong.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysong.Activity.PlayMusicActivity;
import com.example.mysong.Adapter.PlayMusicAdapter;
import com.example.mysong.R;

public class FragmentListSongPlay extends Fragment {
    View view;
    RecyclerView playsongrecycleview;
    PlayMusicAdapter playMusicAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_song_play, container, false);
        playsongrecycleview = view.findViewById(R.id.songlistplay);
        if (PlayMusicActivity.songPlayArrayList.size() > 0) {
            playMusicAdapter = new PlayMusicAdapter(getActivity(), PlayMusicActivity.songPlayArrayList);
            playsongrecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
            playsongrecycleview.setAdapter(playMusicAdapter);
        }
        return view;
    }
}
