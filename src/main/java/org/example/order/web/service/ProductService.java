package org.example.order.web.service;

import lombok.RequiredArgsConstructor;
import org.example.order.web.entity.Product;
import org.example.order.web.exception.NotFoundException;
import org.example.order.web.mapper.ProductMapper;
import org.example.order.web.payload.ApiResponse;
import org.example.order.web.payload.request.ProductCreateReqDto;
import org.example.order.web.repository.ProductRepository;
import org.example.order.web.util.messages.error.ErrorMessageKey;
import org.example.order.web.util.messages.error.ErrorMessageService;
import org.example.order.web.util.messages.info.MessageInfo;
import org.example.order.web.util.messages.info.MessageInfoKey;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final MessageInfo messageInfo;
    private final ErrorMessageService errorMessageService;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ApiResponse create(ProductCreateReqDto productCreateReqDto) {
        Product product = new Product();
        productMapper.toProduct(product, productCreateReqDto);
        productRepository.save(product);
        return new ApiResponse(messageInfo.getMessage(MessageInfoKey.PRODUCT_SAVED_SUCCESSFULLY), true, product);
    }

    public ApiResponse getSingle(Long id) {
        Product product = getProductById(id);
        return ApiResponse.builder().data(product).status(true).build();
    }

    public ApiResponse getAll() {
        List<Product> products = productRepository.findAllByDeletedFalse();
        if (products.isEmpty()) {
            throw new NotFoundException(messageInfo.getMessage(ErrorMessageKey.PRODUCT_NOT_FOUND));
        }
        return ApiResponse.builder().data(products).status(true).build();
    }

    public ApiResponse delete(Long id) {
        Product product = getProductById(id);
        product.setDeleted(true);
        productRepository.save(product);
        return new ApiResponse(messageInfo.getMessage(MessageInfoKey.PRODUCT_DELETED_SUCCESSFULLY), true, product);
    }

    public ApiResponse update(ProductCreateReqDto productCreateReqDto) {
        Product product = getProductById(productCreateReqDto.id());
        productMapper.toProduct(product, productCreateReqDto);
        productRepository.save(product);
        return ApiResponse.builder().data(product).status(true).message(messageInfo.getMessage(MessageInfoKey.PRODUCT_UPDATE_SUCCESSFULLY)).build();
    }


    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessageKey.PRODUCT_NOT_FOUND));
    }
}
