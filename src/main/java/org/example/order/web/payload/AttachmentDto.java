package org.example.order.web.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentDto {

    private Long id;

    private String fileName;

    private String filePath;

    private String contentType;

    private Long fileSize;

    private String extension;

}
