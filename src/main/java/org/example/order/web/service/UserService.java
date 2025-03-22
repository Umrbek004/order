package org.example.order.web.service;

import lombok.RequiredArgsConstructor;
import org.example.order.web.entity.User;
import org.example.order.web.exception.NotFoundException;
import org.example.order.web.mapper.UserMapper;
import org.example.order.web.payload.ApiResponse;
import org.example.order.web.payload.request.UserCreateReqDto;
import org.example.order.web.repository.UserRepository;
import org.example.order.web.util.messages.error.ErrorMessageKey;
import org.example.order.web.util.messages.info.MessageInfo;
import org.example.order.web.util.messages.info.MessageInfoKey;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MessageInfo messageInfo;


    public ApiResponse create(UserCreateReqDto userCreateReqDto) {
        User user = new User();
        userMapper.toUser(user, userCreateReqDto);
        userRepository.save(user);
        return new ApiResponse(
                messageInfo.getMessage(MessageInfoKey.USER_SAVED_SECCESSFULLY),
                true,
                user
        );
    }


    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessageKey.USER_NOT_FOUND));
    }

    public ApiResponse getById(Long id) {
        return ApiResponse.builder().status(true).data(getUserById(id)).build();
    }

    public ApiResponse getAll() {
        return ApiResponse.builder().status(true).data(userRepository.findAllByDeletedFalse()).build();
    }

    public ApiResponse update(UserCreateReqDto userCreateReqDto) {
        User user = getUserById(userCreateReqDto.id());
        userMapper.toUser(user, userCreateReqDto);
        userRepository.save(user);
        return new ApiResponse(
                messageInfo.getMessage(MessageInfoKey.USER_SAVED_SECCESSFULLY),
                true,
                user
        );
    }
}
