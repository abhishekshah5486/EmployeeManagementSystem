package com.abhishek.employeemanagementsystem.ExceptionHandler;

import com.abhishek.employeemanagementsystem.Dtos.ExceptionDto;
import com.abhishek.employeemanagementsystem.Exceptions.*;
import org.springframework.data.crossstore.ChangeSetPersister;
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

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handleArithmeticException(ArithmeticException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Something went wrong");
        exceptionDto.setResolution("ArithmeticException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionDto> handleNullPointerException(NullPointerException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Something went wrong");
        exceptionDto.setResolution("NullPointerException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ExceptionDto> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Something went wrong");
        exceptionDto.setResolution("UsernameAlreadyExistsException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<ExceptionDto> handleArrayIndexOutOfBoundsException(ArrayIndexOutOfBoundsException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Something went wrong");
        exceptionDto.setResolution("ArrayIndexOutOfBoundsException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
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

    @ExceptionHandler(AdminNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleAdminNotFoundException(AdminNotFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Admin with id " + e.getId() + " not found");
        exceptionDto.setResolution("AdminNotFoundException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(NoAdminsFoundException.class)
    public ResponseEntity<ExceptionDto> handleNoAdminsFoundException(NoAdminsFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("No Admins Found");
        exceptionDto.setResolution("NoAdminsFoundException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(InvalidUsernamePassedException.class)
    public ResponseEntity<ExceptionDto> handleInvalidUsernamePassedException(InvalidUsernamePassedException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Invalid username passed");
        exceptionDto.setResolution("InvalidUsernamePassedException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(InvalidPasswordForUsernameException.class)
    public ResponseEntity<ExceptionDto> handleInvalidPasswordForUsernameException(InvalidPasswordForUsernameException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Password for username is not correct");
        exceptionDto.setResolution("InvalidPasswordForUsernameException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(DepartmentIDAlreadyExistsException.class)
    public ResponseEntity<ExceptionDto> handleDepartmentIDAlreadyExistsException(DepartmentIDAlreadyExistsException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Depatment ID Already Exists");
        exceptionDto.setResolution("DepartmentIDAlreadyExistsException");
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

    @ExceptionHandler(InvalidDepartmentIDException.class)
    public ResponseEntity<ExceptionDto> handleInvalidDepartmentIDException(InvalidDepartmentIDException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Department ID : " + e.getId() + " is not correct");
        exceptionDto.setResolution("InvalidDepartmentIDException");
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
