package be.pxl.services.services;

import be.pxl.services.client.NotificationClient;
import be.pxl.services.domain.Employee;
import be.pxl.services.domain.NotificationRequest;
import be.pxl.services.domain.dto.EmployeeRequest;
import be.pxl.services.domain.dto.EmployeeResponse;
import be.pxl.services.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;
    private final NotificationClient notificationClient;

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        log.info("Fetching all employees");
        return employeeRepository.findAll().stream().map(this::mapToEmployeeResponse).toList();
    }

    @Override
    public void addEmployee(EmployeeRequest employeeRequest) {
        log.info("Adding new employee: {}", employeeRequest.getName());

        Employee employee =
                Employee.builder().age(employeeRequest.getAge()).name(employeeRequest.getName()).position(employeeRequest.getPosition()).departmentId(employeeRequest.getDepartmentId()).organizationId(employeeRequest.getOrganizationId()).build();
        employeeRepository.save(employee);
        log.info("Employee {} saved successfully", employee.getName());

        NotificationRequest notificationRequest = NotificationRequest.builder().message("Employee created").sender(
                "Wouter").build();
        notificationClient.sendNotification(notificationRequest);
        log.info("Notification sent for employee {}", employee.getName());

    }

    @Override
    public EmployeeResponse getEmployee(Long id) {
        log.info("Fetching employee with id: {}", id);
        return employeeRepository.findById(id).stream().map(this::mapToEmployeeResponse).findFirst().orElse(null);
    }

    @Override
    public List<EmployeeResponse> getEmployeesByDepartmentId(Long departmentId) {
        log.info("Fetching employees for departmentId: {}", departmentId);
        return employeeRepository.findByDepartmentId(departmentId).stream().map(this::mapToEmployeeResponse).toList();
    }

    @Override
    public List<EmployeeResponse> getEmployeesByOrganizationId(Long organizationId) {
        log.info("Fetching employees for organizationId: {}", organizationId);
        return employeeRepository.findByOrganizationId(organizationId).stream().map(this::mapToEmployeeResponse).toList();
    }

    private EmployeeResponse mapToEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder().age(employee.getAge()).name(employee.getName()).position(employee.getPosition()).departmentId(employee.getDepartmentId()).organizationId(employee.getOrganizationId()).build();
    }
}
