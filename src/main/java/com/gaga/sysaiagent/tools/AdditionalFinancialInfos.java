package com.gaga.sysaiagent.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Slf4j
public class AdditionalFinancialInfos {

    @Tool(description = "Get additional financial information about the company in last years")
    public AdditionalFinancialResponse getAdditionalFinancialInfo(String companyName) {
        log.error("AdditionalFinancialInfos Tool => Company {}", companyName);
        return new AdditionalFinancialResponse("The number of subscribers is the very upward trend in the last years");
    }

    public record AdditionalFinancialResponse(String additionalFinancialInfos) {}
}
