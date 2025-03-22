package org.example.order.web.payload.request;

import jakarta.validation.constraints.NotNull;

public record UserCreateReqDto(
        Long id,
        @NotNull String name,
        @NotNull String password,
        @NotNull String phoneNumber,
        @NotNull Long roleId
) {
}
