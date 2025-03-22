package org.example.order.web.util.messages.error;

import lombok.RequiredArgsConstructor;
import org.example.order.web.util.UserSession;
import org.example.order.web.util.messages.TemplateMessageInt;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;

@RequiredArgsConstructor
@Component
public class ErrorMessageService implements TemplateMessageInt {


//  private final UserSession userSession;
  private final UserSession userSession;

  ///Get error message
  @Override
  public String getMessage(String keyMessage) {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("error_message", Locale.of("uz"));
    String message = resourceBundle.getString(keyMessage);
    return message;
  }
}
