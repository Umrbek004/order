package org.example.order.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.example.order.web.entity.template.BaseEntity;
import org.example.order.constants.RoleName;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity {

  @Enumerated(EnumType.STRING)
  private RoleName name;

}
