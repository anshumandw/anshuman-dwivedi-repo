package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.services.IPlaylistService;

public class CreatePlaylistCommand implements ICommand {

    private IPlaylistService mPlaylistService;

    public CreatePlaylistCommand(IPlaylistService mPlaylistService) {
        this.mPlaylistService = mPlaylistService;
    }

    @Override
    public void execute(List<String> commands) {
        List<String> playlistSongs = new ArrayList<>();
        for(int i=3; i<commands.size(); i++) {
            playlistSongs.add(commands.get(i));
        }
        Playlist mPlaylist = mPlaylistService.create(Integer.parseInt(commands.get(1)), commands.get(2), playlistSongs);
        System.out.println("Playlist ID - "+ mPlaylist.getId()); //If Playlist already exist
        
    }
    
}
