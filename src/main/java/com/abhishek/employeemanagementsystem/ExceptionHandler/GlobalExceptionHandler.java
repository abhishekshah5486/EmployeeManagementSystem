package com.abhishek.employeemanagementsystem.ExceptionHandler;

import com.abhishek.employeemanagementsystem.Dtos.ExceptionDto;
import com.abhishek.employeemanagementsystem.Exceptions.*;
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

    @ExceptionHandler(NoEmployeesFoundException.class)
    public ResponseEntity<ExceptionDto> handleNoEmployeesFoundException(NoEmployeesFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("No Employees Found");
        exceptionDto.setResolution("NoEmployeesFoundException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }


    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleRoleNotFoundException(RoleNotFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Role not found");
        exceptionDto.setResolution("RoleNotFoundException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(NoEmployeesFoundForRoleException.class)
    public ResponseEntity<ExceptionDto> handleNoEmployeesFoundForRoleException(NoEmployeesFoundForRoleException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("No Employees Found for role Id : " + e.getId() + " " + e.getName());
        exceptionDto.setResolution("NoEmployeesFoundForRoleException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(NoTeamsFoundException.class)
    public ResponseEntity<ExceptionDto> handleNoTeamsFoundException(NoTeamsFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("No Teams Found");
        exceptionDto.setResolution("NoTeamsFoundException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(InvalidTeamIDException.class)
    public ResponseEntity<ExceptionDto> handleInvalidTeamIDException(InvalidTeamIDException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("No team found for the ID : " + e.getId());
        exceptionDto.setResolution("InvalidTeamIDException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
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

    @ExceptionHandler(InvalidTeamLeaderIDException.class)
    public ResponseEntity<ExceptionDto> handleInvalidTeamLeaderIDException(InvalidTeamLeaderIDException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Team ID : " + e.getId() + " is not correct");
        exceptionDto.setResolution("InvalidTeamLeaderIDException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(NoTeamLeadersFoundException.class)
    public ResponseEntity<ExceptionDto> handleNoTeamLeadersFoundException(NoTeamLeadersFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("No Team Leaders Found");
        exceptionDto.setResolution("NoTeamLeadersFoundException");
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeAlreadyInTeamException.class)
    public ResponseEntity<ExceptionDto> handleEmployeeAlreadyInTeamException(EmployeeAlreadyInTeamException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Employee Already In Team");
        exceptionDto.setResolution("EmployeeAlreadyInTeamException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(EmployeeAlreadyInOtherTeamException.class)
    public ResponseEntity<ExceptionDto> handleEmployeeAlreadyInOtherTeamException(EmployeeAlreadyInOtherTeamException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Employee Already In Other Team");
        exceptionDto.setResolution("EmployeeAlreadyInOtherTeamException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(AdminAlreadyInTeamException.class)
    public ResponseEntity<ExceptionDto> handleAdminAlreadyInTeamException(AdminAlreadyInTeamException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Admin Already In Team");
        exceptionDto.setResolution("AdminAlreadyInTeamException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(AdminNotAssignedToTeamException.class)
    public ResponseEntity<ExceptionDto> handleAdminNotAssignedToTeamException(AdminNotAssignedToTeamException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Admin is not currently assigned to this team. Please ensure you have selected the correct team or verify the admin's current assignments.");
        exceptionDto.setResolution("AdminNotAssignedToTeamException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(EmployeeNotAssignedToTeamException.class)
    public ResponseEntity<ExceptionDto> handleEmployeeNotAssignedToTeamException(EmployeeNotAssignedToTeamException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Employee is not currently assigned to this team.");
        exceptionDto.setResolution("EmployeeNotAssignedToTeamException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(TeamAlreadyAssignedToDepartment.class)
    public ResponseEntity<ExceptionDto> handleTeamAlreadyAssignedToDepartment(TeamAlreadyAssignedToDepartment e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Team has already been assigned to a department. Please update the department in case you want to make any changes");
        exceptionDto.setResolution("TeamAlreadyAssignedToDepartmentException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(InvalidProjectManagerIDException.class)
    public ResponseEntity<ExceptionDto> handleInvalidProjectManagerIDException(InvalidProjectManagerIDException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Invalid Project Manager ID : " + e.getId() + " passed.");
        exceptionDto.setResolution("InvalidProjectManagerIDException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(ProjectManagerIDNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleProjectManagerIDNotFoundException(ProjectManagerIDNotFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Project Manager ID " + e.getId() + " not found.");
        exceptionDto.setResolution("ProjectManagerIDNotFoundException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(NoProjectManagersFoundException.class)
    public ResponseEntity<ExceptionDto> handleNoProjectManagersFoundException(NoProjectManagersFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("No Project Managers Found");
        exceptionDto.setResolution("NoProjectManagersFoundException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(InvalidRiskManagerIDException.class)
    public ResponseEntity<ExceptionDto> handleInvalidRiskManagerIDException(InvalidRiskManagerIDException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Invalid Risk Manager ID : " + e.getId() + " passed.");
        exceptionDto.setResolution("InvalidRiskManagerIDException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(RiskManagerIDNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleRiskManagerIDNotFoundException(RiskManagerIDNotFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Risk Manager ID " + e.getId() + " not found.");
        exceptionDto.setResolution("RiskManagerIDNotFoundException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(NoRiskManagersFoundException.class)
    public ResponseEntity<ExceptionDto> handleNoRiskManagersFoundException(NoRiskManagersFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("No Risk Manager Found");
        exceptionDto.setResolution("NoRiskManagersFoundException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(InvalidFinanceManagerIDException.class)
    public ResponseEntity<ExceptionDto> handleInvalidFinanceManagerIDException(InvalidFinanceManagerIDException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Invalid Finance Manager ID : " + e.getId() + " passed.");
        exceptionDto.setResolution("InvalidFinanceManagerIDException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(FinanceManagerIDNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleFinanceManagerIDNotFoundException(FinanceManagerIDNotFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Finance Manager ID " + e.getId() + " not found.");
        exceptionDto.setResolution("FinanceManagerIDNotFoundException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(NoFinanceManagersFoundException.class)
    public ResponseEntity<ExceptionDto> handleNoFinanceManagersFoundException(NoFinanceManagersFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("No Finance Manager Found");
        exceptionDto.setResolution("NoFinanceManagersFoundException");
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleException(Exception e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Something went wrong");
        exceptionDto.setResolution("GeneralException");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

}
