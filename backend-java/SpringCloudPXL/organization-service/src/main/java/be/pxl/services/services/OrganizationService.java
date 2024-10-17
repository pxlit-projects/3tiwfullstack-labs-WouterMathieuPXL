package be.pxl.services.services;

import be.pxl.services.client.DepartmentClient;
import be.pxl.services.client.EmployeeClient;
import be.pxl.services.domain.Organization;
import be.pxl.services.domain.dto.OrganizationRequest;
import be.pxl.services.domain.dto.OrganizationResponse;
import be.pxl.services.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationService implements IOrganizationService {

    private final OrganizationRepository organizationRepository;
    private final EmployeeClient employeeClient;
    private final DepartmentClient departmentClient;

    @Override
    public void addOrganization(OrganizationRequest organizationRequest) {
        Organization organization = Organization.builder()
                .name(organizationRequest.getName())
                .address(organizationRequest.getAddress())
                .build();

        organizationRepository.save(organization);
    }

    @Override
    public OrganizationResponse getOrganization(Long id) {
        return organizationRepository.findById(id)
                .map(this::mapToOrganizationResponse)
                .orElseThrow();
    }

    @Override
    public OrganizationResponse getOrganizationWithDepartmentsAndEmployees(Long id) {
        OrganizationResponse organization = organizationRepository.findById(id)
                .map(this::mapToOrganizationResponse)
                .orElseThrow();
        organization.setDepartments(departmentClient.findByOrganization(id));
        organization.setEmployees(employeeClient.findByOrganization(id));
        return organization;
    }

    @Override
    public OrganizationResponse getOrganizationWithEmployees(Long id) {
        OrganizationResponse organization = organizationRepository.findById(id)
                .map(this::mapToOrganizationResponse)
                .orElseThrow();
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
