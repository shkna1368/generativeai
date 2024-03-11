package com.shabab.aiproject.ollama;

import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OllamaChatGenerator {

    private final OllamaChatClient ollamaChatClient;

    public OllamaChatGenerator(OllamaChatClient ollamaChatClient) {
        this.ollamaChatClient = ollamaChatClient;
    }

    public Map generate(String message) {
        return Map.of("generation", this.ollamaChatClient.call(message));
    }
}