package com.gaga.sysaiagent.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Slf4j
public class FinancialDataTool {

    @Tool ( description = """
        Get financial data about the company including :
        - The turnover of the last 3 years
        - The profit of the last 3 years
        - The value of the stock in the last 7 days
        """)
    public FinancialDataResponse getFinancialData(String companyName) {
        log.error("financialDataTool invocation  company {}", companyName);
        return new FinancialDataResponse(
                new double[]{1000000, 2000000, 3000000},
                new double[]{10000, 20000, 30000},
                new double[]{450, 460, 480, 480, 320, 340, 250}
        );
    }

    public record FinancialDataResponse(
            double[] turnover,
            double[] profit,
            double[] stock
    ) {}
}
