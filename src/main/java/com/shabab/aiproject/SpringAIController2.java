/*
package com.shabab.aiproject;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

*/
/**
 * @author mhmdz
 * Created By Zeeshan on 17-11-2023
 * @project spring-ai
 *//*


@RestController
@RequestMapping("/api/v2")
public class SpringAIController2 {


    @Autowired(required = true)
     ChatClient chatClient;



    @GetMapping("/learn/{topic}")
    public String getTopic(@PathVariable("topics") String topic) {

        //'topic' is a placeholder in the prompt
        PromptTemplate promptTemplate = new PromptTemplate("""
        I just started learning Spring. Can you provide me 
        fundamentals of {topic} to get started?
        Please provide me short and concise details in simple language.
      """);
        promptTemplate.add("topic", topic);
return chatClient.call(promptTemplate.create().getContents());
       // return chatClient.generate(promptTemplate.create()).getGeneration().getContent();
    }






}*/
