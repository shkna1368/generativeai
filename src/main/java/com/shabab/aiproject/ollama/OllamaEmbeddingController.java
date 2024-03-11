package com.shabab.aiproject.ollama;

import org.springframework.ai.embedding.Embedding;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class OllamaEmbeddingController {

    private final OllamaEmbeddingsGenerator ollamaEmbeddingsGenerator;

    public OllamaEmbeddingController(OllamaEmbeddingsGenerator ollamaEmbeddingsGenerator) {
        this.ollamaEmbeddingsGenerator = ollamaEmbeddingsGenerator;
    }
    @PostMapping(value = "/embedding1", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> fetchEmbeddings1(@RequestBody Payload payload){
        Embedding embedding = this.ollamaEmbeddingsGenerator.getEmbeddingsWithModelOverride(payload).getResult();
        return ResponseEntity.ok(new Response(embedding.getOutput()));
    }

    @PostMapping(value = "/embedding2", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, EmbeddingResponse>> fetchEmbeddings2(@RequestBody Payload payload){
        Map<String, EmbeddingResponse> embedding = this.ollamaEmbeddingsGenerator.getEmbeddingsWithDefaultModel(payload);
        return ResponseEntity.ok(embedding);
    }
}