package com.example.mysong.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysong.Model.Album;
import com.example.mysong.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> albumArrayList;

    public AlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.album_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albumArrayList.get(position);
        holder.txttencasi.setText(album.getSingerName());
        holder.txttenalbum.setText(album.getAlbumName());
        Picasso.with(context).load(album.getAlbumPhoto()).into(holder.anhalbum);
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView anhalbum;
        TextView txttenalbum, txttencasi;
        public ViewHolder(View itemView){
            super(itemView);
            anhalbum = itemView.findViewById(R.id.anhalbumitem);
            txttenalbum = itemView.findViewById(R.id.tvalbumname);
            txttencasi = itemView.findViewById(R.id.tvtencasi);
        }
    }
}
