package be.pxl.services.services;

import be.pxl.services.client.EmployeeClient;
import be.pxl.services.domain.Department;
import be.pxl.services.domain.dto.DepartmentRequest;
import be.pxl.services.domain.dto.DepartmentResponse;
import be.pxl.services.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeClient employeeClient;

    @Override
    public void addDepartment(DepartmentRequest departmentRequest) {
        Department department = Department.builder()
                .organizationId(departmentRequest.getOrganizationId())
                .name(departmentRequest.getName())
                .position(departmentRequest.getPosition())
                .build();
        departmentRepository.save(department);
    }

    @Override
    public DepartmentResponse getDepartment(Long id) {
        return departmentRepository.findById(id)
                .map(this::mapToDepartmentResponse)
                .orElseThrow();
    }

    @Override
    public List<DepartmentResponse> getDepartments() {
        return departmentRepository.findAll()
                .stream().map(this::mapToDepartmentResponse).toList();
    }

    @Override
    public List<DepartmentResponse> getDepartmentsByOrganizationId(Long organizationId) {
        return departmentRepository.findByOrganizationId(organizationId)
                .stream().map(this::mapToDepartmentResponse).toList();
    }

    @Override
    public List<DepartmentResponse> getDepartmentsByOrganizationIdWithEmployees(Long organizationId) {
        List<DepartmentResponse> departments = getDepartmentsByOrganizationId(organizationId);
        for (DepartmentResponse department : departments) {
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
