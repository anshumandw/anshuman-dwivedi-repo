package com.crio.jukebox.entities;

import java.util.List;

public class Playlist extends BaseEntity{

    private User user;
    private String playlistName;
    private List<String> song;

    // public void setSong(List<String> song) {
    //     this.song = song;
    // }

    public User getUser() {
        return user;
    }

    public List<String> getSong() {
        return song;
    }

    public String getName() {
        return playlistName;
    }

    public List<String> getAllSong() {
        return song;
    }

    public Playlist(String playlistName, Integer id) {
        this.id = id;
        this.playlistName = playlistName;
    }

    public Playlist(String playlistName, User user) {
        this.playlistName = playlistName;
        this.user = user;
    }

    public Playlist(Integer playlistId, User user) {
        this.id = playlistId;
        this.user = user;
    }

    public Playlist(String name, User user, List<String> song) {
        this(name, user);
        this.song = song;
    }

    public Playlist(Integer id, String name, User user, List<String> song) {
        this(name, user, song);
        this.id = id;
    }

    public Playlist(Playlist playlist) {
        this(playlist.playlistName, playlist.user, playlist.song);
    }

}
