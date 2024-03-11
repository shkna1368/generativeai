package com.shabab.aiproject.ollama;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class OllamaChatController {

    private final OllamaChatGenerator ollamaChatGenerator;

    public OllamaChatController(OllamaChatGenerator ollamaChatGenerator) {
        this.ollamaChatGenerator = ollamaChatGenerator;
    }

    @PostMapping(value = "/chat", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map chat(@RequestBody Payload payload){
        return this.ollamaChatGenerator.generate(payload.toString());
    }
}