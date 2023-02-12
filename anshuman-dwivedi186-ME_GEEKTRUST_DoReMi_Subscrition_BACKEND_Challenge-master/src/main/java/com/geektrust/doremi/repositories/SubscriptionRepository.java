package com.geektrust.doremi.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscriptionRepository implements ISubscriptionRepository {
    
  private Map<Integer, LocalDate> subscriptionDate = new HashMap<>();
  private static Integer user_ID = 1;

  public Integer getUser_ID() {
    return user_ID;
  }

  @Override
  public void save(LocalDate date) {
    subscriptionDate.put(user_ID, date);
    ++user_ID;
  }

  @Override
  public LocalDate findById(Integer id) {
    return subscriptionDate.get(id);
  }

  /**
* Some javadoc.
*/
  public List<LocalDate> findAll() {
    List<LocalDate> dateList = new ArrayList<>();
    for (LocalDate date : subscriptionDate.values()) {
      dateList.add(date);
    }
    return dateList;
  }

  @Override
  public void delete(LocalDate entity) {
    return;
  }

  @Override
  public void deleteById(Integer id) {
    LocalDate val = subscriptionDate.get(id);
    subscriptionDate.remove(id, val);
  }

  @Override
  public Integer count() {
    return subscriptionDate.size();
  }

}
