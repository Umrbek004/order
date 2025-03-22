package org.example.order.web.payload.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProductCreateReqDto(
        Long id,
        @NotNull String name,
        @NotNull String description,
        double price,
        List<Long> images,
        @NotNull Long categoryId,
        Double discountPrice
) {
}
