package org.example.order.web.payload;

import java.sql.Timestamp;

public record ErrorResponse(
  String errorMessage,
  int status,
  Timestamp timestamp,
  String path
) {
}
