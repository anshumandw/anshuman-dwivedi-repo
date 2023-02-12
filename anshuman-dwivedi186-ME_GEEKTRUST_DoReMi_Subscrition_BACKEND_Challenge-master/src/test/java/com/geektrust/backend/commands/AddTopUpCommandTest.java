package com.geektrust.backend.commands;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.geektrust.doremi.commands.AddTopUpCommand;
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

@DisplayName("Add_Top_up_Command_Test")
@ExtendWith(MockitoExtension.class)
public class AddTopUpCommandTest {
  
  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
  @Mock
  ISubscriptionRepository subscriptionRepositoryMock;

  @Mock
  ISubscriptionCategoryRepository subscriptionCategoryRepositoryMock;

  @Mock
  ISubscriptionCategoryService subscriptionCategoryServiceMock;

  @InjectMocks
  AddTopUpCommand addTopUpMock;

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  @DisplayName("execute method of AddTopUpCommand should print error if there is invalid date")
  public void printInvalidDateErrorWhenAddSubscriptionIsCalled() {

    Integer userID = 0;
    String expectedOutput = "ADD_TOPUP_FAILED INVALID_DATE";
        
    when(subscriptionRepositoryMock.count()).thenReturn(userID);
    addTopUpMock.execute(List.of("ADD_TOPUP","TEN_DEVICE", "4"));

    Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    verify(subscriptionRepositoryMock, times(1)).count();

  }

  // @Test  
  // @DisplayName("execute method of AddTopUpCommand print error msg if given duplicate category")
  // public void execute_ShouldPrintErrorMessage_GivenDuplicateCategory() {

  //   Integer userId = 1;
  //   Integer categoryCount = 1;

  //   when(subscriptionRepositoryMock.count()).thenReturn(userId);
  //   when(subscriptionCategoryRepositoryMock.count()).thenReturn(categoryCount);
  //   doThrow(new DuplicateTopUpCommandException())
  //       .when(subscriptionCategoryRepositoryMock)
  //       .save(category);

  //   addTopUpMock.execute(List.of("ADD_TOPUP", "FOUR_DEVICE", "2"));

  //   String expectedOutput = "ADD_TOPUP_FAILED DUPLICATE_CATEGORY";
  //   Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
  //   verify(subscriptionCategoryRepositoryMock, times(1)).save(anyString());;

  // }

  // @Test  
  // @DisplayName("execute method of AddTopUpCommand print error msg When Subscriptions Not Found")
  // public void execute_ShouldPrintErrorMessage_WhenSubscriptionsNotFound() {

  //   Integer userId = 1;
  //   Integer categoryCount = 0;

  //   when(subscriptionRepositoryMock.count()).thenReturn(userId);
  //   when(subscriptionCategoryRepositoryMock.count()).thenReturn(categoryCount);

  //   addTopUpMock.execute(List.of("ADD_TOPUP", "FOUR_DEVICE", "5"));

  //   String expectedOutput = "ADD_TOPUP_FAILED SUBSCRIPTIONS_NOT_FOUND";
  //   Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
  //   verify(subscriptionCategoryRepositoryMock, times(1)).save(anyString());;

  // }

  @AfterEach
  public void tearDown() {
    System.setOut(standardOut);
  }  
}
