package com.gaga.sysaiagent.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CountryIdentityInfo {

    @Tool( description = """
        Get Identity Information about a given company including :
        - The Name of the company
        - The country of the company
        - The industry domain of the company
        - The founded year of the company
        """)
    public CompanyIdentityResponse getCompanyIdentity(String companyName) {
        log.error("======== countryIdentityInfo ==== Company {}", companyName);
        return new CompanyIdentityResponse(companyName, "Morocco", "Telecom", 1911);
    }

    public record CompanyIdentityResponse(
            String companyName,
            String country,
            String industryDomain,
            int foundedYear
    ) {}
}

