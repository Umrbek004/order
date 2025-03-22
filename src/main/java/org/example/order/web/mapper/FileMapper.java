package org.example.order.web.mapper;


import org.example.order.web.entity.Attachment;
import org.example.order.web.payload.AttachmentDto;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {

    public AttachmentDto toDto(Attachment attachment) {
        AttachmentDto dto = new AttachmentDto();
        dto.setId(attachment.getId());
        dto.setFileName(attachment.getFileName());
        dto.setFileSize(attachment.getFileSize());
        dto.setContentType(attachment.getContentType());
        dto.setFilePath(attachment.getFilePath());
        dto.setExtension(attachment.getExtension());
        return dto;
    }
}
