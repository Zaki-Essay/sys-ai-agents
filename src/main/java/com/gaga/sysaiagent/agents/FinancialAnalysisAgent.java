package com.gaga.sysaiagent.agents;

import com.gaga.sysaiagent.annotations.AiAgent;
import com.gaga.sysaiagent.services.PromptLoaderService;
import com.gaga.sysaiagent.tools.AdditionalFinancialInfos;
import com.gaga.sysaiagent.tools.CountryIdentityInfo;
import com.gaga.sysaiagent.tools.FinancialDataTool;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@BrowserCallable
@AnonymousAllowed
@Service
public class FinancialAnalysisAgent {
    private final PromptLoaderService promptLoader;
    private final ChatClient chatClient;
    private final FinancialDataTool financialDataTool = new FinancialDataTool();
    private final CountryIdentityInfo countryIdentityInfo = new CountryIdentityInfo();
    private final AdditionalFinancialInfos additionalFinancialInfos = new AdditionalFinancialInfos();




    public FinancialAnalysisAgent(PromptLoaderService promptLoader, ChatClient.Builder chatClient) {
        this.promptLoader = promptLoader;
        this.chatClient = chatClient
                .build();
    }

    public String financialAnalysisReport(String companyName){
        String systemPrompt = promptLoader.loadPrompt("financial-analysis-system.txt");
        String userPrompt = promptLoader.loadPromptWithVariables("financial-analysis-user.txt",
                Map.of("companyName", companyName));

        return Objects.requireNonNull(chatClient.prompt()
                .system(systemPrompt)
                .user(userPrompt)
                .tools(financialDataTool, countryIdentityInfo, additionalFinancialInfos)
                .call().content());
    }
}