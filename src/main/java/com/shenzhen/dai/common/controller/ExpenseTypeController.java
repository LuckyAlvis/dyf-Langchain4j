package com.shenzhen.dai.common.controller;

import com.shenzhen.dai.common.response.ApiResponse;
import com.shenzhen.dai.expense.entity.ExpenseType;
import com.shenzhen.dai.expense.service.ExpenseTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 花销类型控制器
 */
@RestController
@RequestMapping("/api/expense-types")
@Tag(name = "花销类型接口", description = "花销类型相关接口")
public class ExpenseTypeController {

    private final ExpenseTypeService expenseTypeService;

    public ExpenseTypeController(ExpenseTypeService expenseTypeService) {
        this.expenseTypeService = expenseTypeService;
    }

    @GetMapping
    @Operation(summary = "获取所有花销类型")
    public ApiResponse<List<ExpenseType>> getAllExpenseTypes() {
        List<ExpenseType> types = expenseTypeService.getAllExpenseTypes();
        return ApiResponse.success("获取成功", types);
    }
}
