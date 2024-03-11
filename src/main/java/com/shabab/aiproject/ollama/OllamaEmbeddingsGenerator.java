package com.shabab.aiproject.ollama;


import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public class OllamaEmbeddingsGenerator {
    private final EmbeddingClient embeddingClient;

    public OllamaEmbeddingsGenerator(EmbeddingClient embeddingClient) {
        this.embeddingClient = embeddingClient;
    }

    public EmbeddingResponse getEmbeddingsWithModelOverride(Payload payload) {
        return this.embeddingClient.call(new EmbeddingRequest(
                List.of(payload.strings()),
                OllamaOptions.create().withModel("mistral")));
    }

    public Map<String, EmbeddingResponse> getEmbeddingsWithDefaultModel(Payload payload) {
        EmbeddingResponse embeddingResponse = this.embeddingClient.embedForResponse(
                List.of(payload.strings()));
        return Map.of("embedding", embeddingResponse);
    }
}