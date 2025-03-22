package org.example.order.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.order.web.payload.ApiResponse;
import org.example.order.web.payload.request.CategoryCreateReqDto;
import org.example.order.web.payload.request.UserCreateReqDto;
import org.example.order.web.service.UserService;
import org.example.order.web.util.RestConstant;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Tag(name = "Creating user ", description = "It is accessible for admin")
    @PostMapping(RestConstant.BASE_OPEN_APIS + "user/add")
    public HttpEntity<?> create(@RequestBody UserCreateReqDto userCreateReqDto) {
        ApiResponse response = userService.create(userCreateReqDto);
        return ResponseEntity.ok(response);
    }

    @Tag(name = "Getting one  user ", description = "It is accessible for admin")
    @GetMapping(RestConstant.BASE_OPEN_APIS + "user/get/{id}")
    public HttpEntity<?> getById(@PathVariable Long id) {
        ApiResponse response = userService.getById(id);
        return ResponseEntity.ok(response);
    }

    @Tag(name = "Getting all users ", description = "It is accessible for admin")
    @GetMapping(RestConstant.BASE_OPEN_APIS + "user/get-all")
    public HttpEntity<?> getAll() {
        ApiResponse response = userService.getAll();
        return ResponseEntity.ok(response);
    }

    @Tag(name = "Getting all users ", description = "It is accessible for admin")
    @PutMapping(RestConstant.BASE_OPEN_APIS + "user/update")
    public HttpEntity<?> update(@RequestBody UserCreateReqDto userCreateReqDto) {
        ApiResponse response = userService.update(userCreateReqDto);
        return ResponseEntity.ok(response);
    }

}
