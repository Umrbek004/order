package org.example.order.web.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NotSupportedFile extends RuntimeException {
  private String message;

  @Override
  public String getMessage() {
    return message;
  }

  public NotSupportedFile(String message) {
    this.message = message;
  }
}
