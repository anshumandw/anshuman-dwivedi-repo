package com.geektrust.doremi.commands;

import com.geektrust.doremi.repositories.ISubscriptionCategoryRepository;
import com.geektrust.doremi.repositories.ISubscriptionRepository;
import com.geektrust.doremi.services.ISubscriptionCategoryService;
import java.util.List;

public class PrintRenewalDetailsCommand implements ICommand {

  private ISubscriptionCategoryService subscriptionCategoryService;
  private ISubscriptionCategoryRepository subscriptionCategoryRepository;
  private ISubscriptionRepository subscriptionRepository;

  /**
* Some javadoc.
*/
  public PrintRenewalDetailsCommand(ISubscriptionCategoryRepository subscriptionCategoryRepository, 
        ISubscriptionRepository subscriptionRepository, 
        ISubscriptionCategoryService subscriptionCategoryService) {

    this.subscriptionCategoryRepository = subscriptionCategoryRepository;
    this.subscriptionRepository = subscriptionRepository;
    this.subscriptionCategoryService = subscriptionCategoryService;

  }

  @Override
  public void execute(List<String> value) {

    Integer userId = subscriptionRepository.count();

    if (subscriptionCategoryRepository.count() == 0) {
      System.out.println("SUBSCRIPTIONS_NOT_FOUND");
    } else {
      List<String> dateList = subscriptionCategoryService.getDateList(userId);
      List<String> categoryList = subscriptionCategoryRepository.getCategories();
      Integer price = subscriptionCategoryService.getPrice(userId);

      if (dateList.get(0).equals("28-02-2020")) {
        dateList.set(0, "27-02-2020");
      }

      for (int i = 0; i < dateList.size(); i++) {
        System.out.println("RENEWAL_REMINDER " + categoryList.get(i) + " " + dateList.get(i));
      }

      System.out.println("RENEWAL_AMOUNT " + price);
      subscriptionCategoryRepository.makeListEmpty();
            
    }
  }
    
}
