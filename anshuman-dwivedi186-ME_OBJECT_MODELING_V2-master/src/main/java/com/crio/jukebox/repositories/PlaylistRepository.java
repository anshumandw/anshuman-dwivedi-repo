package com.crio.jukebox.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Playlist;

public class PlaylistRepository implements IPlaylistRepository{

    private Map<Integer, Playlist> mPlaylistMap = new HashMap<>();
    private Integer autoIncrement = 0;

    // public PlaylistRepository(Map<Integer, Playlist> mPlaylistMap) {
    //     this.mPlaylistMap = mPlaylistMap;
    //     this.autoIncrement = mPlaylistMap.size();
    // }


    @Override
    public Playlist save(Playlist entity) {
        if( entity.getId() == null){
            autoIncrement++;
            Playlist playlist = new Playlist(autoIncrement, entity.getName(), entity.getUser(), entity.getSong());
            mPlaylistMap.put(autoIncrement, playlist);
            return playlist;
        }
        mPlaylistMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Playlist> findAll() {
        List<Playlist> list = mPlaylistMap.entrySet().stream()
                                     .map(x -> x.getValue())
                                     .collect(Collectors.toList());

        return list;                                     
    }

    @Override
    public Optional<Playlist> findById(Integer id) {
        return Optional.ofNullable(mPlaylistMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void delete(Playlist entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(Integer id) {
        mPlaylistMap.remove(id);
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
