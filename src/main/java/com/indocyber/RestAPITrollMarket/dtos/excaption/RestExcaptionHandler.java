package com.indocyber.RestAPITrollMarket.dtos.excaption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

//@ControllerAdvice
@RestControllerAdvice
public class RestExcaptionHandler {
    @ExceptionHandler(EntityNotFoundExcaption.class)
    public ResponseEntity<ErrorMessageDto> hendleEntityNotFoundException(EntityNotFoundExcaption e) {

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ErrorMessageDto dto = ErrorMessageDto.builder()
                .status(httpStatus)
                .errors(e)
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(dto,httpStatus);

    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageDto<Map<String , String>>>handletMethodArgumentNotVslidException(
            MethodArgumentNotValidException e) {
        HttpStatus httpStatus = HttpStatus.NOT_ACCEPTABLE;

        var errorMap = new HashMap<String, String>();
        e.getBindingResult().getFieldErrors().forEach(fieldError ->
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage())
        );

        ErrorMessageDto<Map<String, String>>dto =ErrorMessageDto.<Map<String,String>>builder()
                .status(httpStatus)
                .message(" input invalid")
                .errors(errorMap)
                .build();

        return new ResponseEntity<>(dto, httpStatus);

    }

//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<ErrorMessageDto<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
//        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
//
//        ErrorMessageDto<String> dto = ErrorMessageDto.<String>builder()
//                .status(httpStatus)
//                .message(ex.getMessage())
//                .errors(ex.getMessage())
//                .build();
//
//        return new ResponseEntity<>(dto, httpStatus);
//    }

    @ExceptionHandler(DependencyExistsException.class)
    public ResponseEntity<String> handleDependencyExistsException(DependencyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }


}
