package org.example.order.web.payload;

import org.example.order.web.entity.User;

public record AuthResponse(User user, String token) {
}
