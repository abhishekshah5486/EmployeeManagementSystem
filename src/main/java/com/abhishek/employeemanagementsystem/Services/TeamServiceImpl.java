package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.TeamRequestDto;
import com.abhishek.employeemanagementsystem.Exceptions.*;
import com.abhishek.employeemanagementsystem.Models.*;
import com.abhishek.employeemanagementsystem.Repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamLeaderServiceImpl teamLeaderService;
    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private DepartmentServiceImpl departmentService;
    @Autowired
    private ProjectManagerServiceImpl projectManagerService;
    @Autowired
    private RiskManagerServiceImpl riskManagerService;
    @Autowired
    private MarketManagerServiceImpl marketManagerService;
    @Autowired
    private TechnicalManagerServiceImpl technicalManagerService;
    @Autowired
    private FinanceManagerServiceImpl financeManagerService;
    @Autowired
    private ProjectServiceImpl projectService;
    private TeamRepository teamRepository;
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Teams createTeam(TeamRequestDto teamRequestDto) {
        Teams team = new Teams();
        team.setName(teamRequestDto.getTeamName());
        team.setDescription(teamRequestDto.getTeamDescription());
        return teamRepository.save(team);
    }

    @Override
    public Teams getTeamById(Long id) {
        Optional<Teams> team = teamRepository.findById(id);
        if (team.isEmpty()){
            throw new InvalidTeamIDException("Invalid Team Id", id);
        }
        return team.get();
    }

    @Override
    public List<Teams> retrieveAllTeams() {
        List<Teams> teams = teamRepository.findAll();
        if (teams.isEmpty()){
            throw new NoTeamsFoundException("No teams found");
        }
        return teams;
    }

    @Override
    public void deleteTeamById(Long id) {
        Optional<Teams> team = teamRepository.findById(id);
        if (team.isEmpty()){
            throw new InvalidTeamIDException("Invalid Team Id", id);
        }
        teamRepository.deleteById(id);
    }

    @Override
    public Teams assignTeamLeader(Long teamId, Long teamLeaderId) {
        // fetch the team by id and team leader by id;
        Teams team = getTeamById(teamId);
        TeamLeader teamLeader = teamLeaderService.getTeamLeaderById(teamLeaderId);
        team.setTeamLeader(teamLeader);
        return teamRepository.save(team);
    }

    @Override
    public Teams assignEmployeeToTeam(Long teamId, Long employeeId) {
        // fetch the team by id and team leader by id;
        Teams team = getTeamById(teamId);
//        List<Employee> employees = team.getEmployees();
        // Check if the employee has already been added
        // Check if the employee is present in any teams before adding/assigning to a team and throw exception
        // accordingly;
//        for (int j=0; j<employees.size(); j++) {
//            if (employees.get(j).getId().equals(employeeId)){
//                throw new EmployeeAlreadyInTeamException("Employee Already Exists !");
//            }
//        }
        List<Teams> allTeams = teamRepository.findAll();
        for (int j=0; j<allTeams.size(); j++){
            Long tempTeamId = allTeams.get(j).getId();
            List<Employee> employees = allTeams.get(j).getEmployees();
            for (int k=0; k<employees.size(); k++){
                if (employees.get(k).getId().equals(employeeId) && tempTeamId.equals(teamId)){
                    throw new EmployeeAlreadyInTeamException("Employee Already Assigned to the current team !");
                }
                else if (employees.get(k).getId().equals(employeeId)){
                    throw new EmployeeAlreadyInOtherTeamException("Employee is already assigned to other team");
                }
            }
        }
        Employee employee = employeeService.getEmployeeById(employeeId);
        team.getEmployees().add(employee);
        return teamRepository.save(team);
    }

    @Override
    public void deleteEmployeeFromTeam(Long teamId, Long employeeId) {
        // fetch the team by id and team leader by id;
        Teams team = getTeamById(teamId);
        List<Employee> employees = team.getEmployees();
        boolean isFound = false;
        for (int j=0; j<employees.size(); j++) {
            Long tempId = employees.get(j).getId();
            if (tempId.equals(employeeId)){
                isFound = true;
                employees.remove(j);
            }
        }
        if (isFound){
            throw new EmployeeNotAssignedToTeamException("Employee Not Assigned to the current team");
        }
        team.setEmployees(employees);
        teamRepository.save(team);
    }

    @Override
    public Teams assignAdminToTeam(Long teamId, Long adminId) {
        // fetch the team by id and team leader by id;
        Teams team = getTeamById(teamId);
        // Check if the admin is already assigned to the team
        List<Admin> admins = team.getAdmins();
        for (int j=0; j<admins.size(); j++){
            if (admins.get(j).getId().equals(adminId)){
                throw new AdminAlreadyInTeamException("Admin Already Assigned to the current team");
            }
        }
        Admin admin = adminService.getAdminById(adminId);
        team.getAdmins().add(admin);
        return teamRepository.save(team);
    }

    @Override
    public void deleteAdminFromTeam(Long teamId, Long adminId) {
        // fetch the team by id and admin
        Teams team = getTeamById(teamId);
        List<Admin> admins = team.getAdmins();
        // Check if the Admin is Present in the team, else throw relevant
        // Admin not present exception or invalid id
        boolean isFound = false;
        for (int j=0; j<admins.size(); j++){
            Long tempId = admins.get(j).getId();
            if (tempId.equals(adminId)){
                isFound = true;
                admins.remove(j);
            }
        }
        if (!isFound){
            throw new AdminNotAssignedToTeamException("Admin Not Assigned To Team");
        }
        team.setAdmins(admins);
        teamRepository.save(team);
    }

    @Override
    public Teams assignDepartmentToTeam(Long teamId, Long departmentId) {
        // fetch team by team id and department by department id
        Teams team = getTeamById(teamId);
        Department department = departmentService.getDepartmentById(departmentId);
        // Check if department has already been assigned to team (any department)
        if (team.getDepartment() != null){
            throw new TeamAlreadyAssignedToDepartment("Department Already Assigned to the current team");
        }
        team.setDepartment(department);
        return teamRepository.save(team);
    }

    @Override
    public Teams updateTeamDepartment(Long teamId, Long departmentId) {
        // fetch team by team id and department by department id
        Teams team = getTeamById(teamId);
        Department department = departmentService.getDepartmentById(departmentId);
        team.setDepartment(department);
        return teamRepository.save(team);
    }

    @Override
    public Teams aasignProjectManagerToTeam(Long teamId, Long projectManagerId) {
        // Fetch team by team id and project manager by projectManagerId
        Teams team = getTeamById(teamId);
        ProjectManager projectManager = projectManagerService.getProjectManagerById(projectManagerId);
        // Check if the project manager has already been assigned to the team
        if (team.getProjectManager() != null){
            throw new ProjectManagerAlreadyAssignedToTeamException("Project Manager has already been assigned " +
                    "to this team", teamId);
        }
        team.setProjectManager(projectManager);
        return teamRepository.save(team);
    }

    @Override
    public Teams updateProjectManagerToTeam(Long teamId, Long projectManagerId) {
        // Fetch team by team id and project manager by projectManagerId
        Teams team = getTeamById(teamId);
        ProjectManager projectManager = projectManagerService.getProjectManagerById(projectManagerId);
        // Check if the project manager has been assigned to the team or not
        if (team.getProjectManager() == null){
            throw new NoProjectManagerAssignedToTeamException("Unable to update the project manager !" +
                    "No Project Manager has been assigned to this team." +
                    "Please assign a project manager to the team.", teamId);
        }
        team.setProjectManager(projectManager);
        return teamRepository.save(team);
    }

    @Override
    public void deleteProjectManagerFromTeam(Long teamId, Long projectManagerId) {
        // Fetch team by team id and project manager by projectManagerId
        Teams team = getTeamById(teamId);
        if (team.getProjectManager() == null){
            throw new NoProjectManagerAssignedToTeamException("No Project Manager with this id has been assigned to team", teamId);
        }
        team.setProjectManager(null);
        teamRepository.save(team);
    }

    @Override
    public Teams assignMarketManagerToTeam(Long teamId, Long marketManagerId) {
        // Fetch team by team id and market manager by marketManagerId
        Teams team = getTeamById(teamId);
        MarketManager marketManager = marketManagerService.getMarketManagerById(marketManagerId);
        // Check if the market manager has already been assigned to the team
        if (team.getMarketManager() != null){
            throw new MarketManagerAlreadyAssignedToTeamException("Market Manager has already been assigned " +
                    "to this team", teamId);
        }
        team.setMarketManager(marketManager);
        return teamRepository.save(team);
    }

    @Override
    public Teams updateMarketManagerToTeam(Long teamId, Long marketManagerId) {
        // Fetch team by team id and market manager by marketManagerId
        Teams team = getTeamById(teamId);
        MarketManager marketManager = marketManagerService.getMarketManagerById(marketManagerId);
        // Check if the market manager has been assigned to the team or not
        if (team.getMarketManager() == null){
            throw new NoMarketManagerAssignedToTeamException("Unable to update the market manager !" +
                    "No Market Manager has been assigned to this team." +
                    "Please assign a market manager to the team.", teamId);
        }
        team.setMarketManager(marketManager);
        return teamRepository.save(team);
    }

    @Override
    public void deleteMarketManagerFromTeam(Long teamId, Long marketManagerId) {
        // Fetch team by team id and market manager by marketManagerId
        Teams team = getTeamById(teamId);
        if (team.getMarketManager() == null){
            throw new NoMarketManagerAssignedToTeamException("No Market Manager with this id has been assigned to team", teamId);
        }
        team.setMarketManager(null);
        teamRepository.save(team);
    }

    @Override
    public Teams assignFinanceManagerToTeam(Long teamId, Long financeManagerId) {
        // Fetch team by team id and finance manager by financeManagerId
        Teams team = getTeamById(teamId);
        FinanceManager financeManager = financeManagerService.getFinanceManagerById(financeManagerId);
        // Check if the finance manager has already been assigned to the team
        if (team.getFinanceManager() != null){
            throw new FinanceManagerAlreadyAssignedToTeamException("Finance Manager has already been assigned " +
                    "to this team", teamId);
        }
        team.setFinanceManager(financeManager);
        return teamRepository.save(team);
    }

    @Override
    public Teams updateFinanceManagerToTeam(Long teamId, Long financeManagerId) {
        // Fetch team by team id and finance manager by financeManagerId
        Teams team = getTeamById(teamId);
        FinanceManager financeManager = financeManagerService.getFinanceManagerById(financeManagerId);
        // Check if the finance manager has been assigned to the team or not
        if (team.getFinanceManager() == null){
            throw new NoFinanceManagerAssignedToTeamException("Unable to update the finance manager !" +
                    "No Finance Manager has been assigned to this team." +
                    "Please assign a finance manager to the team.", teamId);
        }
        team.setFinanceManager(financeManager);
        return teamRepository.save(team);
    }

    @Override
    public void deleteFinanceManagerFromTeam(Long teamId, Long financeManagerId) {
        // Fetch team by team id and finance manager by financeManagerId
        Teams team = getTeamById(teamId);
        if (team.getFinanceManager() == null){
            throw new NoFinanceManagerAssignedToTeamException("No Finance Manager with this id has been assigned to team", teamId);
        }
        team.setFinanceManager(null);
        teamRepository.save(team);
    }

    @Override
    public Teams assignTechnicalManagerToTeam(Long teamId, Long technicalManagerId) {
        // Fetch team by team id and technical manager by technicalManagerId
        Teams team = getTeamById(teamId);
        TechnicalManager technicalManager = technicalManagerService.getTechnicalManagerById(technicalManagerId);
        // Check if the technical manager has already been assigned to the team
        if (team.getTechnicalManager() != null){
            throw new TechnicalManagerAlreadyAssignedToTeamException("Technical Manager has already been assigned " +
                    "to this team", teamId);
        }
        team.setTechnicalManager(technicalManager);
        return teamRepository.save(team);
    }

    @Override
    public Teams updateTechnicalManagerToTeam(Long teamId, Long technicalManagerId) {
        // Fetch team by team id and technical manager by technicalManagerId
        Teams team = getTeamById(teamId);
        TechnicalManager technicalManager = technicalManagerService.getTechnicalManagerById(technicalManagerId);
        // Check if the technical manager has been assigned to the team or not
        if (team.getTechnicalManager() == null){
            throw new NoTechnicalManagerAssignedToTeamException("Unable to update the technical manager !" +
                    "No Technical Manager has been assigned to this team." +
                    "Please assign a technical manager to the team.", teamId);
        }
        team.setTechnicalManager(technicalManager);
        return teamRepository.save(team);
    }

    @Override
    public void deleteTechnicalManagerFromTeam(Long teamId, Long technicalManagerId) {
        // Fetch team by team id and technical manager by technicalManagerId
        Teams team = getTeamById(teamId);
        if (team.getTechnicalManager() == null){
            throw new NoTechnicalManagerAssignedToTeamException("No Technical Manager with this id has been assigned to team", teamId);
        }
        team.setTechnicalManager(null);
        teamRepository.save(team);
    }

    @Override
    public Teams assignRiskManagerToTeam(Long teamId, Long riskManagerId) {
        // Fetch team by team id and risk manager by riskManagerId
        Teams team = getTeamById(teamId);
        RiskManager riskManager = riskManagerService.getRiskManagerById(riskManagerId);
        // Check if the risk manager has already been assigned to the team
        if (team.getRiskManager() != null){
            throw new RiskManagerAlreadyAssignedToTeamException("Risk Manager has already been assigned " +
                    "to this team", teamId);
        }
        team.setRiskManager(riskManager);
        return teamRepository.save(team);
    }

    @Override
    public Teams updateRiskManagerToTeam(Long teamId, Long riskManagerId) {
        // Fetch team by team id and risk manager by riskManagerId
        Teams team = getTeamById(teamId);
        RiskManager riskManager = riskManagerService.getRiskManagerById(riskManagerId);
        // Check if the risk manager has been assigned to the team or not
        if (team.getRiskManager() == null){
            throw new NoRiskManagerAssignedToTeamException("Unable to update the risk manager !" +
                    "No Risk Manager has been assigned to this team." +
                    "Please assign a risk manager to the team.", teamId);
        }
        team.setRiskManager(riskManager);
        return teamRepository.save(team);
    }

    @Override
    public void deleteRiskManagerFromTeam(Long teamId, Long riskManagerId) {
        // Fetch team by team id and risk manager by riskManagerId
        Teams team = getTeamById(teamId);
        if (team.getRiskManager() == null){
            throw new NoRiskManagerAssignedToTeamException("No Risk Manager with this id has been assigned to team", teamId);
        }
        team.setRiskManager(null);
        teamRepository.save(team);
    }

}
