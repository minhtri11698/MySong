package com.example.mysong.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Song implements Parcelable {

    @SerializedName("idSong")
    @Expose
    private String idSong;
    @SerializedName("idAlbum")
    @Expose
    private String idAlbum;
    @SerializedName("idType")
    @Expose
    private String idType;
    @SerializedName("idPlaylist")
    @Expose
    private String idPlaylist;
    @SerializedName("songName")
    @Expose
    private String songName;
    @SerializedName("songPhoto")
    @Expose
    private String songPhoto;
    @SerializedName("singer")
    @Expose
    private String singer;
    @SerializedName("songLink")
    @Expose
    private String songLink;
    @SerializedName("likeNum")
    @Expose
    private String likeNum;

    protected Song(Parcel in) {
        idSong = in.readString();
        idAlbum = in.readString();
        idType = in.readString();
        idPlaylist = in.readString();
        songName = in.readString();
        songPhoto = in.readString();
        singer = in.readString();
        songLink = in.readString();
        likeNum = in.readString();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public String getIdSong() {
        return idSong;
    }

    public void setIdSong(String idSong) {
        this.idSong = idSong;
    }

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(String idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongPhoto() {
        return songPhoto;
    }

    public void setSongPhoto(String songPhoto) {
        this.songPhoto = songPhoto;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSongLink() {
        return songLink;
    }

    public void setSongLink(String songLink) {
        this.songLink = songLink;
    }

    public String getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(String likeNum) {
        this.likeNum = likeNum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idSong);
        dest.writeString(idAlbum);
        dest.writeString(idType);
        dest.writeString(idPlaylist);
        dest.writeString(songName);
        dest.writeString(songPhoto);
        dest.writeString(singer);
        dest.writeString(songLink);
        dest.writeString(likeNum);
    }
}