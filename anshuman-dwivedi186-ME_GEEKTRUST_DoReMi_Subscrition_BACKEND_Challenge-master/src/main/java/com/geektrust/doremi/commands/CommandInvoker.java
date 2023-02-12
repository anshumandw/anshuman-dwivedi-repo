package com.geektrust.doremi.commands;

import com.geektrust.doremi.exceptions.NoSuchCommandException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandInvoker {
  Map<String, ICommand> commandMap = new HashMap<>();

  public ICommand getCommand(String commandName) {
    return commandMap.get(commandName);
  }

  public void register(String commandName, ICommand command) {
    commandMap.put(commandName, command);
  }
   
  /**
* Some javadoc.
*/
  public void executeCommand(String commandName, List<String> value) {
    ICommand command = getCommand(commandName);
    if (command == null) {
      throw new NoSuchCommandException();
    }
    command.execute(value);
  }
  
}
