package com.geektrust.doremi.services;

import com.geektrust.doremi.repositories.ISubscriptionRepository;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SubscriptionCategoryService implements ISubscriptionCategoryService {
   
  private Map<Integer, Integer> priceMap = new HashMap<>();
  private Map<Integer, List<String>> dateMap = new HashMap<>();
  private ISubscriptionRepository subscriptionRepository;

  public SubscriptionCategoryService(ISubscriptionRepository subscriptionRepository) {
    this.subscriptionRepository = subscriptionRepository;
  }

  @Override
  public void setPriceCategory(Integer userId, String category, String planType) {

    if (priceMap.get(userId) == null) {
      priceMap.put(userId, 0);
    }
        
    int price = 0;
    switch (category) {
      case "MUSIC":
        price = (planType.equals("PERSONAL")) ? 100 : planType.equals("PREMIUM") ? 250 : 0;
        priceMap.put(userId, priceMap.get(userId) + price);
        break;
      case "VIDEO":
        price = (planType.equals("PERSONAL")) ? 200 : planType.equals("PREMIUM") ? 500 : 0;
        priceMap.put(userId, priceMap.get(userId) + price);
        break;
      case "PODCAST": 
        price = (planType.equals("PERSONAL")) ? 100 : planType.equals("PREMIUM") ? 300 : 0;
        priceMap.put(userId, priceMap.get(userId) + price);
        break;
      default:
        price = 0;
    }
  }

  @Override
  public void setDateCategory(Integer userId, String planType) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate dateParse = subscriptionRepository.findById(userId);        
    Month month = dateParse.getMonth();
    boolean b = dateParse.isLeapYear();
    boolean subscriptionPlan = (planType.equals("FREE") || planType.equals("PERSONAL"));
    int monthValue = month.length(b);

    setDateToMap(monthValue, dateParse, subscriptionPlan, userId, formatter);

  }

  /**
* Some javadoc.
*/
  public void setDateToMap(int monthValue, LocalDate dateParse, 
      boolean subscriptionPlan, Integer userId, DateTimeFormatter formatter) {

    String answer = "";
    switch (monthValue) {
      case 28: 
        answer = (subscriptionPlan)  
          ? formatter.format(dateParse.plusDays(18)) : formatter.format(dateParse.plusDays(79));
        storingDateIntoMap(answer, userId);
        break;

      case 29:
        answer = (subscriptionPlan) 
          ? formatter.format(dateParse.plusDays(19)) : formatter.format(dateParse.plusDays(80));
        storingDateIntoMap(answer, userId);
        break;

      case 30:
        answer = (subscriptionPlan) 
          ? formatter.format(dateParse.plusDays(20)) : formatter.format(dateParse.plusDays(81));
        storingDateIntoMap(answer, userId);
        break;

      case 31:
        answer = (subscriptionPlan)  
          ? formatter.format(dateParse.plusDays(21)) : formatter.format(dateParse.plusDays(82));
        storingDateIntoMap(answer, userId);
        break;       

      default:
        answer = "";
    }

  }

  /**
* Some javadoc.
*/
  public void storingDateIntoMap(String answer, Integer userId) {
    if (dateMap.containsKey(userId)) {
      List<String> list = dateMap.get(userId);
      list.add(answer);
      dateMap.put(userId, list);
    } else {
      List<String> list = new ArrayList<>();
      list.add(answer);
      dateMap.put(userId, list);
    }
  }

  @Override
  public List<String> getDateList(Integer userId) {
    return dateMap.get(userId);
  }

  @Override
  public Integer getPrice(Integer userId) {
    return priceMap.get(userId);
  }

  @Override
  public Map<Integer, Integer> getPriceMap() {    
    return priceMap;
  }

  @Override
  public void setPriceMap(Map<Integer, Integer> priceMap) {
    this.priceMap = priceMap;    
  }
    
}
