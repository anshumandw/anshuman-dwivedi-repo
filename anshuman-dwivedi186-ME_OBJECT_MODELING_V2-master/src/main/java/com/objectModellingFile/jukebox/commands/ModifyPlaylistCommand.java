package com.objectModellingFile.jukebox.commands;

import java.util.List;
import com.objectModellingFile.jukebox.entities.Playlist;
import com.objectModellingFile.jukebox.services.IPlaylistService;

public class ModifyPlaylistCommand implements ICommand {

    private IPlaylistService mPlaylistService;

    public ModifyPlaylistCommand(IPlaylistService mPlaylistService) {
        this.mPlaylistService = mPlaylistService;
    }

    @Override
    public void execute(List<String> commands) {
        System.out.println("Playlist ID - " + commands.get(3));
        
        if(commands.get(1).contains("ADD")) {

            Playlist playlist = mPlaylistService.getPlaylists(Integer.parseInt(commands.get(3)));
            List<String> allSongId = playlist.getAllSong();
            if(allSongId.contains(commands.get(4))) {

            } else {
                allSongId.add(commands.get(4));
            }
            playlist = mPlaylistService.create(Integer.parseInt(commands.get(2)), playlist.getName(), allSongId);
            System.out.println("Playlist Name - "+ playlist.getName());
            String allSongsIDs = "";
            for(int i=0; i<allSongId.size()-1; i++) {
                allSongsIDs += allSongId.get(i) + " ";
            }
            allSongsIDs += allSongId.get(allSongId.size()-1);
            System.out.println("Song IDs - "+ allSongsIDs);


        } else if(commands.get(1).contains("DELETE")){


            Playlist playlist = mPlaylistService.getPlaylists(Integer.parseInt(commands.get(3)));
            List<String> allSongId = playlist.getAllSong();
            if(allSongId.contains(commands.get(4))) {
                allSongId.remove(commands.get(4));
            }
            playlist = mPlaylistService.create(Integer.parseInt(commands.get(2)), playlist.getName(), allSongId);
            System.out.println("Playlist Name - "+ playlist.getName());
            String allSongsIDs = "";
            for(int i=0; i<allSongId.size()-1; i++) {
                allSongsIDs += allSongId.get(i) + " ";
            }
            allSongsIDs += allSongId.get(allSongId.size()-1);
            System.out.println("Song IDs - "+ allSongsIDs);
        }
        
    }
    
}
