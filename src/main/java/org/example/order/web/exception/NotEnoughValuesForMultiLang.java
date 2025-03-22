package org.example.order.web.exception;

public class NotEnoughValuesForMultiLang extends RuntimeException {
  String message;

  @Override
  public String getMessage() {
    return message;
  }

  public NotEnoughValuesForMultiLang(String message) {
    this.message = message;
  }
}
