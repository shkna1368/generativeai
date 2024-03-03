package com.shabab.aiproject.huggingface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class ModelService {

    private final WebClient.Builder webClientBuilder;

Map<String,String> textToTextllms=Map.of("Mistral-7B-Instruct-v0.2","https://api-inference.huggingface.co/models/mistralai/Mistral-7B-Instruct-v0.2",
        "Bloom","https://api-inference.huggingface.co/models/bigscience/bloom",

        "flan-t5-large","https://api-inference.huggingface.co/models/google/flan-t5-large",

        "gemma-7b","https://api-inference.huggingface.co/models/google/gemma-7b");
Map<String,String> textToCodellms=Map.of("Mistral-7B-Instruct-v0.2","https://api-inference.huggingface.co/models/mistralai/Mistral-7B-Instruct-v0.2",
        "starcoder2-15b","https://api-inference.huggingface.co/models/bigcode/starcoder2-15b");


Map<String,String> textToImagellms=Map.of("stable-diffusion-v1-5","https://api-inference.huggingface.co/models/runwayml/stable-diffusion-v1-5",
"playground-v2.5-1024px-aesthetic","https://api-inference.huggingface.co/models/playgroundai/playground-v2.5-1024px-aesthetic"
     );


Map<String,String> textToSeeechllms=Map.of("mms-tts-eng","https://api-inference.huggingface.co/models/facebook/mms-tts-eng");



    @Autowired
    public ModelService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;

    }


    public byte[] sendInferenceWithModel( String input,String modelInferencekey) {
      /*  Optional<Model> modelByIdOpt = modelRepository.findById(modelId);
        Model model = modelByIdOpt.orElseThrow(() -> new RuntimeException("Model not found"));*/

        InferenceRequestToHuggingFace inferenceRequestToHuggingFace = new InferenceRequestToHuggingFace(input);
        try {
            byte[] imageData = webClientBuilder.build()
                    .post()
                    .uri(textToImagellms.get(modelInferencekey))
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

    public byte[] sendInferenceWithSpeechModel( String input,String modelInferencekey) {
      /*  Optional<Model> modelByIdOpt = modelRepository.findById(modelId);
        Model model = modelByIdOpt.orElseThrow(() -> new RuntimeException("Model not found"));*/
Map<String,String> data=Map.of("inputs",input);
        InferenceRequestToHuggingFace inferenceRequestToHuggingFace = new InferenceRequestToHuggingFace(input);
    inferenceRequestToHuggingFace.setParameters(null);
    inferenceRequestToHuggingFace.setOptions(null);
        try {
            byte[] audioData = webClientBuilder.build()
                    .post()
                    .uri(textToSeeechllms.get(modelInferencekey))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + "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")
                    .body(BodyInserters.fromValue(data))
                    .retrieve()
                    .bodyToMono(byte[].class)
                    .block();
            return audioData;
        } catch (Exception e) {
            throw new RuntimeException("Huggingface api call failed");
        }
    }

    public TextModelResponse[] sendInferenceWithTextModel( ModelType modelType,String input,String modelInferencekey) {
      /*  Optional<Model> modelByIdOpt = modelRepository.findById(modelId);
        Model model = modelByIdOpt.orElseThrow(() -> new RuntimeException("Model not found"));*/
      String   modelInferenceUrl=null;
        if(modelType.equals(ModelType.TEXT)) {
            modelInferenceUrl=textToTextllms.get(modelInferencekey);
        }
        else if(modelType.equals(ModelType.CODE)) {
            modelInferenceUrl=textToCodellms.get(modelInferencekey);

        }

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