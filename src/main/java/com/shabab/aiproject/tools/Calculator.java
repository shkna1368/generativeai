/*
package com.shabab.aiproject.tools;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.dashscope.QwenChatModel;
import dev.langchain4j.model.huggingface.HuggingFaceChatModel;
import dev.langchain4j.model.huggingface.HuggingFaceEmbeddingModel;
import dev.langchain4j.model.huggingface.HuggingFaceLanguageModel;
import dev.langchain4j.model.huggingface.HuggingFaceModelName;
import dev.langchain4j.model.language.LanguageModel;
import dev.langchain4j.model.localai.LocalAiChatModel;
import dev.langchain4j.model.mistralai.MistralAiChatModel;
import dev.langchain4j.model.mistralai.MistralAiChatModelName;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.qianfan.QianfanChatModel;
import dev.langchain4j.model.vertexai.VertexAiGeminiChatModel;
import dev.langchain4j.service.AiServices;

import static dev.langchain4j.model.huggingface.HuggingFaceModelName.TII_UAE_FALCON_7B_INSTRUCT;
import static java.time.Duration.ofSeconds;

class ServiceWithToolsExample {

    // Please also check CustomerSupportApplication and CustomerSupportApplicationTest
    // from spring-boot-example module

    static class Calculator {

        @Tool("Calculates the length of a string")
        int stringLength(String s) {
            System.out.println("Called stringLength with s='" + s + "'");
            return s.length();
        }

        @Tool("Calculates the sum of two numbers")
        int add(int a, int b) {
            System.out.println("Called add with a=" + a + ", b=" + b);
            return a + b;
        }

        @Tool("Calculates the square root of a number")
        double sqrt(int x) {
            System.out.println("Called sqrt with x=" + x);
            return Math.sqrt(x);
        }
    }

    interface Assistant {

        String chat(String userMessage);
    }

     public static void main(String[] args) {
*/
/*         HuggingFaceEmbeddingModel model = HuggingFaceEmbeddingModel.builder()
                 .accessToken(System.getenv("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\""))
                 .modelId("sentence-transformers/all-MiniLM-L6-v2")
                 .build();
        ;*//*

        */
/* ChatLanguageModel hf = HuggingFaceChatModel.builder()
                         .modelId("Mistral-7B-Instruct-v0.1-function-calling-v2")
                                 .accessToken("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")
                 .timeout(ofSeconds(15))
                 .temperature(0.7)
                 .maxNewTokens(20)
                 .waitForModel(true)
                 .build();*//*

          */
/* ChatLanguageModel hf2 = LocalAiChatModel.builder()
                   .modelName("ggml-model-gpt4all-falcon-q4_0.bin")
                         .baseUrl("http://localhost:8080")
                 .timeout(ofSeconds(15))
                 .temperature(0.7)
                 .build();*//*

    */
/*     ChatLanguageModel model = VertexAiGeminiChatModel.builder()
                 .modelName("gemini-pro")
                 .build();
*//*

        */
/* ChatLanguageModel model2 = MistralAiChatModel.builder()
                 .apiKey(System.getenv("MISTRAL_AI_API_KEY")) // Please use your own Mistral AI API key
                 .modelName(MistralAiChatModelName.MISTRAL_SMALL.toString())
                 .logRequests(true)
                 .logResponses(true)
                 .build();
*//*



*/
/*
         QianfanChatModel model3 = QianfanChatModel.builder().modelName("ERNIE-Bot 4.0").temperature(0.7).topP(1.0).maxRetries(1)
                 .apiKey("apiKey")
                 .secretKey("secretKey")
                 .build();*//*

      */
/*   BedrockAnthropicChatModel bedrockChatModel = BedrockAnthropicChatModel
                 .builder()
                 .temperature(0.50f)
                 .maxTokens(300)
                 .region(Region.US_EAST_1)
                 .model(BedrockAnthropicChatModel.Types.AnthropicClaudeV2)
                 .maxRetries(1)
                 .build();*//*


      */
/*   OllamaChatModel model = OllamaChatModel.builder()
                 .baseUrl("http:localhost")
                 .modelName("MODEL")
                 .numPredict(1)
                 .temperature(0.0)
                 .build();*//*



  */
/*       HuggingFaceLanguageModel model = HuggingFaceLanguageModel.builder()
                 .accessToken(System.getenv("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"))
                 .modelId(HuggingFaceModelName.TII_UAE_FALCON_7B_INSTRUCT)
                 .timeout(ofSeconds(15))
                 .temperature(0.7)
                 .maxNewTokens(20)
                 .waitForModel(true)
                 .build();*//*


         ChatLanguageModel model = QwenChatModel.builder()
                 .apiKey("apiKey()")
                 .modelName("modelName")
                 .build();
         Assistant assistant = AiServices.builder(Assistant.class)
                 .chatLanguageModel(model)
               .tools(new Calculator())
                 .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                 .build();





       */
/*

         Assistant assistant = AiServices.builder(Assistant.class)

                 .chatLanguageModel(hf2)

                 .tools(new Calculator())
                 .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                 .build();*//*


         String question = "What is the square root of the sum of the numbers of letters in the words \"hello\" and \"world\"?";

         String answer = assistant.chat(question);

         System.out.println(answer);






         // The square root of the sum of the number of letters in the words "hello" and "world" is approximately 3.162.
     }
}*/
