package com.shenzhen.dai.langchain4j.controller;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 你好
 * @author: daiyifan
 * @create: 2025-04-07 11:24
 */
@RestController
@Slf4j
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        log.info("Hello, world");
        return "Hello, World!";
    }

    /**
     * 聊天接口
     * @param question 问题参数
     * @return 包含回答的响应
     */
    @GetMapping("/chat")
    public Map<String, String> chat(@RequestParam String question) {
        log.info("开始处理聊天请求");
        log.info("收到问题: {}", question);

        ChatLanguageModel model = OpenAiChatModel
                .builder()
                .apiKey("demo")
                .modelName("gpt-4o-mini")
                .build();

        String answer = model.chat(question);
        log.info("生成回答: {}", answer);

        Map<String, String> response = new HashMap<>();
        response.put("answer", answer);

        log.info("聊天请求处理完成");
        return response;
    }
}
