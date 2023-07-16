package com.objectModellingFile.jukebox.commands;

import java.util.List;
import com.objectModellingFile.jukebox.entities.Songs;
import com.objectModellingFile.jukebox.services.ISongService;

public class PlayPlaylistCommand implements ICommand {

    private ISongService mSongService;

    public PlayPlaylistCommand(ISongService mSongService) {
        this.mSongService = mSongService;
    }

    @Override
    public void execute(List<String> commands) {
        System.out.println("Current Song Playing");
        Songs song = mSongService.playSongFromPlaylist(Integer.parseInt(commands.get(1)), Integer.parseInt(commands.get(2)));
        System.out.println("Song - " + song.getName());
        System.out.println("Album - " + song.getAlbumName());
        String allArtists = "" ;
        //Artists - Ed Sheeran,Cardi.B,Camilla Cabello
        List<String> featuredArtist = song.getFeaturedArtist();
        for(int i=0; i<featuredArtist.size()-1; i++) {
            allArtists += featuredArtist.get(i) + ",";
        }
        allArtists += featuredArtist.get(featuredArtist.size()-1);
        System.out.println("Artists - " + allArtists);
    }
    
}
