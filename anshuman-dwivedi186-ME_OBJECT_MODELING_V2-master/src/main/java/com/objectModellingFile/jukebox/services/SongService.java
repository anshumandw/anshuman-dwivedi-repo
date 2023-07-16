package com.objectModellingFile.jukebox.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.objectModellingFile.jukebox.entities.Playlist;
import com.objectModellingFile.jukebox.entities.Songs;
import com.objectModellingFile.jukebox.exceptions.PlaylistNotFoundException;
import com.objectModellingFile.jukebox.exceptions.SongNotFoundException;
import com.objectModellingFile.jukebox.exceptions.UserNotHavingPlaylistException;
import com.objectModellingFile.jukebox.repositories.IPlaylistRepository;
import com.objectModellingFile.jukebox.repositories.ISongRepository;

public class SongService implements ISongService{

    private final ISongRepository mSongRepository;
    private final IPlaylistRepository mPlaylistRepository;
    private List<String> mAllSongs = new ArrayList<>();
    private Playlist playlist;
    // private List<Songs> allSongs = new ArrayList<>();

    public SongService(ISongRepository mSongRepository, IPlaylistRepository mPlaylistRepository) {
        this.mSongRepository = mSongRepository;
        this.mPlaylistRepository = mPlaylistRepository;
    }

    @Override
    public void loadPoolOfSongs(String file) {
    
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                mAllSongs.add(line);
            }
            br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            List<String> songDetails;

            for(String i: mAllSongs) {
                songDetails = Arrays.asList(i.split(","));
                mSongRepository.save(new Songs(songDetails.get(0), songDetails.get(1), songDetails.get(2), Arrays.asList(songDetails.get(songDetails.size()-1).split("#"))));
                // allSongs.add(song);
            }
            
    }

    @Override
    public Songs playSongFromPlaylist(Integer userId, Integer playlistId) {
       playlist = mPlaylistRepository.findById(playlistId).orElseThrow(() -> new PlaylistNotFoundException("Playlist Not Found"));
       if(playlist.getUser().getId() == userId) {
        Integer songId = Integer.parseInt(playlist.getAllSong().get(0));
        Songs song = mSongRepository.findById(songId).orElseThrow(() -> new SongNotFoundException("Song Not Found"));
        // mPlaylistRepository.save(new Playlist(playlist.getId(), playlist.getName(), playlist.getUser(), playlist.getAllSong(), PlaylistStatus.RUNNING));
        return song;
       } else {
        throw new UserNotHavingPlaylistException("Playlist doesn't exist with the User ID given.");
       }
    }

    @Override
    public Playlist getPlayListFromSong(Integer userId, String songId) {
        return this.playlist;
    }

    @Override
    public Songs getSong(Integer songId) {
        return mSongRepository.findById(songId).orElseThrow(() -> new SongNotFoundException("Song not found!"));
    }
    
}
