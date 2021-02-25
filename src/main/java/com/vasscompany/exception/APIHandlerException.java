package com.vasscompany.exception;

import com.vasscompany.exception.dto.ErrorField;
import com.vasscompany.exception.dto.ResponseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@RestControllerAdvice
public class APIHandlerException extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ErrorField> errors = new ArrayList<>();
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        allErrors.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(new ErrorField(fieldName, errorMessage));
        });

        ResponseException responseException = new ResponseException(
            LocalDateTime.now(), "Errores de validación", String.valueOf(errors.size())
        );
        responseException.setErrors(errors);
        return new ResponseEntity<Object>(responseException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationFailure(ConstraintViolationException ex, WebRequest request) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Stream<ConstraintViolation<?>> stream = constraintViolations.stream();
        Stream<String> stringStream = stream.map(c -> c.getMessage());
        List<ErrorField> errors = new ArrayList<>();
        stringStream.forEach(errorMessage -> {
            errors.add(new ErrorField(errorMessage));
        });
        ResponseException responseException = new ResponseException(
                LocalDateTime.now(), "Errores de validación", String.valueOf(errors.size())
        );
        responseException.setErrors(errors);
        return new ResponseEntity<>(responseException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(APINotFoundException.class)
    public final ResponseEntity<Object> APINotFoundException(APINotFoundException ex, WebRequest request) {
        ResponseException responseException = new ResponseException(
                LocalDateTime.now(), ex.getMessage(), null
        );
        return new ResponseEntity<>(responseException, HttpStatus.NOT_FOUND);
    }

}
