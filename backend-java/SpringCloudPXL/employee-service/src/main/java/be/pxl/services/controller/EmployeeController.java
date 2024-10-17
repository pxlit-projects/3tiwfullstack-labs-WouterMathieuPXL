package be.pxl.services.controller;

import be.pxl.services.domain.dto.EmployeeRequest;
import be.pxl.services.domain.dto.EmployeeResponse;
import be.pxl.services.services.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    private final IEmployeeService employeeService;

    @GetMapping
    public ResponseEntity findAll() {
        log.info("Request to get all employees");
        return new ResponseEntity(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse findById(@PathVariable Long id) {
        log.info("Request to get employee by id: {}", id);
        return employeeService.getEmployee(id);
    }

    @GetMapping("/department/{departmentId}")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeResponse> findByDepartment(@PathVariable Long departmentId) {
        log.info("Request to get employees by departmentId: {}", departmentId);
        return employeeService.getEmployeesByDepartmentId(departmentId);
    }

    @GetMapping("/organization/{organizationId}")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeResponse> findByOrganization(@PathVariable Long organizationId) {
        log.info("Request to get employees by organizationId: {}", organizationId);
        return employeeService.getEmployeesByOrganizationId(organizationId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody EmployeeRequest employeeRequest) {
        log.info("Request to add new employee: {}", employeeRequest.getName());
        employeeService.addEmployee(employeeRequest);
    }
}
