package org.example.order.web.exception;

public class WrongNumber extends RuntimeException {

  String message;

  @Override
  public String getMessage() {
    return message;
  }

  public WrongNumber(String message) {
    this.message = message;
  }
}
