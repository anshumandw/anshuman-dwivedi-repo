package com.objectModellingFile.codingame.commands;

import java.util.List;

import com.objectModellingFile.codingame.entities.Contest;
import com.objectModellingFile.codingame.entities.Level;
import com.objectModellingFile.codingame.services.IContestService;

public class ListContestCommand implements ICommand{

    private final IContestService contestService;
    
    public ListContestCommand(IContestService contestService) {
        this.contestService = contestService;
    }

    // Execute getAllContestLevelWise method of IContestService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["LIST_CONTEST","HIGH"]
    // or
    // ["LIST_CONTEST"]

    @Override
    public void execute(List<String> tokens) {
        if(tokens.size() > 1) {
        Level level = Level.valueOf(tokens.get(1));
        List<Contest> contest = contestService.getAllContestLevelWise(level);
        System.out.print(contest);
        return;
        }

        List<Contest> contest = contestService.getAllContestLevelWise(null);
        System.out.print(contest);
        
    }
    
}
