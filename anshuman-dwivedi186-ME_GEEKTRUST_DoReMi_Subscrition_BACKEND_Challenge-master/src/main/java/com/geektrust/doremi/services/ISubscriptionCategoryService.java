package com.geektrust.doremi.services;

import java.util.List;
import java.util.Map;

public interface ISubscriptionCategoryService {

  public void setPriceCategory(Integer userId, String category, String planType);

  public void setDateCategory(Integer userId, String planType);

  public Map<Integer, Integer> getPriceMap();

  public void setPriceMap(Map<Integer, Integer> priceMap);

  public List<String> getDateList(Integer userId);

  public Integer getPrice(Integer userId);

}
