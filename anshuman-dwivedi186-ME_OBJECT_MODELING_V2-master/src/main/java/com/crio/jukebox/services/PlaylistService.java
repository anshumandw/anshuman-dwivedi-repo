package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;

public class PlaylistService implements IPlaylistService {

    private IPlaylistRepository mPlaylistRepository;
    private ISongRepository mSongRepository;
    private IUserRepository mUserRepository;
    private IUserService mUserService;
    private Integer autoIncrement = 0;


    public PlaylistService(IPlaylistRepository mPlaylistRepository, ISongRepository mSongRepository,
            IUserRepository mUserRepository, IUserService mUserService) {

                this.mPlaylistRepository = mPlaylistRepository;
                this.mSongRepository = mSongRepository;
                this.mUserRepository = mUserRepository;
                this.mUserService = mUserService;
            }


    @Override
    public Playlist create(Integer userId, String playlistName, List<String> songs) {
        final User user = mUserRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Cannot Create User. User for given name: " + playlistName + " not found!"));
        Playlist mPlaylist = mPlaylistRepository.save(new Playlist(playlistName, user, songs));
        user.addPlaylist(mPlaylist);
        return mPlaylist;
    }


    @Override
    public void delete(Integer userId, Integer playlistId) {
        final User user = mUserRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Cannot Create User. User for given id: " + userId + " not found!"));

        //Is playlist present in User.

        boolean playlistPresent =  user.checkIfPlaylistExist(new Playlist(playlistId, user));

        if(playlistPresent == true) {
            mPlaylistRepository.deleteById(playlistId);
        } else {
            throw new PlaylistNotFoundException("Playlist is not created yet or not available");
        }
        
    }

    @Override
    public Playlist getPlaylists(Integer playlistId)  {
        return mPlaylistRepository.findById(playlistId).orElseThrow(() -> new PlaylistNotFoundException("Playlist not exists"));

        // List<Playlist> allPlaylists = mPlaylistRepository.findAll();

        // for(Playlist i: allPlaylists) {
        //     if(i.getId() == playlistId) {
        //         return i;
        //     }
        // }

        // throw new PlaylistNotFoundException("Playlist Not Exists");

    }


    // @Override
    // public void play(Integer userId, Integer playlistId) {
                        
    // }          
    
}
