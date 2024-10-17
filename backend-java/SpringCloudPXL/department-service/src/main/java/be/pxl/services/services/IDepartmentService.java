package be.pxl.services.services;

import be.pxl.services.domain.dto.DepartmentRequest;
import be.pxl.services.domain.dto.DepartmentResponse;

import java.util.List;

public interface IDepartmentService {
    void addDepartment(DepartmentRequest departmentRequest);

    DepartmentResponse getDepartment(Long id);

    List<DepartmentResponse> getDepartments();

    List<DepartmentResponse> getDepartmentsByOrganizationId(Long organizationId);

    List<DepartmentResponse> getDepartmentsByOrganizationIdWithEmployees(Long organizationId);

}
