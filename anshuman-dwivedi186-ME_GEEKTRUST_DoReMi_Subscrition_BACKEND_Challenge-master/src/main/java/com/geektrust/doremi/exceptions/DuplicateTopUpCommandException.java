package com.geektrust.doremi.exceptions;

public class DuplicateTopUpCommandException extends RuntimeException {
    
  public DuplicateTopUpCommandException() {
    super();
  }

  public DuplicateTopUpCommandException(String msg) {
    super(msg);
  }

}
