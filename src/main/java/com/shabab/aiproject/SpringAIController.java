package com.shabab.aiproject;




import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.openai.OpenAiEmbeddingClient;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mhmdz
 * Created By Zeeshan on 17-11-2023
 * @project spring-ai
 */

@RestController
@RequestMapping("/api/v1")
public class SpringAIController {

@Autowired
EmbeddingClient embeddingClient;


/*


   //private final    SpringAIService aiService;



   //private final   ChatClient chatClient;
    private final OpenAiChatClient chatClient;


    public SpringAIController(AiClient aiClient,
                              OpenAiChatClient  chatClient) {
     //   this.aiService = aiService;
        this.chatClient = chatClient;
        this.aiClient=aiClient;




    }*/

    @GetMapping("/learn/{topics}")
    public String getTopic(@PathVariable("topics") String topics) {



    /*    var openAiApi = new OpenAiApi(System.getenv("OPENAI_API_KEY"));

        var embeddingClient = new OpenAiEmbeddingClient(openAiApi)
                .withDefaultOptions(OpenAiChatOptions.build()
                        .withModel("text-embedding-ada-002")
                        .withUser("user-6")
                        .build());

        EmbeddingResponse embeddingResponse = embeddingClient
                .embedForResponse(List.of("Hello World", "World is big and salvation is near"));*/

        EmbeddingResponse embeddingResponse = this.embeddingClient.embedForResponse(List.of("tell me jok about " + topics));
        System.out.println(embeddingResponse);

      // var j= chatClient.call("Tell me a joke a bout "+topics);







    //    var openAiApi = new OpenAiApi(System.getenv("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"));

        /*var chatClient = new OpenAiChatClient(openAiApi)
                .withDefaultOptions(OpenAiChatOptions.builder()
                        .withModel("gpt-35-turbo")
                        .withTemperature(0.4f)
                        .withMaxTokens(200)
                        .build());

        ChatResponse response1 = chatClient.call(
                new Prompt("Generate the names of 5 famous pirates."));*/

/*
        ChatResponse response = chatClient.call(
                new Prompt(
                        "Generate the names of 5 famous pirates",
                        OpenAiChatOptions.builder()
                                .withModel("gpt-4-32k")
                                .withTemperature(0.4f)
                                .build()
                ));
*/



        //'topic' is a placeholder in the prompt
/*        PromptTemplate promptTemplate = new PromptTemplate("""
        I just started learning Spring. Can you provide me 
        fundamentals of {topic} to get started?
        Please provide me short and concise details in simple language.
      """);
        promptTemplate.add("topic", topics);
       var x= chatClient.call(promptTemplate.create()).getResult().getOutput().getContent();
        return x;*/
     //   return chatClient.generate(promptTemplate.create()).getGeneration().getContent();

  return null;
    }








   /* @GetMapping("/joke")
    public String getJoke(@RequestParam String topic) {
        return aiService.getJoke(topic);
    }

    @GetMapping("/book")
    public String getBook(@RequestParam(name = "category") String category, @RequestParam(name = "year") String year) {
        return aiService.getBestBook(category, year);
    }

    @GetMapping(value = "/image", produces = "image/jpeg")
    public ResponseEntity<InputStreamResource> getImage(@RequestParam(name = "topic") String topic) throws URISyntaxException {
        return ResponseEntity.ok().body(aiService.getImage(topic));
    }*/
}