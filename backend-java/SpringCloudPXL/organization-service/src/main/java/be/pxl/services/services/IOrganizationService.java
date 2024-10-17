package be.pxl.services.services;

import be.pxl.services.domain.dto.OrganizationRequest;
import be.pxl.services.domain.dto.OrganizationResponse;

public interface IOrganizationService {

    void addOrganization(OrganizationRequest organizationRequest);
    OrganizationResponse getOrganization(Long id);
    OrganizationResponse getOrganizationWithDepartmentsAndEmployees(Long id);
    OrganizationResponse getOrganizationWithEmployees(Long id);
}
