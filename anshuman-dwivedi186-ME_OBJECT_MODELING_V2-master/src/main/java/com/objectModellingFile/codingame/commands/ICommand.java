package com.objectModellingFile.codingame.commands;

import java.util.List;

public interface ICommand {
    void execute(List<String> tokens);
}
