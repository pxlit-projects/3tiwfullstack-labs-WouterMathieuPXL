package be.pxl.services.controller;

import be.pxl.services.domain.dto.OrganizationRequest;
import be.pxl.services.domain.dto.OrganizationResponse;
import be.pxl.services.services.IOrganizationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organization")
@RequiredArgsConstructor
public class OrganizationController {

    private static final Logger log = LoggerFactory.getLogger(OrganizationController.class);

    private final IOrganizationService organizationService;

    @PostMapping
    public void add(@RequestBody OrganizationRequest organizationRequest){
        log.info("Received request to add new organization: {}", organizationRequest.getName());
        organizationService.addOrganization(organizationRequest);
        log.info("Organization {} added successfully", organizationRequest.getName());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationResponse findById(@PathVariable Long id) {
        log.info("Fetching organization with id: {}", id);
        return organizationService.getOrganization(id);
    }

    @GetMapping("/{id}/with-departments")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationResponse findByIdWithDepartments(@PathVariable Long id) {
        log.info("Fetching organization with id: {} along with departments", id);
        return organizationService.getOrganization(id);
    }

    @GetMapping("/{id}/with-departments-and-employees")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationResponse findByIdWithDepartmentsAndEmployees(@PathVariable Long id) {
        log.info("Fetching organization with id: {} along with departments and employees", id);
        return organizationService.getOrganizationWithDepartmentsAndEmployees(id);
    }

    @GetMapping("/{id}/with-employees")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationResponse findByIdWithEmployees(@PathVariable Long id) {
        log.info("Fetching organization with id: {} along with employees", id);
        return organizationService.getOrganizationWithEmployees(id);
    }
}
