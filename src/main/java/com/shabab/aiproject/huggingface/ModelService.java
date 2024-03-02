package com.shabab.aiproject.huggingface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ModelService {

    private final WebClient.Builder webClientBuilder;





    @Autowired
    public ModelService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;

    }


    public byte[] sendInferenceWithModel( String input) {
      /*  Optional<Model> modelByIdOpt = modelRepository.findById(modelId);
        Model model = modelByIdOpt.orElseThrow(() -> new RuntimeException("Model not found"));*/

        InferenceRequestToHuggingFace inferenceRequestToHuggingFace = new InferenceRequestToHuggingFace(input);
        try {
            byte[] imageData = webClientBuilder.build()
                    .post()
                    .uri("https://api-inference.huggingface.co/models/runwayml/stable-diffusion-v1-5")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.IMAGE_JPEG)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + "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")
                    .body(BodyInserters.fromValue(inferenceRequestToHuggingFace))
                    .retrieve()
                    .bodyToMono(byte[].class)
                    .block();
            return imageData;
        } catch (Exception e) {
            throw new RuntimeException("Huggingface api call failed");
        }
    }

    public TextModelResponse[] sendInferenceWithTextModel( String input,String modelInferenceUrl) {
      /*  Optional<Model> modelByIdOpt = modelRepository.findById(modelId);
        Model model = modelByIdOpt.orElseThrow(() -> new RuntimeException("Model not found"));*/

        InferenceRequestToHuggingFace inferenceRequestToHuggingFace = new InferenceRequestToHuggingFace(input);
        try {
            TextModelResponse[] imageData = webClientBuilder.build()
                    .post()
                    .uri(modelInferenceUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + "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")
                    .body(BodyInserters.fromValue(inferenceRequestToHuggingFace))
                    .retrieve()
                    .bodyToMono(TextModelResponse[].class)
                    .block();

            System.out.println(webClientBuilder.toString());
            return imageData;
        } catch (Exception e) {
            throw new RuntimeException("Huggingface api call failed");
        }
    }



}