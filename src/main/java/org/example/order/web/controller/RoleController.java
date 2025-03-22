package org.example.order.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.order.web.payload.ApiResponse;
import org.example.order.web.service.RoleService;
import org.example.order.web.util.RestConstant;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    @Tag(name = "Getting all users ", description = "It is accessible for admin")
    @GetMapping(RestConstant.BASE_OPEN_APIS + "role/get-all")
    public HttpEntity<?> getAll() {
        ApiResponse response = roleService.getAll();
        return ResponseEntity.ok(response);
    }
}
