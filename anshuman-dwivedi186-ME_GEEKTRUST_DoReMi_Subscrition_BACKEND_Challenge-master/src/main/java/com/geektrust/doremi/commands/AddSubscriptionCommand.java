package com.geektrust.doremi.commands;

import com.geektrust.doremi.exceptions.DuplicateCategoryException;
import com.geektrust.doremi.repositories.ISubscriptionCategoryRepository;
import com.geektrust.doremi.repositories.ISubscriptionRepository;
import com.geektrust.doremi.services.ISubscriptionCategoryService;
import java.util.List;

public class AddSubscriptionCommand implements ICommand {

  private ISubscriptionRepository subscriptionRepository;
  private ISubscriptionCategoryRepository subscriptionCategoryRepository;
  private ISubscriptionCategoryService subscriptionCategoryService;

  /**
* Some javadoc.
*/
  public AddSubscriptionCommand(ISubscriptionRepository subscriptionRepository, 
      ISubscriptionCategoryRepository subscriptionCategoryRepository, 
      ISubscriptionCategoryService subscriptionCategoryService) {

    this.subscriptionRepository = subscriptionRepository;
    this.subscriptionCategoryRepository = subscriptionCategoryRepository;
    this.subscriptionCategoryService = subscriptionCategoryService;

  }

  @Override
    public void execute(List<String> value) {  

    if (subscriptionRepository.count() == 0) {
      System.out.println("ADD_SUBSCRIPTION_FAILED INVALID_DATE");
    } else {        

      try {
        
        subscriptionCategoryRepository.save(value.get(1));
        subscriptionCategoryRepository.saveCategories(value.get(1));
        subscriptionCategoryService.setPriceCategory(subscriptionRepository.count(), 
            value.get(1), value.get(2));
        subscriptionCategoryService.setDateCategory(subscriptionRepository.count(), value.get(2));


      } catch (DuplicateCategoryException e) {
        System.out.println("ADD_SUBSCRIPTION_FAILED DUPLICATE_CATEGORY");
      }

    }

  }
    
}
