package org.example.order.web.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserNotException extends Exception {

  String message;

  @Override
  public String getMessage() {
    return this.message;
  }

}
