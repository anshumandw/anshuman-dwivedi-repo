package com.geektrust.doremi.repositories;

import java.util.List;

public interface CRUDRepository<T, ID> {

  public void save(T entity);

  public T findById(Integer id);

  public List<T> findAll();

  public void delete(T entity);

  public void deleteById(Integer id);

  public Integer count();

}
