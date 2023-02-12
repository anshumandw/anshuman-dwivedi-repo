package com.geektrust.doremi.repositories;

import java.util.List;

public interface ISubscriptionCategoryRepository extends CRUDRepository<String, Integer> {

  public void saveCategories(String category);

  public List<String> getCategories();

  public void makeListEmpty();
  
}
