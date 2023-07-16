package com.objectModellingFile.jukebox.commands;

import java.util.List;
import com.objectModellingFile.jukebox.entities.User;
import com.objectModellingFile.jukebox.services.IUserService;

public class CreateUserCommand implements ICommand {

    private final IUserService userService;
    
    public CreateUserCommand(IUserService userService) {
        this.userService = userService;
    }

    // TODO: objectModellingFile_TASK_MODULE_CONTROLLER
    // Execute create method of IUserService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["CREATE_QUESTION","Ross"]

    @Override
    public void execute(List<String> tokens) {
        User user = userService.create(tokens.get(1));
        System.out.println(user.getId() + " " + user.getName());
    }
    
}
