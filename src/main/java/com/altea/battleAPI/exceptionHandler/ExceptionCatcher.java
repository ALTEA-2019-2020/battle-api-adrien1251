package com.altea.battleAPI.exceptionHandler;

import com.altea.battleAPI.bo.ExceptionResponse;
import com.altea.battleAPI.exceptions.ApplicationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.UnexpectedTypeException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class ExceptionCatcher {

    @ExceptionHandler(value = {ApplicationException.class})
    public ResponseEntity<ExceptionResponse> applicationException(ApplicationException ex, WebRequest request) {
        return ResponseEntity.status(ex.getErrCode())
                .body(ExceptionResponse.builder()
                        .statusErrorCode(ex.getErrCode().value())
                        .statusErrorMessage(ex.getErrCode().getReasonPhrase())
                        .errorMessage(ex.getErrMsg())
                        .build()
                );
    }

    @ExceptionHandler({UnexpectedTypeException.class})
    public final ResponseEntity<Object> BadArgumentException(UnexpectedTypeException ex){
        return ResponseEntity.status(404).body(ex.getMessage());
    }


    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity genericExceptionHandler(Exception ex, WebRequest request) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(ExceptionResponse.builder()
                .statusErrorCode(INTERNAL_SERVER_ERROR.value())
                .statusErrorMessage(INTERNAL_SERVER_ERROR.getReasonPhrase())
                .errorMessage(ex.getMessage())
                .build());
    }}
