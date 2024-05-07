package com.abhishek.employeemanagementsystem.ExceptionHandler;

import com.abhishek.employeemanagementsystem.Dtos.ExceptionDto;
import com.abhishek.employeemanagementsystem.Exceptions.EmployeeIDAlreadyExistsException;
import com.abhishek.employeemanagementsystem.Exceptions.InvalidEmployeeIdFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidEmployeeIdFoundException.class)
    public ResponseEntity<ExceptionDto> handleInvalidEmployeeIdFoundException(InvalidEmployeeIdFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Something went wrong");
        exceptionDto.setResolution("Invalid Employee Id Passed");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDto> handleRuntimeException(RuntimeException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Something went wrong");
        exceptionDto.setResolution("RuntimeException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

    @ExceptionHandler(EmployeeIDAlreadyExistsException.class)
    public ResponseEntity<ExceptionDto> handleEmployeeIDAlreadyExistsException(EmployeeIDAlreadyExistsException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Employee with ID : " + e.getId() + " Already Exists");
        exceptionDto.setResolution("EmployeeIDAlreadyExistsException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleException(Exception e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Something went wrong");
        exceptionDto.setResolution("GeneralException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

}
