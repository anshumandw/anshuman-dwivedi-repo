package com.crio.jukebox.entities;

import java.util.List;

public class Albums {

    private String mAlbumName;
    private Artist artist;
    
    public void setmAlbumName(String mAlbumName) {
        this.mAlbumName = mAlbumName;
    }
    public void setArtist(Artist artist) {
        this.artist = artist;
    }
    
    public String getmAlbumName() {
        return mAlbumName;
    }
    public Artist getArtist() {
        return artist;
    }
    
}
