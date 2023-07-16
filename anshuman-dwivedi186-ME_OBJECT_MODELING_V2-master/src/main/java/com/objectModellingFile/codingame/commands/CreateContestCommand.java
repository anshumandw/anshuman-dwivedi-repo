package com.objectModellingFile.codingame.commands;

import java.util.List;

import com.objectModellingFile.codingame.entities.Contest;
import com.objectModellingFile.codingame.entities.Level;
import com.objectModellingFile.codingame.exceptions.QuestionNotFoundException;
import com.objectModellingFile.codingame.exceptions.UserNotFoundException;
import com.objectModellingFile.codingame.services.IContestService;

public class CreateContestCommand implements ICommand{

    private final IContestService contestService;

    public CreateContestCommand(IContestService contestService) {
        this.contestService = contestService;
    }

    // Execute create method of IContestService and print the result.
    // Also Handle Exceptions and print the error messsages if any.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["CREATE_CONTEST","objectModellingFileDO2_CONTEST","LOW Monica","40"]
    // or
    // ["CREATE_CONTEST","objectModellingFileDO1_CONTEST","HIGH","Ross"]
    // Hint - Use Parameterized Exceptions in the Service class to match with the Unit Tests Output.

    @Override
    public void execute(List<String> tokens) {
        Level level = Level.valueOf(tokens.get(2));
        try{
        if(tokens.size() > 4) {
        Integer integer = Integer.parseInt(tokens.get(4));
        Contest contest = contestService.create(tokens.get(1), level, tokens.get(3), integer);
            System.out.print(contest);
        } else {
            Contest contest = contestService.create(tokens.get(1), level, tokens.get(3), null);
            System.out.print(contest);
        }
        } catch (UserNotFoundException | QuestionNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
    
}
