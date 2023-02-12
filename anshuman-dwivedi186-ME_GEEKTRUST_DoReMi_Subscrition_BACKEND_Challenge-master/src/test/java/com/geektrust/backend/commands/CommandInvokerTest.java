package com.geektrust.backend.commands;

import static org.mockito.ArgumentMatchers.anyList;

import com.geektrust.doremi.commands.AddSubscriptionCommand;
import com.geektrust.doremi.commands.AddTopUpCommand;
import com.geektrust.doremi.commands.CommandInvoker;
import com.geektrust.doremi.commands.PrintRenewalDetailsCommand;
import com.geektrust.doremi.commands.StartSubscriptionCommand;
import com.geektrust.doremi.exceptions.NoSuchCommandException;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("CommandInvokerTest")
@ExtendWith(MockitoExtension.class)
public class CommandInvokerTest {
  private CommandInvoker commandInvoker;

  @Mock
  StartSubscriptionCommand startSubscriptionCommand;

  @Mock
   AddSubscriptionCommand addSubscriptionCommand;

  @Mock
  AddTopUpCommand addTopUpCommand;

  @Mock
  PrintRenewalDetailsCommand printRenewalDetailsCommand;

  @BeforeEach
  void setup() {
    commandInvoker = new CommandInvoker();
    commandInvoker.register("START_SUBSCRIPTION", startSubscriptionCommand);
    commandInvoker.register("ADD_SUBSCRIPTION", addSubscriptionCommand);
    commandInvoker.register("ADD_TOPUP", addTopUpCommand);
    commandInvoker.register("PRINT_RENEWAL_DETAILS", printRenewalDetailsCommand);
  }

  @Test
  @DisplayName("executeCommand method Should Execute Command Given CommandName and List of tokens")
  public void executeCommand_GivenNameAndTokens_ShouldExecuteCommand() {
    commandInvoker.executeCommand("START_SUBSCRIPTION", anyList());
    commandInvoker.executeCommand("ADD_SUBSCRIPTION", anyList());
    commandInvoker.executeCommand("ADD_TOPUP", anyList());
    commandInvoker.executeCommand("PRINT_RENEWAL_DETAILS", anyList());
  }

  @Test
  @DisplayName("executeCommand method Should Throw Exception Given Incorrect CommandName and List of tokens")
  public void executeCommand_GivenIncorrectNameAndTokens_ShouldThrowException() {
    Assertions.assertThrows(NoSuchCommandException.class,() -> commandInvoker
              .executeCommand("RANDOM-COMMAND", new ArrayList<String>()));

  }

    
}
