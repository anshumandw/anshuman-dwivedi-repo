package com.objectModellingFile.codingame.commands;

import java.util.List;

import com.objectModellingFile.codingame.dtos.UserRegistrationDto;
import com.objectModellingFile.codingame.exceptions.ContestNotFoundException;
import com.objectModellingFile.codingame.exceptions.InvalidOperationException;
import com.objectModellingFile.codingame.exceptions.UserNotFoundException;
import com.objectModellingFile.codingame.services.IUserService;

public class AttendContestCommand implements ICommand{

    private final IUserService userService;
    
    public AttendContestCommand(IUserService userService) {
        this.userService = userService;
    }

    // Execute attendContest method of IUserService and print the result.
    // Also Handle Exceptions and print the error messsages if any.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["ATTEND_CONTEST","3","Joey"]
    // Hint - Use Parameterized Exceptions in the Service class to match with the Unit Tests Output.

    @Override
    public void execute(List<String> tokens) {
        try{
        UserRegistrationDto userRegistrationDto =  userService.attendContest(tokens.get(1), tokens.get(2));
        System.out.print(userRegistrationDto);
        } catch (ContestNotFoundException e) {
           System.out.print(e.getMessage());
        } catch (InvalidOperationException e) {
           System.out.print(e.getMessage());  
        } catch (UserNotFoundException e) {
            System.out.print(e.getMessage());  
        } 
    }
    
}
