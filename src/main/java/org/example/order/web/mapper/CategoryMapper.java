package org.example.order.web.mapper;

import lombok.RequiredArgsConstructor;
import org.example.order.web.entity.Attachment;
import org.example.order.web.entity.Category;
import org.example.order.web.payload.request.CategoryCreateReqDto;
import org.example.order.web.service.AttachmentService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryMapper {

    private final AttachmentService attachmentService;

    public void toCategory(Category category, CategoryCreateReqDto categoryCreateReqDto) {
        if (categoryCreateReqDto.name() != null)
            category.setName(categoryCreateReqDto.name());
        if (categoryCreateReqDto.description() != null)
            category.setDescription(categoryCreateReqDto.description());
        if (categoryCreateReqDto.imageId()!=null){
            Attachment attachment = attachmentService.attachment(categoryCreateReqDto.imageId());
            if (attachment != null)
                category.setImage(attachment);
        }else {
            category.setImage(null);
        }
    }

//    public CategoryResponseDto toDto(Category category) {
//        return null;
//    }
}
