package com.crio.jukebox.services;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Songs;

public interface ISongService {

    public void loadPoolOfSongs(String file);
    public Songs playSongFromPlaylist(Integer userId, Integer playlistId);
    public Playlist getPlayListFromSong(Integer userId, String songId);
    public Songs getSong(Integer songId);

}
