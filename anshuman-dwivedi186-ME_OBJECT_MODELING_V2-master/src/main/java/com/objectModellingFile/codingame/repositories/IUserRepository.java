package com.objectModellingFile.codingame.repositories;

import java.util.Optional;

import com.objectModellingFile.codingame.entities.User;

public interface IUserRepository extends CRUDRepository<User,String> {
    public Optional<User> findByName(String name); 
}
