package org.example.order.web.controller;

import org.example.order.web.exception.UserNotException;
import org.example.order.web.payload.AuthRequest;
import org.example.order.web.payload.ResponseData;
import org.example.order.web.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.example.order.web.payload.ResponseData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    /**
     * This API is designed for authorizing User
     *
     * @param authRequest has 2 variable:
     *                    1-phoneNumber -> should be String
     *                    2-password -> should be String
     * @return
     */
    @PostMapping("/sign-in")
    private ResponseData<?> signIn(@RequestBody AuthRequest authRequest) throws UserNotException {
        return authService.authenticate(authRequest);
    }
}
