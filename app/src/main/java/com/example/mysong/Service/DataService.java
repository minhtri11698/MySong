package com.example.mysong.Service;

import com.example.mysong.Model.Album;
import com.example.mysong.Model.MType;
import com.example.mysong.Model.Playlist;
import com.example.mysong.Model.Song;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @GET("playlisttoday.php")
    Call<List<Playlist>> GetPlaylistToday();

    @GET("theloai.php")
    Call<List<MType>> GetMusicType();

    @GET("album.php")
    Call<List<Album>> GetAlbum();

    @GET("danhsachplaylist.php")
    Call<List<Playlist>> GetPlaylistList();

    @FormUrlEncoded
    @POST("songlist.php")
    Call<List<Song>> GetSongListOnType(@Field("idtype") String idType);

    @FormUrlEncoded
    @POST("songlist.php")
    Call<List<Song>> GetSongOnPlaylist(@Field("idplaylist") String idplaylist);
}
