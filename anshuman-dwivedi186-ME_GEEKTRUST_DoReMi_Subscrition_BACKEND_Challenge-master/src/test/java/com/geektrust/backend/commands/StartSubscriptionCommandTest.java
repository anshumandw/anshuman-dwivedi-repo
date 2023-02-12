package com.geektrust.backend.commands;

import com.geektrust.doremi.commands.StartSubscriptionCommand;
import com.geektrust.doremi.repositories.ISubscriptionRepository;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Start_Subscription_Command_Test")
@ExtendWith(MockitoExtension.class)
public class StartSubscriptionCommandTest {

  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
  @Mock
  ISubscriptionRepository subscriptionRepositoryMock;

  @InjectMocks
  StartSubscriptionCommand startSubscriptionCommand;

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  @DisplayName("execute method of StartSubscription should print error if there is invalid date")
  public void printInvalidDateErrorWhenexecuteOfStartSubscriptionIsCalled() {

    List<String> values = List.of("START_SUBSCRIPTION","10-21-2022");

    startSubscriptionCommand.execute(List.of(values.get(0), values.get(1)));

    String expectedOutput = "INVALID_DATE";
    Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    //verify(subscriptionRepositoryMock, times(1)).count();

  }

  @AfterEach
  public void tearDown() {
    System.setOut(standardOut);
  }
  
}
