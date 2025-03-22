package org.example.order.web.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.order.web.entity.template.BaseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attachment extends BaseEntity {

 private String fileName;

 private String filePath;

 private String contentType;

 private Long fileSize;

 private String extension;

}
