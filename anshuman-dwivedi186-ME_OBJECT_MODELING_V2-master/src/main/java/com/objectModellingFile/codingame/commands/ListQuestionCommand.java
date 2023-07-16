package com.objectModellingFile.codingame.commands;

import java.util.List;

import com.objectModellingFile.codingame.entities.Level;
import com.objectModellingFile.codingame.entities.Question;
import com.objectModellingFile.codingame.services.IQuestionService;

public class ListQuestionCommand implements ICommand{

    private final IQuestionService questionService;
    
    public ListQuestionCommand(IQuestionService questionService) {
        this.questionService = questionService;
    }

    // Execute getAllQuestionLevelWise method of IQuestionService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["LIST_QUESTION","HIGH"]
    // or
    // ["LIST_QUESTION"]

    @Override
    public void execute(List<String> tokens) {
        if(tokens.size() == 1){
            List<Question> qList = questionService.getAllQuestionLevelWise(null);
            System.out.println(qList);
            return;
        }
        String level = tokens.get(1);
        List<Question> qList = questionService.getAllQuestionLevelWise(Level.valueOf(level));
        System.out.println(qList);
    }
    
}
