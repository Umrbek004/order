package org.example.order.web.util.messages.info;

import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class MessageInfo {

  public String getMessage(String keyMessage) {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("message", Locale.of("uz"));
    return resourceBundle.getString(keyMessage);
  }


}
