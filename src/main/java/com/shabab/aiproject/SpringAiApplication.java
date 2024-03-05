package com.shabab.aiproject;

import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.huggingface.HuggingfaceChatClient;
import org.springframework.ai.model.ModelOptions;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAiApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
       /* HuggingfaceChatClient client = new HuggingfaceChatClient("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", "https://api-inference.huggingface.co/models/mistralai/Mistral-7B-v0.1/");


        Prompt prompt = new Prompt("tell me joke about java");

        ChatResponse response = client.call(prompt);
        System.out.println(response.toString());*/
    }
}