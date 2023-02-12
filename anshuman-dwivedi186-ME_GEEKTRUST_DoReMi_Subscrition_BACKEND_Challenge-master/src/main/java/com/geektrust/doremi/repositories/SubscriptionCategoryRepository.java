package com.geektrust.doremi.repositories;

import com.geektrust.doremi.exceptions.DuplicateCategoryException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscriptionCategoryRepository implements ISubscriptionCategoryRepository {
    
  private Map<String, Integer> categoryRepository = new HashMap<>();
  private List<String> categoryList = new ArrayList<>();
  private ISubscriptionRepository subscriptionRepository;

  public SubscriptionCategoryRepository(ISubscriptionRepository subscriptionRepository) {
    this.subscriptionRepository = subscriptionRepository;
  }

  @Override
  public void save(String category) {
    if (categoryRepository.containsKey(category)) {
      throw new DuplicateCategoryException("ADD_SUBSCRIPTION_FAILED DUPLICATE_CATEGORY");
    } else {
      categoryRepository.put(category, subscriptionRepository.count());
    }
  }

  @Override
  public String findById(Integer id) {
    return null;
  }

  @Override
  public void delete(String entity) {
         
  }

  @Override
  public void deleteById(Integer id) {
  
  }

  @Override
  public Integer count() {
    return categoryRepository.size();
  }

  @Override
  public List<String> findAll() {
    return null;
  }

  @Override
  public void saveCategories(String category) {
    categoryList.add(category);
  }

  @Override
  public void makeListEmpty() {
    categoryList.clear();
  }

  @Override
  public List<String> getCategories() {
    return categoryList;
  }
    
}
