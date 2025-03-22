package org.example.order.web.mapper;

import lombok.RequiredArgsConstructor;
import org.example.order.web.entity.User;
import org.example.order.web.payload.request.UserCreateReqDto;
import org.example.order.web.service.RoleService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public void toUser(User user, UserCreateReqDto userCreateReqDto) {
        user.setPhoneNumber(user.getPhoneNumber());
        user.setName(user.getName());
        if (user.getPassword() == null)
            user.setPassword(passwordEncoder.encode(userCreateReqDto.password()));
        user.setRole(roleService.findRoleById(userCreateReqDto.roleId()));
    }
}
