package com.objectModellingFile.codingame.commands;

import java.util.List;

import com.objectModellingFile.codingame.entities.Level;
import com.objectModellingFile.codingame.entities.Question;
import com.objectModellingFile.codingame.services.IQuestionService;

public class CreateQuestionCommand implements ICommand{

    private final IQuestionService questionService;
    
    public CreateQuestionCommand(IQuestionService questionService) {
        this.questionService = questionService;
    }

    // Execute create method of IQuestionService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["CREATE_QUESTION","Question22","HIGH","220"]

    @Override
    public void execute(List<String> tokens) {
        String title = tokens.get(1);
        String level = tokens.get(2);
        Integer score = Integer.parseInt(tokens.get(3));
        Question question = questionService.create(title, Level.valueOf(level),score);
        System.out.println(question);
    }
    
}
