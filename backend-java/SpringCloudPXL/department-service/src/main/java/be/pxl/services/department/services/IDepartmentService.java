package be.pxl.services.department.services;

import be.pxl.services.department.domain.dto.DepartmentRequest;
import be.pxl.services.department.domain.dto.DepartmentResponse;

import java.util.List;

public interface IDepartmentService {
    void addDepartment(DepartmentRequest departmentRequest);

    DepartmentResponse getDepartment(Long id);

    List<DepartmentResponse> getDepartments();

    List<DepartmentResponse> getDepartmentsByOrganizationId(Long organizationId);
}
