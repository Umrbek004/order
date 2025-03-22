package org.example.order.web.service;

import lombok.RequiredArgsConstructor;
import org.example.order.web.entity.Role;
import org.example.order.web.exception.NotFoundException;
import org.example.order.web.payload.ApiResponse;
import org.example.order.web.repository.RoleRepository;
import org.example.order.web.util.messages.error.ErrorMessageKey;
import org.example.order.web.util.messages.error.ErrorMessageService;
import org.example.order.web.util.messages.info.MessageInfo;
import org.example.order.web.util.messages.info.MessageInfoKey;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final ErrorMessageService errorMessageService;
    private final MessageInfo messageInfo;

    public Role findRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(() ->
                new NotFoundException(errorMessageService.getMessage(ErrorMessageKey.ROLE_NOT_FOUND)));
    }

    public ApiResponse getAll() {
        List<Role> roles = roleRepository.findAll();
        return ApiResponse.builder()
                .data(roles)
                .status(true)
                .build();
    }
}
