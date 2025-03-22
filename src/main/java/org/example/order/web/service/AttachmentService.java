package org.example.order.web.service;

import org.example.order.web.entity.Attachment;
import org.example.order.web.exception.NotFoundException;
import org.example.order.web.repository.AttachmentRepository;
import org.example.order.web.util.messages.error.ErrorMessageKey;
import org.example.order.web.util.messages.error.ErrorMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final ErrorMessageService errorMessageService;
    private static final String BASE_URL = "https://yourdomain.com/api/attachments/";

    public String getImageUrl(String fileName) {
        return BASE_URL + fileName; // Fayl nomi kengaytmasi bilan qaytariladi
    }

    public List<String> getImageUrlProducts(List<String> fileNames) {
        ArrayList<String> fileNamesList = new ArrayList<>();
        for (String fileName : fileNames) {
            fileNamesList.add(BASE_URL + fileName);
        }
        return fileNamesList;
    }

    public List<Attachment> attachmentList(List<Long> imagesId) {
        List<Attachment> attachments = new ArrayList<>();
        for (Long aLong : imagesId) {
            Attachment attachment = attachmentRepository.findById(aLong).orElseThrow(
                    () -> new NotFoundException(errorMessageService.getMessage(ErrorMessageKey.NOT_FOUND_ATTACHMENT)));
            attachments.add(attachment);
        }
        return attachments;
    }

    public Attachment attachment(Long id) {
        return attachmentRepository.findById(id).orElseThrow(
                () -> new NotFoundException(errorMessageService.getMessage(ErrorMessageKey.NOT_FOUND_ATTACHMENT))
        );
    }
}
