package org.example.order.web.payload;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

  private String message;

  private boolean status;

  private Object data;

}
