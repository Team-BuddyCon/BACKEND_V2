package yapp.buddycon.app.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import yapp.buddycon.app.common.response.ApiResponse;
import yapp.buddycon.app.common.response.ApplicationException;
import yapp.buddycon.app.common.response.BadRequestException;
import yapp.buddycon.app.common.response.ForbiddenRequestException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BadRequestException.class})
    protected ResponseEntity<?> handleBadRequestException(BadRequestException e) {
        log.error("handleBadRequestException throw BadRequestException : {}", e.getMessage());
        return ApiResponse.badRequest(e.getMessage(), null);
    }

    @ExceptionHandler(value = {ApplicationException.class})
    protected ResponseEntity<?> handleApplicationException(ApplicationException e) {
        log.error("handleApplicationException throw ApplicationException : {}", e.getMessage());
        return ApiResponse.serverError(e.getMessage(), null);
    }

    @ExceptionHandler(value = ForbiddenRequestException.class)
    protected ResponseEntity<?> handleForbiddenRequestException(ForbiddenRequestException e) {
        log.error("handleForbiddenRequestException throw ForbiddenRequestException : {}", e.getMessage());
        return ApiResponse.forbidden(e.getMessage(), null);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<?> handleValidationException(MethodArgumentNotValidException e) {
        log.error("handleValidationException throw MethodArgumentNotValidException : {}", e.getMessage());
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ApiResponse.badRequest(e.getBody().getDetail(), errors);
    }
}