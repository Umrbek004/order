package org.example.order.web.service;

import org.example.order.web.config.security.JWTProvider;
import org.example.order.web.entity.User;
import org.example.order.web.exception.NotFoundException;
import org.example.order.web.exception.UserNotException;
import org.example.order.web.payload.AuthRequest;
import org.example.order.web.payload.AuthResponse;
import org.example.order.web.repository.UserRepository;
import org.example.order.web.payload.ResponseData;
import org.example.order.web.util.messages.error.ErrorMessageKey;
import org.example.order.web.util.messages.info.MessageInfoKey;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final AuthenticationManager authenticationManager;
  private final JWTProvider jwtProvider;

  public ResponseData<?> authenticate(AuthRequest authRequest) throws UserNotException {

    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.phoneNumber(), authRequest.password()));

    Optional<User> byUserDetailPhoneNumber = userRepository.findByPhoneNumber(authRequest.phoneNumber());
    if (byUserDetailPhoneNumber.isEmpty()) {
      throw new NotFoundException(ErrorMessageKey.USER_NOT_FOUND);
    }
    User user = byUserDetailPhoneNumber.get();
    ResponseData<AuthResponse> authResponseResponseData = new ResponseData<>();
    String token = jwtProvider.generateAccessToken(user);
    AuthResponse authResponse = new AuthResponse(user, token);
    authResponseResponseData.setData(authResponse);
    authResponseResponseData.setSuccess(true);
    authResponseResponseData.setMessage(MessageInfoKey.USER_FOUND);
    return ResponseData.successResponse(authResponse);
  }
}
