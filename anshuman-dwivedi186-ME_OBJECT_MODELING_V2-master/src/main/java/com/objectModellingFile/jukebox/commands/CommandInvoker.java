package com.objectModellingFile.jukebox.commands;

import java.util.Map;
import com.objectModellingFile.jukebox.exceptions.NoSuchCommandException;
import java.util.HashMap;
import java.util.List;

public class CommandInvoker {
    private static final Map<String, ICommand> commandMap = new HashMap<>();

    // Register the command into the HashMap
    public void register(String commandName, ICommand command){
        commandMap.put(commandName,command);
    }

    // Get the registered Command
    private ICommand get(String commandName){
        return commandMap.get(commandName);
    }

    // Execute the registered Command
    public void executeCommand(String commandName, List<String> commands) throws NoSuchCommandException {
        ICommand command = get(commandName);
        if(command == null){
            throw new NoSuchCommandException();
        }
        command.execute(commands);
    }
}
