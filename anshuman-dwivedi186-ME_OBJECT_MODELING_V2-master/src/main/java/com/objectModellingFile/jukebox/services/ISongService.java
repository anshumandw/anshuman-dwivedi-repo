package com.objectModellingFile.jukebox.services;

import com.objectModellingFile.jukebox.entities.Playlist;
import com.objectModellingFile.jukebox.entities.Songs;

public interface ISongService {

    public void loadPoolOfSongs(String file);
    public Songs playSongFromPlaylist(Integer userId, Integer playlistId);
    public Playlist getPlayListFromSong(Integer userId, String songId);
    public Songs getSong(Integer songId);

}
