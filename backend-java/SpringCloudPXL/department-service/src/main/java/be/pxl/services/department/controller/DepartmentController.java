package be.pxl.services.department.controller;

import be.pxl.services.department.domain.dto.DepartmentRequest;
import be.pxl.services.department.domain.dto.DepartmentResponse;
import be.pxl.services.department.services.IDepartmentService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final IDepartmentService departmentService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody DepartmentRequest departmentRequest) {
        departmentService.addDepartment(departmentRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DepartmentResponse findById(@PathVariable Long id) {
        return departmentService.getDepartment(id);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<DepartmentResponse> findAll() {
        return departmentService.getDepartments();
    }

    @GetMapping("/organization/{organizationId}")
    @ResponseStatus(HttpStatus.OK)
    public List<DepartmentResponse> findByOrganization(@PathVariable Long organizationId) {
        return departmentService.getDepartmentsByOrganizationId(organizationId);
    }

    @GetMapping("/organization/{organizationId}/with-employees")
    @ResponseStatus(HttpStatus.OK)
    public List<DepartmentResponse> findByOrganizationWithEmployees(@PathVariable Long organizationId) {
        return departmentService.getDepartmentsByOrganizationId(organizationId);
    }
}
