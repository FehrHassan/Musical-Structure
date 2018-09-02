package com.fehr.nanodegree.musicalstructure.utils;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable {
    private String mName;
    private String mArtist;
    private String mAlbum;
    private Playlist[] mPlaylist;
    private String photoUrl;
    private boolean isToString = false;

    public Song(String name, String artist, String album, String photoUrl, boolean isToString) {
        this.mName = name;
        this.mArtist = artist;
        this.mAlbum = album;
        this.photoUrl = photoUrl;
        this.isToString = isToString;
    }

    public Song(String name, String artist, String album, Playlist[] playlist, String photoUrl, boolean isToString) {
        this.mName = name;
        this.mArtist = artist;
        this.mAlbum = album;
        this.mPlaylist = playlist;
        this.photoUrl = photoUrl;
        this.isToString = isToString;
    }


    protected Song(Parcel in) {
        mName = in.readString();
        mArtist = in.readString();
        mAlbum = in.readString();
        photoUrl = in.readString();
        isToString = in.readByte() != 0;
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

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    public String getAlbum() {
        return mAlbum;
    }

    public void setAlbum(String album) {
        mAlbum = album;
    }

    public Playlist[] getPlaylist() {
        return mPlaylist;
    }

    public void setPlaylist(Playlist[] playlist) {
        mPlaylist = playlist;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public boolean getIsToString() {
        return isToString;
    }

    @Override
    public String toString() {
        return mName + " (by " + mArtist + " from " + mAlbum + ")";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
        parcel.writeString(mArtist);
        parcel.writeString(mAlbum);
        parcel.writeString(photoUrl);
        parcel.writeByte((byte) (isToString ? 1 : 0));
    }
}
