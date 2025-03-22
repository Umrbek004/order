package org.example.order.web.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OtherException extends RuntimeException {
  private String message;

  public OtherException(String message) {
  }

}
