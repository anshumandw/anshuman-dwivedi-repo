package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.Playlist;

public interface IPlaylistService {
    public Playlist create(Integer userId, String name, List<String> songs);
    public void delete(Integer userId, Integer playlistId);
    public Playlist getPlaylists(Integer playlistId);
    // public void play(Integer userId, Integer playlistId);
}
