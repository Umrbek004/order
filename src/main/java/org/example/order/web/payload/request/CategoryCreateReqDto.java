package org.example.order.web.payload.request;

import jakarta.validation.constraints.NotNull;

public record CategoryCreateReqDto(
        Long id,
    @NotNull String name,
    @NotNull String description,
    Long imageId
) {
}
