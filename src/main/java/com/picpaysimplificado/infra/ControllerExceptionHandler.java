package com.picpaysimplificado.infra;

import com.picpaysimplificado.dtos.ExceptionDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateEntry(DataIntegrityViolationException exception){
        ExceptionDTO exception = new ExceptionDTO("Usuário já cadastrado!", '400');
        return ResponseEntity.badRequest().body(exception);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threat404(EntityNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatGeneralException(Exception exception){
        ExceptionDTO exception = new ExceptionDTO(exception.getMessage(), '500');
        return ResponseEntity.internalServerError().body(exception);
    }
}