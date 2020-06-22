package com.example.mysong.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysong.Model.Song;
import com.example.mysong.R;

import java.util.ArrayList;

public class PlayMusicAdapter extends RecyclerView.Adapter<PlayMusicAdapter.ViewHolder> {

    Context context;
    ArrayList<Song> playsongArrayList;

    public PlayMusicAdapter(Context context, ArrayList<Song> playsongArrayList) {
        this.context = context;
        this.playsongArrayList = playsongArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_play_music, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = playsongArrayList.get(position);
        holder.indexsong.setText(position + 1 + "");
        holder.tenbaihat.setText(song.getSongName());
        holder.tencasi.setText(song.getSinger());
    }

    @Override
    public int getItemCount() {
        return playsongArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tenbaihat, tencasi, indexsong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenbaihat = itemView.findViewById(R.id.playmusicsongname);
            tencasi = itemView.findViewById(R.id.songplaysingername);
            indexsong = itemView.findViewById(R.id.songplayindex);
        }
    }
}
