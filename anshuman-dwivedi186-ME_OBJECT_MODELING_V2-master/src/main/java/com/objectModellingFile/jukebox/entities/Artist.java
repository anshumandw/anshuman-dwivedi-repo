package com.objectModellingFile.jukebox.entities;

import java.util.List;

public class Artist {
    private String mArtistname;
    private List<String> mFeaturedArtists;
    
    public void setmArtistname(String mArtistname) {
        this.mArtistname = mArtistname;
    }

    public void setmFeaturedArtists(List<String> mFeaturedArtists) {
        this.mFeaturedArtists = mFeaturedArtists;
    }

    public List<String> getmFeaturedArtists() {
        return mFeaturedArtists;
    }
    
}
