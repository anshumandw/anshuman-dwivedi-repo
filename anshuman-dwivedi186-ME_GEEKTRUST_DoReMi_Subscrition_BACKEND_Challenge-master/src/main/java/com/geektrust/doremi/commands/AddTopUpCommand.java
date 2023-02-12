package com.geektrust.doremi.commands;

import com.geektrust.doremi.exceptions.DuplicateTopUpCommandException;
import com.geektrust.doremi.repositories.ISubscriptionCategoryRepository;
import com.geektrust.doremi.repositories.ISubscriptionRepository;
import com.geektrust.doremi.services.ISubscriptionCategoryService;
import java.util.List;
import java.util.Map;

public class AddTopUpCommand implements ICommand {

  private ISubscriptionCategoryRepository subscriptionCategoryRepository;
  private ISubscriptionCategoryService subscriptionCategoryService;
  private ISubscriptionRepository subscriptionRepository;
  private Integer checkIfEqualToUserID = 1;

  /**
* Some javadoc.
*/
  public AddTopUpCommand(ISubscriptionCategoryRepository subscriptionCategoryRepository,
        ISubscriptionCategoryService subscriptionCategoryService, 
        ISubscriptionRepository subscriptionRepository) {

    this.subscriptionCategoryRepository = subscriptionCategoryRepository;
    this.subscriptionCategoryService = subscriptionCategoryService;
    this.subscriptionRepository = subscriptionRepository;

  }

  @Override
    public void execute(List<String> value) {

    Integer userId = subscriptionRepository.count();

    Map<Integer, Integer> map = subscriptionCategoryService.getPriceMap();

    if (userId == 0) {
            
      System.out.println("ADD_TOPUP_FAILED INVALID_DATE");

    } else if (subscriptionCategoryRepository.count() == 0) {

      System.out.println("ADD_TOPUP_FAILED SUBSCRIPTIONS_NOT_FOUND");

    } else if (value.get(1).equals("FOUR_DEVICE")) {

      int sum = 50 * Integer.parseInt(value.get(2));

      checkTopUpDuplication(map, sum, userId);

    } else {
                
      int sum = 100 * Integer.parseInt(value.get(2));

      checkTopUpDuplication(map, sum, userId);

    }

    subscriptionCategoryService.setPriceMap(map);
        
  }


  /**
* Some javadoc.
*/
  public void checkTopUpDuplication(Map<Integer, Integer> map, Integer sum, Integer userId) {
    if (map.containsKey(userId) && checkIfEqualToUserID == userId) {

      map.put(userId, map.get(userId) + sum);
      checkIfEqualToUserID++;

    } else {
      checkIfEqualToUserID = 1;
      try {
        throw new DuplicateTopUpCommandException();
      } catch (DuplicateTopUpCommandException e) {
        System.out.println("ADD_TOPUP_FAILED DUPLICATE_TOPUP");
      }
    }
  }

}

