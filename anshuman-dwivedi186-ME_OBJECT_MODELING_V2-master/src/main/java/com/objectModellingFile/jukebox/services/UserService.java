package com.objectModellingFile.jukebox.services;

import com.objectModellingFile.jukebox.entities.User;
import com.objectModellingFile.jukebox.exceptions.UserNotFoundException;
import com.objectModellingFile.jukebox.repositories.IPlaylistRepository;
import com.objectModellingFile.jukebox.repositories.IUserRepository;
import com.objectModellingFile.jukebox.repositories.PlaylistRepository;
import com.objectModellingFile.jukebox.repositories.UserRepository;

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
