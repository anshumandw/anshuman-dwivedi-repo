package com.geektrust.backend.commands;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.geektrust.doremi.commands.PrintRenewalDetailsCommand;
import com.geektrust.doremi.repositories.SubscriptionCategoryRepository;
import com.geektrust.doremi.repositories.SubscriptionRepository;
import com.geektrust.doremi.services.SubscriptionCategoryService;
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

@DisplayName("PrintRenewalCommand_Test")
@ExtendWith(MockitoExtension.class)
public class PrintRenewalDetailsCommandTest {
  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @Mock
  SubscriptionCategoryRepository subscriptionCategoryRepositoryMock;

  @Mock
  SubscriptionRepository subscriptionRepositoryMock;

  @Mock
  SubscriptionCategoryService subscriptionCategoryServiceMock;

  @InjectMocks
  PrintRenewalDetailsCommand printRenewalDetailsCommand;

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  @DisplayName("execute method of printRenewalCommand print errorMsg when SubscriptionNotFound")
  public void executeMethodShouldPrintErrorWhenSubscriptionNotFound() {
    
    Integer userId = 0;
    Integer categoryValue = 0;

    when(subscriptionRepositoryMock.count()).thenReturn(userId);
    when(subscriptionCategoryRepositoryMock.count()).thenReturn(categoryValue);
    printRenewalDetailsCommand.execute(List.of("PRINT_RENEWAL_DETAILS"));
    String expectedOutput = "SUBSCRIPTIONS_NOT_FOUND";
    
    Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    verify(subscriptionCategoryRepositoryMock, times(1)).count();
  }

  @Test
  @DisplayName("execute method of printRenewalCommand printing output as expected")
  public void executeMethodPrintingOutputAsExpected() {

    List<String> dateList = List.of("15-10-2021", "15-10-2021", "15-08-2021");
    List<String> categoryList = List.of("MUSIC", "VIDEO", "PODCAST");
    
    Integer userId = 1;
    Integer categoryValue = 3;
    Integer price = 750;

    when(subscriptionRepositoryMock.count()).thenReturn(userId);
    when(subscriptionCategoryRepositoryMock.count()).thenReturn(categoryValue);
    when(subscriptionCategoryServiceMock.getDateList(anyInt())).thenReturn(dateList);
    when(subscriptionCategoryRepositoryMock.getCategories()).thenReturn(categoryList);
    when(subscriptionCategoryServiceMock.getPrice(anyInt())).thenReturn(price);
    printRenewalDetailsCommand.execute(List.of("PRINT_RENEWAL_DETAILS"));
    String expectedOutput = "";

    for (int i = 0; i < dateList.size(); i++) {
      expectedOutput += "RENEWAL_REMINDER " + categoryList.get(i) + " " + dateList.get(i) + "\n";
    }

    expectedOutput += "RENEWAL_AMOUNT " + price;
    
    Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    verify(subscriptionCategoryRepositoryMock, times(1)).count();
  }

  @AfterEach
  public void tearDown() {
    System.setOut(standardOut);
  }  

}
