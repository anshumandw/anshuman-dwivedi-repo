package com.objectModellingFile.jukebox.repositories;

import java.util.Optional;
import com.objectModellingFile.jukebox.entities.Playlist;
import com.objectModellingFile.jukebox.entities.User;

public interface IUserRepository extends CRUDRepository<User, String>  {
    public Optional<User> findByName(String name);
    public User save(User user); 
}
