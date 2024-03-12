package com.shabab.aiproject.bank;

import com.shabab.aiproject.flight.Flight;
import com.shabab.aiproject.flight.FlightService;
import com.shabab.aiproject.huggingface.InferenceRequestToHuggingFace;
import com.shabab.aiproject.huggingface.ModelType;
import com.shabab.aiproject.huggingface.TextModelResponse;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BankModelService {


    private final WebClient.Builder webClientBuilder;
    private final BankService bankService;
    Map<String, String> textToTextllms = Map.of("Mistral-7B-Instruct-v0.2", "https://api-inference.huggingface.co/models/mistralai/Mistral-7B-Instruct-v0.2",
            "Bloom", "https://api-inference.huggingface.co/models/bigscience/bloom",

            "flan-t5-large", "https://api-inference.huggingface.co/models/google/flan-t5-large",

            "gemma-7b", "https://api-inference.huggingface.co/models/google/gemma-7b");

    public BankModelService(WebClient.Builder webClientBuilder, BankService bankService) {
        this.webClientBuilder = webClientBuilder;
        this.bankService = bankService;
    }


    public Map<String, List> sendInferenceWithTextModel(ModelType modelType, String input, String modelInferencekey, TransActionCallBack transActionCallBack) {


        var prompt = "You are bank assistant." + input + """
                .In your response, I only want you to include  with the following JSON and put not assign value with null:
                {
                "action":action,
                "name":name,
                "balance":balance,
                "accountNumber":accountNumber,
                "branchName":branchName,
                "mobileNumber":mobileNumber,
                "fromAccountNumber":fromAccountNumber,
                "toAccountNumber":toAccountNumber,
                "amount":amount,
                "passportNumber":passportNumber,
                "findBy":findBy 
                }
                """;




      /*  Optional<Model> modelByIdOpt = modelRepository.findById(modelId);
        Model model = modelByIdOpt.orElseThrow(() -> new RuntimeException("Model not found"));*/

        var modelInferenceUrl = textToTextllms.get(modelInferencekey);


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


            return process(data, transActionCallBack);


        } catch (Exception e) {
            throw new RuntimeException("Huggingface api call failed");
        }


    }


    public List<BankAccount> getAllBanAccount() {
        return bankService.getAllBanAccount();
    }


    public Map<String, List> process(TextModelResponse[] textModelResponses, TransActionCallBack transActionCallBack) {
        Map<String, List> map = new HashMap<>();


        String result = "";
        for (TextModelResponse textModelResponse : textModelResponses) {

            result = result + textModelResponse.getGenerated_text();

        }

        System.out.println("result generated is " + result);
        // result= result.replace("\n", "");

        JSONObject jsonObject = new JSONObject(result);


        String action = (String) jsonObject.get("action");


        if (action.equalsIgnoreCase("open") || action.equalsIgnoreCase("openAccount") || action.equalsIgnoreCase("openaccount")) {

            map.put("bankAccounts", bankService.save(jsonObject));
        } else if (action.equalsIgnoreCase("transfer")) {

            map.put("bankAccounts", bankService.transfer(jsonObject));
        } else if (action.equalsIgnoreCase("search") || action.equalsIgnoreCase("find") || action.equalsIgnoreCase("searchAccount")
                || action.equalsIgnoreCase("findAccount") || action.equalsIgnoreCase("RetrieveAccountInformation") ||
                action.equalsIgnoreCase("RetrieveAccountInformation") ||
                action.equalsIgnoreCase("retrieve") ||
                action.equalsIgnoreCase("retrieved") ||
                action.equalsIgnoreCase("viewBalance") ||
                action.equalsIgnoreCase("view_balance") ||
                action.equalsIgnoreCase("getBalance") ||
                action.equalsIgnoreCase("get_balance") ||
                action.equalsIgnoreCase("displayBalance")) {

            map.put("bankAccounts", bankService.search(jsonObject));
        } else if (action.equals("close") || action.equalsIgnoreCase("delete") || action.equals("closeAccount")) {

            map.put("bankAccounts", bankService.delete(jsonObject));
        } else if (action.equals("getTransactions") || action.equalsIgnoreCase("transaction") || action.equals("getTranaction")) {

            transActionCallBack.getTransactions(bankService.getTransaction(jsonObject));
            // map.put("transaction", bankService.getTransaction(jsonObject))       ;
        }


        return map;
    }


    public Map<String,List> sendAudi(ModelType modelType,byte[] audio, String modelInferencekey, TransActionCallBack transActionCallBack) {


        try {

           AudioProcessResponse data = webClientBuilder.build()
                    .post()
                    .uri("https://api-inference.huggingface.co/models/openai/whisper-large-v3")
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .accept(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + "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")
                    .body(BodyInserters.fromValue(audio))
                    .retrieve()
                    .bodyToMono(AudioProcessResponse.class)
                    .block();


         return    sendInferenceWithTextModel(ModelType.TEXT, data.getText(), modelInferencekey, transActionCallBack);
        }
        catch (Exception e){

            System.out.println(e);
        }



        return null;

    }
}