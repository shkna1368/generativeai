package com.shabab.aiproject.huggingface;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ModelController {
@Autowired
   ModelService modelService;



    @PostMapping("/generateImage")
    public ResponseEntity<byte[]> postInference(@RequestBody InferenceRequest inferenceRequest) {
        try {
            byte[] image = modelService.sendInferenceWithModel( inferenceRequest.getInputs(),"stable-diffusion-v1-5");
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Image generation failed, Try again");
        }






    }

     @PostMapping("/generateText")
    public ResponseEntity<TextModelResponse[]> postInferenceText(@RequestBody InferenceRequest inferenceRequest) {
        try {
          var data = modelService.sendInferenceWithTextModel(inferenceRequest.getModelType() ,inferenceRequest.getInputs(),"Mistral-7B-Instruct-v0.2");
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(data);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "text generation failed, Try again");
        }






    }



}
