package com.objectModellingFile.jukebox.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.objectModellingFile.jukebox.entities.Songs;

public class SongRepository implements ISongRepository {
    
    private final Map<Integer, Songs> mSongMap;
    private Integer autoIncrement = 0;

    public SongRepository() {
        mSongMap = new HashMap<>();
    }

    @Override
    public List<Songs> findAll() {
        List<Songs> songList = new ArrayList<>();
        for(int i=0; i<mSongMap.size(); i++) {
            songList.add(mSongMap.get(i));
        }
        return songList;
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
    public Songs save(Songs entity) {
        if( entity.getId() == null){
            autoIncrement++;
            Songs song = new Songs(autoIncrement, entity.getName(), entity.getGenre(), entity.getAlbumName(), entity.getFeaturedArtist());
            mSongMap.put(autoIncrement, song);
            return song;
        }
        mSongMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<Songs> findById(Integer id) {
        return Optional.ofNullable(mSongMap.get(id));
    }

    @Override
    public void delete(Songs entity) {
        
    }

    

}
