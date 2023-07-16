package com.objectModellingFile.codingame.services;

import java.util.List;

import com.objectModellingFile.codingame.entities.Level;
import com.objectModellingFile.codingame.entities.Question;

public interface IQuestionService {
    public Question create(String title, Level level, Integer difficultyScore);
    public List<Question> getAllQuestionLevelWise(Level level);
}
