package be.pxl.services.organization.services;

import be.pxl.services.organization.domain.Organization;
import be.pxl.services.organization.domain.dto.OrganizationResponse;
import be.pxl.services.organization.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationService implements IOrganizationService {
    private final OrganizationRepository organizationRepository;

    @Override
    public OrganizationResponse getOrganization(Long id) {
        return organizationRepository.findById(id)
                .map(this::mapToOrganizationResponse)
                .orElseThrow();
    }

    private OrganizationResponse mapToOrganizationResponse(Organization organization) {
        return OrganizationResponse.builder()
                .name(organization.getName())
                .address(organization.getAddress())
                .build();
    }
}
