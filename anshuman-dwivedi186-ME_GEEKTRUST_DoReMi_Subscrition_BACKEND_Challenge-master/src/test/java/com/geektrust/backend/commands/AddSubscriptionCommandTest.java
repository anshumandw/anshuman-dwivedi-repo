package com.geektrust.backend.commands;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.geektrust.doremi.commands.AddSubscriptionCommand;
import com.geektrust.doremi.exceptions.DuplicateCategoryException;
import com.geektrust.doremi.repositories.ISubscriptionCategoryRepository;
import com.geektrust.doremi.repositories.ISubscriptionRepository;
import com.geektrust.doremi.services.ISubscriptionCategoryService;
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

@DisplayName("ADD_Subscription_Test")
@ExtendWith(MockitoExtension.class)
public class AddSubscriptionCommandTest {

  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
  @Mock
  ISubscriptionRepository subscriptionRepositoryMock;

  @Mock
  ISubscriptionCategoryRepository subscriptionCategoryRepositoryMock;

  @Mock
  ISubscriptionCategoryService subscriptionCategoryServiceMock;

  @InjectMocks
  AddSubscriptionCommand addSubscriptionMock;

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  @DisplayName("execute method of AddSubscription should print error if there is invalid date")
  public void printInvalidDateErrorWhenAddSubscriptionIsCalled() {

    Integer userID = 0;
    String expectedOutput = "ADD_SUBSCRIPTION_FAILED INVALID_DATE";
        
    when(subscriptionRepositoryMock.count()).thenReturn(userID);
    addSubscriptionMock.execute(List.of("ADD_SUBSCRIPTION","MUSIC", "FREE"));

    Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    verify(subscriptionRepositoryMock, times(1)).count();

  }

  @Test  
  @DisplayName("execute method of AddSubscription print error msg if given duplicate category")
  public void execute_ShouldPrintErrorMessage_GivenDuplicateCategory() {

    Integer userID = 1;
    String category = "MUSIC";

    when(subscriptionRepositoryMock.count()).thenReturn(userID);
    doThrow(new DuplicateCategoryException())
        .when(subscriptionCategoryRepositoryMock)
        .save(category);

    addSubscriptionMock.execute(List.of("ADD_SUBSCRIPTION", "MUSIC", "PREMIUM"));

    String expectedOutput = "ADD_SUBSCRIPTION_FAILED DUPLICATE_CATEGORY";
    Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    verify(subscriptionCategoryRepositoryMock, times(1)).save(anyString());;

  }

  @AfterEach
  public void tearDown() {
    System.setOut(standardOut);
  }

}
