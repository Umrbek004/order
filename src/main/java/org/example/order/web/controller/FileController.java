package org.example.order.web.controller;

import org.example.order.web.entity.Attachment;
import org.example.order.web.service.FileService;
import org.example.order.web.util.RestConstant;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class FileController {

  private final FileService fileService;
  @Value("${file.upload.folder}")
  private String uploadFolderPath;

  /**
   * Uploading file
   *
   * @param multipartFile
   * @return
   */
  @Tag(
    name = "Uploads a new file",
    description = "Upload new file it is accessible only admins "
  )
  @PostMapping(
    value = "file/file-upload",
    consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public HttpEntity<?> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
    var response = fileService.upload(multipartFile);
    return ResponseEntity.ok(response);
  }


  /// Show image with name
  @Tag(
    name = "Show image with name",
    description = "it is accessible for all user "
  )
  @GetMapping(RestConstant.BASE_OPEN_APIS + "file-view/{name}")
  public ResponseEntity<Resource> viewFile(@PathVariable String name) throws IOException {
    Resource resource = fileService.getFile(name);

    Path filePath = Paths.get(uploadFolderPath).resolve(name).normalize();
    String contentType = Files.probeContentType(filePath);
    if (contentType == null) {
      contentType = "application/octet-stream";
    }

    return ResponseEntity.ok()
      .contentType(MediaType.parseMediaType(contentType))
      .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + name + "\"")
      .body(resource);
  }

  /// Show image with id
  @Tag(
    name = "Show image with id",
    description = "it is accessible for all user "
  )
  @GetMapping(RestConstant.BASE_OPEN_APIS + "file-view-with-id/{id}")
  public ResponseEntity<Resource> viewFileWithId(@PathVariable Long id) throws IOException {

    Attachment attachment = fileService.findAttachmentById(id);

    Resource resource = fileService.getFile(attachment.getFileName());

    Path filePath = Paths.get(uploadFolderPath).resolve(attachment.getFileName()).normalize();
    String contentType = Files.probeContentType(filePath);
    if (contentType == null) {
      contentType = "application/octet-stream";
    }

    return ResponseEntity.ok()
      .contentType(MediaType.parseMediaType(contentType))
      .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + attachment.getFileName() + "\"")
      .body(resource);
  }


}
