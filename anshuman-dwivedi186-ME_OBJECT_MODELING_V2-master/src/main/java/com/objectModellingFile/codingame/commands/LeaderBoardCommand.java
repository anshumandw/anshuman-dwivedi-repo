package com.objectModellingFile.codingame.commands;

import java.util.List;

import com.objectModellingFile.codingame.entities.ScoreOrder;
import com.objectModellingFile.codingame.entities.User;
import com.objectModellingFile.codingame.services.IUserService;

public class LeaderBoardCommand implements ICommand{

    private final IUserService userService;
    
    public LeaderBoardCommand(IUserService userService) {
        this.userService = userService;
    }

    // Execute getAllUserScoreOrderWise method of IUserService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["LEADERBOARD","ASC"]
    // or
    // ["LEADERBOARD","DESC"]

    @Override
    public void execute(List<String> tokens) {
        ScoreOrder scoreOrder = ScoreOrder.valueOf(tokens.get(1));
        List<User> user = userService.getAllUserScoreOrderWise(scoreOrder);
        System.out.print(user);
    }
    
}
