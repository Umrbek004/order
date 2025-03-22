package org.example.order.web.controller;

import lombok.RequiredArgsConstructor;
import org.example.order.constants.RoleName;
import org.example.order.web.util.RestConstant;
import org.example.order.web.valid.CheckRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class HomeController {
    @GetMapping(RestConstant.BASE_OPEN_APIS + "home/open")
    public String home() {
        return "Hello Open World!";
    }

    @GetMapping(RestConstant.BASE_SECURE_PATH + "home/secure")
    @CheckRole({RoleName.ADMIN})
    public String homeSecure() {
        return "Hello Secure World!";
    }
}
