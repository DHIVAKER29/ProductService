package com.scaler.productservice.advices;


import com.scaler.productservice.DTOs.ErrorDTO;
import com.scaler.productservice.exception.NotValidCategoryException;
import com.scaler.productservice.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleAnyException(Exception ex) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFoundException(ProductNotFoundException ex) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotValidCategoryException.class)

        public ResponseEntity<ErrorDTO> handleNotValidCategoryException(NotValidCategoryException ex){
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setMessage(ex.getMessage());
            return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
        }
    }
}
