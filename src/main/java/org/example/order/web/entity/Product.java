package org.example.order.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.order.web.entity.template.BaseEntity;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product")
public class Product extends BaseEntity {

    private String name;

    private String description;

    private double price;

    @ManyToMany
    private List<Attachment> images;

    private Double discountPrice;

    @ManyToOne
    private Category category;
}
