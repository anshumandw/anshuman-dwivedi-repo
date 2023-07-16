package com.objectModellingFile.jukebox.entities;

import java.util.List;

public class Songs extends BaseEntity{
    
    private String mName;
    private String mGenre;
    private String mAlbumName;
    private List<String> mFeaturedArtist;

    public String getAlbumName() {
        return mAlbumName;
    }

    public List<String> getFeaturedArtist() {
        return mFeaturedArtist;
    }

    public String getName() {
        return mName;
    }

    public String getGenre() {
        return mGenre;
    }

    public Songs(Songs song) {
        this(song.mName, song.mGenre, song.mAlbumName, song.mFeaturedArtist);
    }

    public Songs(Integer songId, String mName2, String mGenre2, String albumName, List<String> featuredArtistNames) {
        this(mName2, mGenre2, albumName, featuredArtistNames);
        this.id = songId;
      }

    public Songs(String mName2, String mGenre2, String albumName, List<String> featuredArtistNames) {
        this.mName = mName2;
        this.mGenre = mGenre2;
        this.mAlbumName = albumName;
        this.mFeaturedArtist = featuredArtistNames;
    }

}
