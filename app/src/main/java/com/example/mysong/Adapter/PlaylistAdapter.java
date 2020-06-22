package com.example.mysong.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mysong.Activity.SongListActivity;
import com.example.mysong.Model.Playlist;
import com.example.mysong.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }
    class ViewHolder{
        TextView tvplaylistname;
        ImageView imgplaylistbg, imgplaylist;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.playlist_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tvplaylistname  = convertView.findViewById(R.id.tvplaylistname);
            viewHolder.imgplaylist = convertView.findViewById(R.id.imgvplaylist);
            viewHolder.imgplaylistbg = convertView.findViewById(R.id.imgviewplaylistbg);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Playlist playlist = getItem(position);
        Picasso.with(getContext()).load(playlist.getPlaylistBg()).into(viewHolder.imgplaylistbg);
        Picasso.with(getContext()).load(playlist.getPlaylistIcon()).into(viewHolder.imgplaylist);
        viewHolder.tvplaylistname.setText(playlist.getPlaylistName());
        return convertView;
    }
}
