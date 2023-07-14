package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class User extends BaseEntity{
    private final String mName;
    private List<Playlist> mPlaylist;
    
    public User(String name) {
        this.mName = name;
        this.mPlaylist = new ArrayList<>();
    }

    public User(Integer id, String name) {
        this.id = id;
        this.mName = name;
        this.mPlaylist = new ArrayList<>();
    }

    public User(User user) {
        this(user.id, user.mName);
    }

    public String getName() {
        return mName;
    }

    public void addPlaylist(Playlist playlist) {
        mPlaylist.add(playlist);
    }

    public void deletePlaylist(Playlist playlist) {
        mPlaylist.removeIf(x -> x.getId() == playlist.id);
    }

    public List<Playlist> getPlaylist() {
        return mPlaylist.stream().collect(Collectors.toList());
    }

    public boolean checkIfPlaylistExist(Playlist playlist) {
        for(Playlist i: mPlaylist) {
            if(i.getId() == playlist.getId()) {
                return true;
            }
        }
        return false;        
    }

}
