package org.example.order.web.exception;

import org.example.order.web.payload.ErrorResponse;
import org.example.order.web.util.messages.error.ErrorMessageKey;
import org.example.order.web.util.messages.error.ErrorMessageService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Hidden
@RequiredArgsConstructor
@RestControllerAdvice
public class
GlobalExceptionHandler {

  private final ErrorMessageService errorMessageService;
  Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  /// Handle Wrong argument
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException exception, WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(
      exception.getMessage(),
      HttpStatus.BAD_REQUEST.value(),
      Timestamp.valueOf(LocalDateTime.now()),
      request.getDescription(false)
    );
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }


  /// Handle all exception types
  @ExceptionHandler({
    NotFoundException.class,
    AlreadyExistException.class,
    AccessDeniedException.class,
    AccountStatusException.class,
    BadCredentialsException.class,
    Exception.class,
    ForbiddenException.class,
    NoResourceFoundException.class,
    UserNotException.class,
    InvalidPasswordException.class,
    NotSupportedFile.class,
    NotEnoughValuesForMultiLang.class
  })
  public ResponseEntity<ErrorResponse> handleException(Exception exception, WebRequest request) {
    LOGGER.error(exception.getMessage(), exception);

    HttpStatus status = determineHttpStatus(exception);
    String message = determineErrorMessage(exception);

    ErrorResponse errorResponse = new ErrorResponse(
      message,
      status.value(),
      Timestamp.valueOf(LocalDateTime.now()),
      request.getDescription(false)
    );

    return new ResponseEntity<>(errorResponse, status);
  }


  ///Determine exact Http status code based on exception
  private HttpStatus determineHttpStatus(Exception exception) {
    if (exception instanceof NotFoundException || exception instanceof NoResourceFoundException || exception instanceof UserNotException) {
      return HttpStatus.NOT_FOUND;
    } else if (exception instanceof AlreadyExistException || exception instanceof InvalidPasswordException || exception instanceof NotSupportedFile) {
      return HttpStatus.BAD_REQUEST;
    } else if (exception instanceof AccessDeniedException || exception instanceof ForbiddenException) {
      return HttpStatus.FORBIDDEN;
    } else if (exception instanceof AccountStatusException || exception instanceof BadCredentialsException) {
      return HttpStatus.UNAUTHORIZED;
    }
    return HttpStatus.INTERNAL_SERVER_ERROR;
  }

  ///Determine exact exception message code based on exception
  private String determineErrorMessage(Exception exception) {
    if (exception instanceof BadCredentialsException) {
      return errorMessageService.getMessage(ErrorMessageKey.BAD_CREDENTIALS_EXCEPTION);
    } else if (exception instanceof NoResourceFoundException || exception instanceof UserNotException) {
      return ErrorMessageKey.NOT_VALID_RESOURCE + exception.getMessage();
    } else if (exception instanceof InvalidPasswordException) {
      return exception.getMessage();
    } else if (exception instanceof NotSupportedFile) {
      return errorMessageService.getMessage(exception.getMessage());
    }
    return exception.getMessage();
  }

}
