package com.example;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.junit.jupiter.api.Test;

public class TestChat {

    /**
     * 测试chatgpt
     */
    @Test
    void test01() {
        ChatLanguageModel model = OpenAiChatModel
                .builder()
                .apiKey("demo")
                .modelName("gpt-4o-mini")
                .build();

        String answer = model.chat("你好，你是谁？");
        System.out.println(answer);
    }

    /**
     * 测试ollama
     */
    @Test
    void test02() {
        ChatLanguageModel model = OllamaChatModel
                .builder()
                .baseUrl("http://localhost:11434")
                .modelName("deepseek-r1:7b")
                .build();

        String answer = model.chat("你好，你是谁？");
        System.out.println(answer);
    }


}
