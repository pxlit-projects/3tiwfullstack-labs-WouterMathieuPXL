package be.pxl.services.services;

import be.pxl.services.client.DepartmentClient;
import be.pxl.services.client.EmployeeClient;
import be.pxl.services.domain.Organization;
import be.pxl.services.domain.dto.OrganizationRequest;
import be.pxl.services.domain.dto.OrganizationResponse;
import be.pxl.services.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationService implements IOrganizationService {

    private static final Logger log = LoggerFactory.getLogger(OrganizationService.class);

    private final OrganizationRepository organizationRepository;
    private final EmployeeClient employeeClient;
    private final DepartmentClient departmentClient;

    @Override
    public void addOrganization(OrganizationRequest organizationRequest) {
        log.info("Adding new organization: {}", organizationRequest.getName());

        Organization organization = Organization.builder()
                .name(organizationRequest.getName())
                .address(organizationRequest.getAddress())
                .build();
        organizationRepository.save(organization);
        log.info("Organization {} saved successfully", organization.getName());
    }

    @Override
    public OrganizationResponse getOrganization(Long id) {
        log.info("Fetching organization with id: {}", id);
        return organizationRepository.findById(id)
                .map(this::mapToOrganizationResponse)
                .orElseThrow();
    }

    @Override
    public OrganizationResponse getOrganizationWithDepartmentsAndEmployees(Long id) {
        log.info("Fetching organization with id: {}", id);
        OrganizationResponse organization = organizationRepository.findById(id)
                .map(this::mapToOrganizationResponse)
                .orElseThrow();

        log.info("Fetching departments for organization id: {}", id);
        organization.setDepartments(departmentClient.findByOrganization(id));

        log.info("Fetching employees for organization id: {}", id);
        organization.setEmployees(employeeClient.findByOrganization(id));

        return organization;
    }

    @Override
    public OrganizationResponse getOrganizationWithEmployees(Long id) {
        log.info("Fetching organization with id: {}", id);
        OrganizationResponse organization = organizationRepository.findById(id)
                .map(this::mapToOrganizationResponse)
                .orElseThrow();

        log.info("Fetching employees for organization id: {}", id);
        organization.setEmployees(employeeClient.findByOrganization(id));
        return organization;
    }

    private OrganizationResponse mapToOrganizationResponse(Organization organization) {
        return OrganizationResponse.builder()
                .name(organization.getName())
                .address(organization.getAddress())
                .build();
    }
}
