package com.shenzhen.dai.langchain4j.controller;

import com.shenzhen.dai.langchain4j.dto.resp.ApiResponse;
import com.shenzhen.dai.langchain4j.entity.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "分类接口", description = "交易分类相关接口")
public class CategoryController {

    @GetMapping
    @Operation(summary = "获取分类列表", description = "获取交易分类列表")
    public ApiResponse<List<Category>> getCategories(
            @Parameter(description = "分类类型：all-全部，income-收入，expense-支出")
            @RequestParam(defaultValue = "all") String type) {
        List<Category> categories = new ArrayList<>();
        return ApiResponse.success("获取成功", categories);
    }
}
