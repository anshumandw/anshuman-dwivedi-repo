package com.objectModellingFile.jukebox.commands;

import java.util.List;
import com.objectModellingFile.jukebox.entities.Playlist;
import com.objectModellingFile.jukebox.entities.Songs;
import com.objectModellingFile.jukebox.services.ISongService;

public class PlaySongCommand implements ICommand {

    private ISongService mSongService;
    private Songs songs;
    private Integer autoIncrement;
    private Integer songSize;
    // Next - 3, Next - 0, Back - 3, Back - 2

    public PlaySongCommand(ISongService mSongService) {
        this.mSongService = mSongService;
    }

    @Override
    public void execute(List<String> commands) {
        //commands.get(1) == userId
        //commands.get(2) == songId
        Playlist playList = mSongService.getPlayListFromSong(Integer.parseInt(commands.get(1)), commands.get(2));
    //    Songs song = new Songs();
    if(commands.get(2).contains("NEXT")) {

        System.out.println("Current Song Playing");
        this.autoIncrement += 1;

            if(autoIncrement > songSize - 1) {
                this.autoIncrement = 0;
            }

            List<String> songIds = playList.getAllSong();
            this.songs = mSongService.getSong(Integer.parseInt(songIds.get(this.autoIncrement)));
            System.out.println("Song - " + songs.getName());
            System.out.println("Album - " + songs.getAlbumName());

            String allArtists = "" ;
            //Artists - Ed Sheeran,Cardi.B,Camilla Cabello
            List<String> featuredArtist = songs.getFeaturedArtist();
            for(int i=0; i<featuredArtist.size()-1; i++) {
                allArtists += featuredArtist.get(i) + ",";
            }
            allArtists += featuredArtist.get(featuredArtist.size()-1);
            System.out.println("Artists - " + allArtists);

    } else if(commands.get(2).contains("BACK")) {

        System.out.println("Current Song Playing");

        this.autoIncrement -= 1;

            if(autoIncrement < 0) {
                this.autoIncrement = this.songSize - 1;
            } 
            List<String> songIds = playList.getAllSong();
            this.songs = mSongService.getSong(Integer.parseInt(songIds.get(this.autoIncrement)));
            System.out.println("Song - " + songs.getName());
            System.out.println("Album - " + songs.getAlbumName());

            String allArtists = "" ;
            //Artists - Ed Sheeran,Cardi.B,Camilla Cabello
            List<String> featuredArtist = songs.getFeaturedArtist();
            for(int i=0; i<featuredArtist.size()-1; i++) {
                allArtists += featuredArtist.get(i) + ",";
            }
            allArtists += featuredArtist.get(featuredArtist.size()-1);
            System.out.println("Artists - " + allArtists);

    } else {
        List<String> songIds = playList.getAllSong();
        
            this.autoIncrement = songIds.indexOf(commands.get(2));//2
            this.songSize = songIds.size();//4

            if(songIds.contains(commands.get(2))) {
                this.songs = mSongService.getSong(Integer.parseInt(commands.get(2)));
                System.out.println("Current Song Playing");
                System.out.println("Song - " + songs.getName());
                System.out.println("Album - " + songs.getAlbumName());

                String allArtists = "" ;
                //Artists - Ed Sheeran,Cardi.B,Camilla Cabello
                List<String> featuredArtist = songs.getFeaturedArtist();
                for(int i=0; i<featuredArtist.size()-1; i++) {
                    allArtists += featuredArtist.get(i) + ",";
                }
                allArtists += featuredArtist.get(featuredArtist.size()-1);
                System.out.println("Artists - " + allArtists);
            } else {
                System.out.println("Given song id is not a part of the active playlist");
            }
    }      
        
    }
    
}
