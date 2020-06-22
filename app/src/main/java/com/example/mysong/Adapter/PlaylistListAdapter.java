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

import com.example.mysong.Activity.DanhsachPlaylistActivity;
import com.example.mysong.Activity.SongListActivity;
import com.example.mysong.Model.Playlist;
import com.example.mysong.R;
import com.squareup.picasso.Picasso;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class PlaylistListAdapter extends RecyclerView.Adapter<PlaylistListAdapter.ViewHolder> {
    Context context;
    ArrayList<Playlist> playlistItemArrayList;

    public PlaylistListAdapter(Context context, ArrayList<Playlist> playlistItemArrayList) {
        this.context = context;
        this.playlistItemArrayList = playlistItemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.playlist_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = playlistItemArrayList.get(position);
        Picasso.with(context).load(playlist.getPlaylistIcon()).into(holder.imgplaylistitem);
        holder.txtplaylistitemname.setText(playlist.getPlaylistName());
    }

    @Override
    public int getItemCount() {
        return playlistItemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgplaylistitem;
        TextView txtplaylistitemname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgplaylistitem = itemView.findViewById(R.id.playlistitemicon);
            txtplaylistitemname = itemView.findViewById(R.id.playlistittemname);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SongListActivity.class);
                    intent.putExtra("itemplaylist", playlistItemArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
