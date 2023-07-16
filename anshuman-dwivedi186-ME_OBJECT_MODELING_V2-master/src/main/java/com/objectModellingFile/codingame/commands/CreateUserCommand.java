package com.objectModellingFile.codingame.commands;

import java.util.List;

import com.objectModellingFile.codingame.entities.User;
import com.objectModellingFile.codingame.services.IUserService;

public class CreateUserCommand implements ICommand{

    private final IUserService userService;
    
    public CreateUserCommand(IUserService userService) {
        this.userService = userService;
    }

    // Execute create method of IUserService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["CREATE_QUESTION","Ross"]

    @Override
    public void execute(List<String> tokens) {
        User user = userService.create(tokens.get(1));
        System.out.print(user);
    }
    
}
