package com.geektrust.doremi.commands;

import com.geektrust.doremi.repositories.ISubscriptionRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class StartSubscriptionCommand implements ICommand{

  private ISubscriptionRepository subscriptionRepository;

  public StartSubscriptionCommand(ISubscriptionRepository subscriptionRepository) {
    this.subscriptionRepository = subscriptionRepository;
  }

  @Override
  public void execute(List<String> value) {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String date  = value.get(1);
    
    try {
      LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
      subscriptionRepository.save(localDate);
    } catch (DateTimeParseException e) {
      System.out.println("INVALID_DATE");
    }

  }
    
}
