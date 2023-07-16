package com.objectModellingFile.codingame.repositories;

import java.util.List;

import com.objectModellingFile.codingame.entities.Contest;
import com.objectModellingFile.codingame.entities.Level;

public interface IContestRepository extends CRUDRepository<Contest,String> {
    public List<Contest> findAllContestLevelWise(Level level);
}
