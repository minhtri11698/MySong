package com.example.mysong.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysong.Activity.PlayMusicActivity;
import com.example.mysong.Model.Song;
import com.example.mysong.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.ViewHolder> {
    Context context;
    ArrayList<Song> songArrayList;


    public SongListAdapter(Context contexts, ArrayList<Song> arrayListSong) {
        this.context = contexts;
        this.songArrayList = arrayListSong;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.song_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = songArrayList.get(position);
        holder.txtsongindexs.setText(position + 1 + "");
        holder.txtsongnames.setText(song.getSongName());
        holder.txtsingers.setText(song.getSinger());
    }

    @Override
    public int getItemCount() {
        return songArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtsongindexs, txtsongnames, txtsingers;
        ImageView linenum;
        public ViewHolder(View itemView){
            super(itemView);
            txtsongindexs = itemView.findViewById(R.id.songlistindex);
            txtsongnames = itemView.findViewById(R.id.songlistname);
            txtsingers = itemView.findViewById(R.id.songlistsinger);
            linenum = itemView.findViewById(R.id.likenumber);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("songid", songArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
