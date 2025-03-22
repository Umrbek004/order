package org.example.order.web.util;

import org.example.order.web.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserSession {


  public User getUser() {
    User user = new User();
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.getPrincipal() instanceof User) {
      user = (User) authentication.getPrincipal();
    }
    return user;
  }
}
