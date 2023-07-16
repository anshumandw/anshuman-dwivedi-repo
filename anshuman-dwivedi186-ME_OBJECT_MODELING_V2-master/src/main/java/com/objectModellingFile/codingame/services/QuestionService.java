package com.objectModellingFile.codingame.services;

import java.util.List;

import com.objectModellingFile.codingame.entities.Level;
import com.objectModellingFile.codingame.entities.Question;
import com.objectModellingFile.codingame.repositories.IQuestionRepository;

public class QuestionService implements IQuestionService{
    private final IQuestionRepository questionRepository;

    public QuestionService(IQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    @Override
    public Question create(String title, Level level, Integer difficultyScore) {
     final Question question = new Question(title,level, difficultyScore);
        return questionRepository.save(question);
    }

    // Get All Questions if level is not specified.
    // Or
    // Get List of Question which matches the level provided.

    @Override
    public List<Question> getAllQuestionLevelWise(Level level) {
        List<Question> list = questionRepository.findAllQuestionLevelWise(level);
        if(level == null) {
          list = questionRepository.findAll();
          return list;
        }
        return list;
    }
    
}
