package com.crio.jukebox.services;

import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.repositories.UserRepository;

public class UserService implements IUserService {

    private final IUserRepository mUserRepository;
    private final IPlaylistRepository mPlaylistRepository;

    public UserService(IUserRepository mUserRepository, IPlaylistRepository mPlaylistRepository) {
        this.mPlaylistRepository = mPlaylistRepository;
        this.mUserRepository = mUserRepository;
    }

    @Override
    public User create(String name) {
        User user = mUserRepository.save(new User(name));
        return user;
    }

    // public User checkIfUserPresent(Integer id) {
    //     final User user = mUserRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not available. User for given id: " + String.valueOf(id) + " not found!"));
    //     return user;
    // }

    
}
