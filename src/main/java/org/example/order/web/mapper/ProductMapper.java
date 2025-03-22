package org.example.order.web.mapper;

import lombok.RequiredArgsConstructor;
import org.example.order.web.entity.Attachment;
import org.example.order.web.entity.Product;
import org.example.order.web.payload.request.ProductCreateReqDto;
import org.example.order.web.service.AttachmentService;
import org.example.order.web.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductMapper {
    private final AttachmentService attachmentService;
    private final CategoryService categoryService;

    public void toProduct(Product product, ProductCreateReqDto productCreateReqDto) {
        if (productCreateReqDto.name() != null) {
            product.setName(productCreateReqDto.name());
        }
        if (productCreateReqDto.description() != null) {
            product.setDescription(productCreateReqDto.description());
        }
        product.setPrice(productCreateReqDto.price());
        product.setDiscountPrice(productCreateReqDto.discountPrice());
        if (productCreateReqDto.images() != null) {
            product.setImages(attachmentService.attachmentList(productCreateReqDto.images()));
        } else {
            product.setImages(null);
        }
        product.setCategory(categoryService.getCategoryById(productCreateReqDto.categoryId()));
    }
}
