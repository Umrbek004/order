package org.example.order.web.service;

import lombok.RequiredArgsConstructor;
import org.example.order.web.entity.Category;
import org.example.order.web.exception.NotFoundException;
import org.example.order.web.mapper.CategoryMapper;
import org.example.order.web.payload.ApiResponse;
import org.example.order.web.payload.request.CategoryCreateReqDto;
import org.example.order.web.repository.CategoryRepository;
import org.example.order.web.util.messages.error.ErrorMessageKey;
import org.example.order.web.util.messages.error.ErrorMessageService;
import org.example.order.web.util.messages.info.MessageInfo;
import org.example.order.web.util.messages.info.MessageInfoKey;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    private final MessageInfo messageInfo;
    private final ErrorMessageService errorMessageService;

    public ApiResponse create(CategoryCreateReqDto categoryCreateReqDto) {
        Category category = new Category();
        categoryMapper.toCategory(category, categoryCreateReqDto);
        categoryRepository.save(category);
        return new ApiResponse(
                messageInfo.getMessage(MessageInfoKey.CATEGORY_SAVED_SUCCESSFULLY),
                true,
                category
        );
    }

    public ApiResponse getOneById(Long id) {
        Category category = getCategoryById(id);
//        CategoryResponseDto data = categoryMapper.toDto(category);

        return ApiResponse.builder().status(true).data(category).build();
    }


    public ApiResponse getAll() {
        List<Category> categories = categoryRepository.findAllByDeletedFalse();
        return ApiResponse.builder().status(true).data(categories).build();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(ErrorMessageKey.CATEGORY_NOT_FOUND));
    }

    public ApiResponse update(CategoryCreateReqDto categoryCreateReqDto) {
        Category category = getCategoryById(categoryCreateReqDto.id());
        categoryMapper.toCategory(category, categoryCreateReqDto);
        categoryRepository.save(category);
        return new ApiResponse(
                messageInfo.getMessage(MessageInfoKey.CATEGORY_UPDATED_SUCCESSFULLY),
                true,
                category
        );
    }

    public ApiResponse delete(Long id) {
        Category category = getCategoryById(id);
        category.setDeleted(true);
        categoryRepository.save(category);
        return ApiResponse.builder().status(true).data(category).message(messageInfo.getMessage(MessageInfoKey.CATEGORY_DELETED_SUCCESSFULLY)).build();
    }
}
