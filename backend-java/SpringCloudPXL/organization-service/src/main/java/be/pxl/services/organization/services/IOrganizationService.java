package be.pxl.services.organization.services;

import be.pxl.services.organization.domain.dto.OrganizationResponse;

public interface IOrganizationService {
    OrganizationResponse getOrganization(Long id);
}
