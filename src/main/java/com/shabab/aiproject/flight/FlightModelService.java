package com.shabab.aiproject.flight;

import com.google.gson.Gson;
import com.shabab.aiproject.huggingface.InferenceRequestToHuggingFace;
import com.shabab.aiproject.huggingface.ModelType;
import com.shabab.aiproject.huggingface.TextModelResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FlightModelService {

    private final WebClient.Builder webClientBuilder;
    private final FlightService flightService;

Map<String,String> textToTextllms=Map.of("Mistral-7B-Instruct-v0.2","https://api-inference.huggingface.co/models/mistralai/Mistral-7B-Instruct-v0.2",
        "Bloom","https://api-inference.huggingface.co/models/bigscience/bloom",

        "flan-t5-large","https://api-inference.huggingface.co/models/google/flan-t5-large",

        "gemma-7b","https://api-inference.huggingface.co/models/google/gemma-7b");

    @Autowired
    public FlightModelService(WebClient.Builder webClientBuilder,FlightService flightService) {
        this.webClientBuilder = webClientBuilder;

        this.flightService=flightService;

    }


    public List<Flight> sendInferenceWithTextModel( ModelType modelType,String input,String modelInferencekey) {


        var prompt= "You are flight support."+input+ """
                .In your response, I only want you to include  below :
                {
                "action":action,
                from,
                to,
                date,
                passengerName,
                passport
                }
                """;




      /*  Optional<Model> modelByIdOpt = modelRepository.findById(modelId);
        Model model = modelByIdOpt.orElseThrow(() -> new RuntimeException("Model not found"));*/

          var  modelInferenceUrl=textToTextllms.get(modelInferencekey);



        InferenceRequestToHuggingFace inferenceRequestToHuggingFace = new InferenceRequestToHuggingFace(prompt);
        try {
            TextModelResponse[] data = webClientBuilder.build()
                    .post()
                    .uri(modelInferenceUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + "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")
                    .body(BodyInserters.fromValue(inferenceRequestToHuggingFace))
                    .retrieve()
                    .bodyToMono(TextModelResponse[].class)
                    .block();





            return process(data);
        } catch (Exception e) {
            throw new RuntimeException("Huggingface api call failed");
        }
    }



    public List<Flight> process(TextModelResponse[] textModelResponses) {

        List<Flight> flightList=new ArrayList<>();
String result="";
        for (TextModelResponse textModelResponse : textModelResponses) {

            result=result + textModelResponse.getGenerated_text();

        }

        System.out.println("result generated is "+result);
      // result= result.replace("\n", "");

        JSONObject jsonObject= new JSONObject(result );




       String action= (String) jsonObject.get("action");

       if (action.equalsIgnoreCase("book")||action.equalsIgnoreCase("reserve")||action.equalsIgnoreCase("bookFlight")||action.equalsIgnoreCase("reserveFlight")) {

      flightService.save(jsonObject);
       }
         if (action.equals("cancel")||action.equalsIgnoreCase("delete")||action.equals("cancelFlight")){

           flightService.delete(jsonObject);
       }
         if (action.equalsIgnoreCase("search")||action.equalsIgnoreCase("find")||action.equalsIgnoreCase("searchFlight")||action.equalsIgnoreCase("findFlight")||action.equalsIgnoreCase("RetrieveFlightInformation")||action.equalsIgnoreCase("RetrieveFlightInformation")||action.equalsIgnoreCase("retrieve")||action.equalsIgnoreCase("retrieved")) {

        return            flightService.search(jsonObject);
       }

        return flightService.getAll();

    }

    public List<Flight> getAll(){

        return flightService.getAll();
    }


}