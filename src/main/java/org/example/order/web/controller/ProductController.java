package org.example.order.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.order.web.payload.ApiResponse;
import org.example.order.web.payload.request.CategoryCreateReqDto;
import org.example.order.web.payload.request.ProductCreateReqDto;
import org.example.order.web.service.ProductService;
import org.example.order.web.util.RestConstant;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @Tag(name = "Creating product ", description = "It is accessible for admin")
    @PostMapping(RestConstant.BASE_OPEN_APIS + "product/add")
    public HttpEntity<?> create(@RequestBody ProductCreateReqDto productCreateReqDto) {
        ApiResponse response = productService.create(productCreateReqDto);
        return ResponseEntity.ok(response);
    }

    @Tag(name = "Getting single product by id", description = "It is accessible for everone")
    @GetMapping(RestConstant.BASE_OPEN_APIS + "product/get-one/{id}")
    public HttpEntity<?> getSingleById(@PathVariable Long id) {
        ApiResponse response = productService.getSingle(id);
        return ResponseEntity.ok(response);
    }

    @Tag(name = "Getting all products", description = "It is accessible for everyone")
    @GetMapping(RestConstant.BASE_OPEN_APIS + "product/get-all")
    public HttpEntity<?> getAll() {
        ApiResponse response = productService.getAll();
        return ResponseEntity.ok(response);
    }

    @Tag(name = "Deleting product by id", description = "It is accessible for admin")
    @DeleteMapping(RestConstant.BASE_OPEN_APIS + "product/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {
        ApiResponse response = productService.delete(id);
        return ResponseEntity.ok(response);
    }

    @Tag(name = "Deleting product by id", description = "It is accessible for admin")
    @PutMapping(RestConstant.BASE_OPEN_APIS + "product/update")
    public HttpEntity<?> update(@RequestBody ProductCreateReqDto productCreateReqDto) {
        ApiResponse response = productService.update(productCreateReqDto);
        return ResponseEntity.ok(response);
    }
}
