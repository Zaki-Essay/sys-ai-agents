package com.gaga.sysaiagent.controller;

import com.gaga.sysaiagent.agents.FinancialAnalysisAgent;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiAgentController {
    private final FinancialAnalysisAgent financialAnalysisAgent;

    public AiAgentController(FinancialAnalysisAgent financialAnalysisAgent) {
        this.financialAnalysisAgent = financialAnalysisAgent;
    }

    @GetMapping(value = "/financialAnalysis", produces = MediaType.TEXT_MARKDOWN_VALUE)
    public String askAgent(String company){
        return financialAnalysisAgent.financialAnalysisReport(company);
    }
}
