package com.geektrust.backend;

import com.geektrust.doremi.App;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Integration Tests")
public class AppTest {
  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  @DisplayName("Integration Test #1")
  void runTest1() {

    String[] arguments = {"test_case_inputs/input1.txt"};

    String expectedOutput = "RENEWAL_REMINDER MUSIC 10-03-2022\n"
        + "RENEWAL_REMINDER VIDEO 10-05-2022\n"
        + "RENEWAL_REMINDER PODCAST 10-03-2022\n"
        + "RENEWAL_AMOUNT 750";
    
    App.main(arguments);
    Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
 
  }

  // @Test
  // @DisplayName("Integration Test #2")
  // void runTest2() {

  //   String[] arguments = {"test_case_inputs/input2.txt"};

  //   String expectedOutput = "ADD_SUBSCRIPTION_FAILED DUPLICATE_CATEGORY\n"
  //       + "ADD_TOPUP_FAILED DUPLICATE_TOPUP\n"
  //       + "RENEWAL_REMINDER MUSIC 10-11-2022\n"
  //       + "RENEWAL_AMOUNT 200";
    
  //   App.main(arguments);
  //   Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
 
  // }

  @Test
  @DisplayName("Integration Test #3")
  void runTest3() {

    String[] arguments = {"test_case_inputs/input3.txt"};

    String expectedOutput = "INVALID_DATE\n"
        + "ADD_SUBSCRIPTION_FAILED INVALID_DATE";
    
    App.main(arguments);
    Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
 
  }

  @Test
  @DisplayName("Integration Test #4")
  void runTest4() {

    String[] arguments = {"test_case_inputs/input4.txt"};

    String expectedOutput = "SUBSCRIPTIONS_NOT_FOUND";
    
    App.main(arguments);
    Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
 
  }

  @Test
  @DisplayName("Integration Test #5")
  void runTest7() {

    String[] arguments = {"test_case_inputs/input5.txt"};

    String expectedOutput = "ADD_TOPUP_FAILED SUBSCRIPTIONS_NOT_FOUND\n"
        + "SUBSCRIPTIONS_NOT_FOUND";
    
    App.main(arguments);
    Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
 
  }

  @AfterEach
  public void tearDown() {
    System.setOut(standardOut);
  }

}
