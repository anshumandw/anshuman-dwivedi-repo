package com.geektrust.doremi.exceptions;

public class DuplicateCategoryException extends RuntimeException{
    
  public DuplicateCategoryException() {
    super();
  }

  public DuplicateCategoryException(String msg) {
    super(msg);
  }

}
