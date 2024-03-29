package com.objectModellingFile.codingame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.objectModellingFile.codingame.appConfig.ApplicationConfig;
import com.objectModellingFile.codingame.commands.CommandInvoker;
import com.objectModellingFile.codingame.exceptions.NoSuchCommandException;

public class App {
    // To run the application  ./gradlew run --args="INPUT_FILE=input.txt"
    public static void main(String[] args) {
        List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
        String expectedSequence = "INPUT_FILE";
        String actualSequence = commandLineArgs.stream()
                .map(a -> a.split("=")[0])
                .collect(Collectors.joining("$"));
        if(expectedSequence.equals(actualSequence)){
            run(commandLineArgs);
        }
    }
    public static void run(List<String> commandLineArgs) {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        CommandInvoker commandInvoker = applicationConfig.getCommandInvoker();
        BufferedReader reader;
        String inputFile = commandLineArgs.get(0).split("=")[1]; // Input file will input.txt over here
        commandLineArgs.remove(0);
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            while (line != null) {
                List<String> tokens = Arrays.asList(line.split(" ")); //CREATE-USER Kiran -> List.add("CREATE-User") & List.add("Kiran")
                commandInvoker.executeCommand(tokens.get(0),tokens);
                line = reader.readLine(); //It will read next line
            }
            reader.close();
        } catch (IOException | NoSuchCommandException e) {
            e.printStackTrace();
        }

   }
}
