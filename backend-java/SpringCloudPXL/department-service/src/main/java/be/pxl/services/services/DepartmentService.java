package be.pxl.services.services;

import be.pxl.services.client.EmployeeClient;
import be.pxl.services.controller.DepartmentController;
import be.pxl.services.domain.Department;
import be.pxl.services.domain.dto.DepartmentRequest;
import be.pxl.services.domain.dto.DepartmentResponse;
import be.pxl.services.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService {

    private static final Logger log = LoggerFactory.getLogger(DepartmentController.class);

    private final DepartmentRepository departmentRepository;
    private final EmployeeClient employeeClient;

    @Override
    public void addDepartment(DepartmentRequest departmentRequest) {
        log.info("Adding new department: {}", departmentRequest.getName());

        Department department = Department.builder()
                .organizationId(departmentRequest.getOrganizationId())
                .name(departmentRequest.getName())
                .position(departmentRequest.getPosition())
                .build();
        departmentRepository.save(department);
        log.info("Department {} saved successfully", department.getName());
    }

    @Override
    public DepartmentResponse getDepartment(Long id) {
        log.info("Fetching department with id: {}", id);
        return departmentRepository.findById(id)
                .map(this::mapToDepartmentResponse)
                .orElseThrow();
    }

    @Override
    public List<DepartmentResponse> getDepartments() {
        log.info("Fetching all departments");
        return departmentRepository.findAll()
                .stream().map(this::mapToDepartmentResponse).toList();
    }

    @Override
    public List<DepartmentResponse> getDepartmentsByOrganizationId(Long organizationId) {
        log.info("Fetching departments by organizationId: {}", organizationId);
        return departmentRepository.findByOrganizationId(organizationId)
                .stream().map(this::mapToDepartmentResponse).toList();
    }

    @Override
    public List<DepartmentResponse> getDepartmentsByOrganizationIdWithEmployees(Long organizationId) {
        log.info("Fetching departments by organizationId: {} with employees", organizationId);
        List<DepartmentResponse> departments = getDepartmentsByOrganizationId(organizationId);
        for (DepartmentResponse department : departments) {
            log.info("Fetching employees for department id: {}", department.getId());
            department.setEmployees(employeeClient.findByDepartment(department.getId()));
        }
        return departments;
    }

    private DepartmentResponse mapToDepartmentResponse(Department department) {
        return DepartmentResponse.builder()
                .id(department.getId())
                .organizationId(department.getOrganizationId())
                .name(department.getName())
                .position(department.getPosition())
                .build();
    }
}
