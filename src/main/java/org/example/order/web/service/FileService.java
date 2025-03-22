package org.example.order.web.service;

import org.example.order.web.entity.Attachment;
import org.example.order.web.exception.NotFoundException;
import org.example.order.web.exception.NotSupportedFile;
import org.example.order.web.exception.OtherException;
import org.example.order.web.mapper.FileMapper;
import org.example.order.web.payload.ApiResponse;
import org.example.order.web.payload.response.AttachmentResDto;
import org.example.order.web.repository.AttachmentRepository;
import org.example.order.web.util.BaseUtils;
import org.example.order.web.util.messages.error.ErrorMessageKey;
import org.example.order.web.util.messages.info.MessageInfo;
import org.example.order.web.util.messages.info.MessageInfoKey;
import org.hashids.Hashids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


@Service
public class FileService {

  private final MessageInfo messageInfo;
  Logger LOGGER = LoggerFactory.getLogger(FileService.class);

  @Value("${file.upload.folder}")
  private String uploadFolderPath;

  private final AttachmentRepository attachmentRepository;
  private final BaseUtils utils;
  private final Hashids hashids;
  private final FileMapper fileMapper;

  public FileService(AttachmentRepository attachmentRepository, BaseUtils utils, FileMapper fileMapper, MessageInfo messageInfo) {
    this.attachmentRepository = attachmentRepository;
    this.utils = utils;
    this.fileMapper = fileMapper;
    this.hashids = new Hashids(getClass().getName(), 12);
    this.messageInfo = messageInfo;
  }

  public boolean isValidFile(MultipartFile multipartFile) {
    String contentType = multipartFile.getContentType();

    assert contentType != null;
    return contentType.equals("image/jpeg") || contentType.equals("image/png")
      || contentType.equals("text/plain") || contentType.equals("application/pdf")
      || contentType.equals("image/gif") || contentType.equals("application/zip")
      || contentType.equals("application/vnd.malformations-office document.spreadsheet.sheet")
      || contentType.equals("video/mp4") || contentType.equals("video/ogg")
      || contentType.equals("audio/mpeg") || contentType.equals("audio/ogg")
      || contentType.equals("audio/mp3") || contentType.equals("audio/m4a");
  }


  /// Receive files and save in multiple places
  public ApiResponse upload(MultipartFile multipartFile) throws IOException {
    if (!isValidFile(multipartFile)) {
      throw new NotSupportedFile(ErrorMessageKey.NOT_SUPPORTED_FILE);
    }
    Attachment attachment = storeFileToLocalMemory(multipartFile);

    return new ApiResponse(
      messageInfo.getMessage(MessageInfoKey.FILE_SAVED_SUCCESSFULLY),
      true,
      new AttachmentResDto(attachment.getId(), attachment.getFileName())
    );
  }


  private Attachment storeFileToLocalMemory(MultipartFile multipartFile) throws IOException {
    Path uploadPath = Paths.get(uploadFolderPath);
    if (!Files.exists(uploadPath)) {
      Files.createDirectories(uploadPath);
    }

    String filename = multipartFile.getOriginalFilename();
    assert filename != null;
    filename = generateUniqueFileName(filename);

    Path filePath = uploadPath.resolve(filename);

    try (InputStream inputStream = multipartFile.getInputStream()) {
      Files.copy(inputStream, filePath);
    } catch (IOException e) {
      throw new OtherException(ErrorMessageKey.NOT_SUPPORTED_FILE);
    }
    return save(multipartFile, String.valueOf(filePath), filename);

  }


  /// Save attachment to repository
  private Attachment save(MultipartFile multipartFile, String filePath, String fileName) {
    Attachment attachment = new Attachment();
    attachment.setFileSize(multipartFile.getSize());
    attachment.setExtension(multipartFile.getOriginalFilename());
    attachment.setContentType(multipartFile.getContentType());
    attachment.setFilePath(filePath);
    attachment.setFileName(fileName);


    return attachmentRepository.save(attachment);
  }


  /// To make unique generate name for file by adding this time
  private String generateUniqueFileName(String originalFileName) {
    String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
    return timestamp + fileExtension;
  }


  /// Get FileSystemResource to show image
  public Resource getFile(String name) {
    Path filePath = Paths.get(uploadFolderPath).resolve(name).normalize();
    try {
      Resource resource = new UrlResource(filePath.toUri());
      return resource;
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  /// Find Attachment by id
  public Attachment findAttachmentById(Long id) {
    Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
    if (optionalAttachment.isEmpty()) {
      throw new NotFoundException(ErrorMessageKey.NOT_FOUND_ATTACHMENT);
    }
    return optionalAttachment.get();
  }


}
