package com.geektrust.doremi.appconfig;

import com.geektrust.doremi.commands.AddSubscriptionCommand;
import com.geektrust.doremi.commands.AddTopUpCommand;
import com.geektrust.doremi.commands.CommandInvoker;
import com.geektrust.doremi.commands.PrintRenewalDetailsCommand;
import com.geektrust.doremi.commands.StartSubscriptionCommand;
import com.geektrust.doremi.repositories.ISubscriptionCategoryRepository;
import com.geektrust.doremi.repositories.ISubscriptionRepository;
import com.geektrust.doremi.repositories.SubscriptionCategoryRepository;
import com.geektrust.doremi.repositories.SubscriptionRepository;
import com.geektrust.doremi.services.ISubscriptionCategoryService;
import com.geektrust.doremi.services.SubscriptionCategoryService;

public class ApplicationConfig {

  private final ISubscriptionRepository subscriptionRepository = new SubscriptionRepository();
  private final ISubscriptionCategoryRepository subscriptionCategoryRepository = 
      new SubscriptionCategoryRepository(subscriptionRepository);
  
  private final ISubscriptionCategoryService subscriptionCategoryService = 
      new SubscriptionCategoryService(subscriptionRepository);

  private final StartSubscriptionCommand startSubscriptionCommand = 
      new StartSubscriptionCommand(subscriptionRepository);

  private final AddSubscriptionCommand addSubscriptionCommand = 
      new AddSubscriptionCommand(subscriptionRepository, 
      subscriptionCategoryRepository, subscriptionCategoryService);

  private final AddTopUpCommand addTopUpCommand = 
      new AddTopUpCommand(subscriptionCategoryRepository, 
      subscriptionCategoryService, subscriptionRepository);

  private final PrintRenewalDetailsCommand printRenewalDetailsCommand = 
      new PrintRenewalDetailsCommand(subscriptionCategoryRepository, 
      subscriptionRepository, subscriptionCategoryService);
    
  private final CommandInvoker commandInvoker = new CommandInvoker();

  /**
* Some javadoc.
*/
  public CommandInvoker getCommandInvoker() {

    commandInvoker.register("START_SUBSCRIPTION", startSubscriptionCommand);
    commandInvoker.register("ADD_SUBSCRIPTION", addSubscriptionCommand);
    commandInvoker.register("ADD_TOPUP", addTopUpCommand);
    commandInvoker.register("PRINT_RENEWAL_DETAILS", printRenewalDetailsCommand);
        
    return commandInvoker;
  }

}
