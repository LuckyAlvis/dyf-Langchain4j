package com.example;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestChat {

    private static final Logger logger = LoggerFactory.getLogger(TestChat.class);

    @Test
    void test01() {
        logger.info("开始测试 OpenAI 聊天功能");

        ChatLanguageModel model = OpenAiChatModel
                .builder()
                .apiKey("demo")
                .modelName("gpt-4o-mini")
                .build();

        String question = "你好，你是谁？";
        logger.info("发送问题: {}", question);

        String answer = model.chat(question);
        logger.info("收到回答: {}", answer);

        logger.info("测试完成");
    }
}
