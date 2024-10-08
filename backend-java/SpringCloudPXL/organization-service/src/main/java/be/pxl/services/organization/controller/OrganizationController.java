package be.pxl.services.organization.controller;

import be.pxl.services.organization.domain.dto.OrganizationResponse;
import be.pxl.services.organization.services.IOrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final IOrganizationService organizationService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationResponse findById(@PathVariable Long id) {
        return organizationService.getOrganization(id);
    }

    @GetMapping("/{id}/with-departments")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationResponse findByIdWithDepartments(@PathVariable Long id) {
        return organizationService.getOrganization(id);
    }

    @GetMapping("/{id}/with-departments-and-employees")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationResponse findByIdWithDepartmentsAndEmployees(@PathVariable Long id) {
        return organizationService.getOrganization(id);
    }

    @GetMapping("/{id}/with-employees")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationResponse findByIdWithEmployees(@PathVariable Long id) {
        return organizationService.getOrganization(id);
    }
}
