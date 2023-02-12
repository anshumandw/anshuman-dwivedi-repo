

package com.geektrust.doremi;

import com.geektrust.doremi.appconfig.ApplicationConfig;
import com.geektrust.doremi.commands.CommandInvoker;
import com.geektrust.doremi.exceptions.NoSuchCommandException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class App {
  /**
* Some javadoc.
*/
  public static void main(String[] args) {
    ApplicationConfig applicationConfig = new ApplicationConfig();
    CommandInvoker commandInvoker = applicationConfig.getCommandInvoker();
    String filePath = args[0];
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(filePath));
      String commandline = reader.readLine();
      while (commandline != null) {
        List<String> value = Arrays.asList(commandline.split(" "));
        commandInvoker.executeCommand(value.get(0),value);
        commandline = reader.readLine();
      }
      reader.close();
    } catch (IOException | NoSuchCommandException e) {
      e.printStackTrace();
    }
  }
}
