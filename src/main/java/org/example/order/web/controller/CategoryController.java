package org.example.order.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.order.web.payload.ApiResponse;
import org.example.order.web.payload.request.CategoryCreateReqDto;
import org.example.order.web.service.CategoryService;
import org.example.order.web.util.RestConstant;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @Tag(name = "Creating category ", description = "It is accessible for admin")
    @PostMapping(RestConstant.BASE_OPEN_APIS + "category/add")
    public HttpEntity<?> create(@RequestBody CategoryCreateReqDto categoryCreateReqDto) {
        ApiResponse response = categoryService.create(categoryCreateReqDto);
        return ResponseEntity.ok(response);
    }

    @Tag(name = "Getting one category by id ", description = "It is accessible for everyone")
    @GetMapping(RestConstant.BASE_OPEN_APIS + "category/get-one/{id}")
    public HttpEntity<?> getOneById(@PathVariable("id") Long id) {
        ApiResponse response = categoryService.getOneById(id);
        return ResponseEntity.ok(response);
    }

    @Tag(name = "Getting all categories ", description = "It is accessible for everyone")
    @GetMapping(RestConstant.BASE_OPEN_APIS + "category/get-all")
    public HttpEntity<?> getAll() {
        ApiResponse response = categoryService.getAll();
        return ResponseEntity.ok(response);
    }

    @Tag(name = "Updating category ", description = "It is accessible for admin")
    @PutMapping(RestConstant.BASE_SECURE_PATH + "category/update")
    public HttpEntity<?> update(@RequestBody CategoryCreateReqDto categoryCreateReqDto) {
        ApiResponse response = categoryService.update(categoryCreateReqDto);
        return ResponseEntity.ok(response);
    }

    @Tag(name = "Deleting category by id ", description = "It is accessible for admin")
    @DeleteMapping(RestConstant.BASE_OPEN_APIS + "category/delete/{id}")
    public HttpEntity<?> delete(@PathVariable("id") Long id) {
        ApiResponse response = categoryService.delete(id);
        return ResponseEntity.ok(response);
    }
}
