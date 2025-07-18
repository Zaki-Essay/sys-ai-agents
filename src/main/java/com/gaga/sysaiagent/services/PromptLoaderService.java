package com.gaga.sysaiagent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PromptLoaderService {

    @Autowired
    private ResourceLoader resourceLoader;

    private final Map<String, String> promptCache = new ConcurrentHashMap<>();

    public String loadPrompt(String promptPath) {
        return promptCache.computeIfAbsent(promptPath, path -> {
            try {
                Resource resource = resourceLoader.getResource("classpath:prompts/" + path);
                return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load prompt: " + path, e);
            }
        });
    }

    public String loadPromptWithVariables(String promptPath, Map<String, String> variables) {
        String template = loadPrompt(promptPath);
        String result = template;

        for (Map.Entry<String, String> entry : variables.entrySet()) {
            result = result.replace("{" + entry.getKey() + "}", entry.getValue());
        }

        return result;
    }
}
