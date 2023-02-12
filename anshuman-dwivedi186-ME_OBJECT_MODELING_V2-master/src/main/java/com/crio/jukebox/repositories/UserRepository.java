package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.User;

public class UserRepository implements IUserRepository{

    private final Map<Integer, User> mUserMap;
    private Integer mAutoIncrement = 0;

    public Integer getmAutoIncrement() {
        return mAutoIncrement;
    }

    public UserRepository() {
        mUserMap = new HashMap<>();
    }

    @Override
    public User save(User entity) {
        if( entity.getId() == null ){
            mAutoIncrement++;
            User user = new User(mAutoIncrement, entity.getName());
            mUserMap.put(user.getId(), user);
            return user;
        }
        mUserMap.put(entity.getId(),entity);
        return entity;
    }

    @Override
    public Optional<User> findByName(String name) {
        List<User> user = mUserMap.entrySet().stream().map(x -> x.getValue())
                                     .filter(x -> x.getName() == name).collect(Collectors.toList());
        if(user.size() == 0) {
            return Optional.empty();
        } else return Optional.ofNullable(user.get(0));
    }
    
    @Override
    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(mUserMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(User entity) {
        // TODO Auto-generated method stub
        
    }
    
}
