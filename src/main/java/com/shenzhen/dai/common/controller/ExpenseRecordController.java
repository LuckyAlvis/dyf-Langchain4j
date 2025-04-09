package com.shenzhen.dai.common.controller;

import com.shenzhen.dai.common.response.ApiResponse;
import com.shenzhen.dai.common.response.PageResult;
import com.shenzhen.dai.expense.dto.ExpenseRecordDTO;
import com.shenzhen.dai.expense.dto.ExpenseRecordQueryDTO;
import com.shenzhen.dai.expense.service.ExpenseRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 消费记录控制器
 */
@RestController
@RequestMapping("/api/expenses")
@Tag(name = "消费记录接口", description = "消费记录相关接口")
public class ExpenseRecordController {

    private final ExpenseRecordService expenseRecordService;

    public ExpenseRecordController(ExpenseRecordService expenseRecordService) {
        this.expenseRecordService = expenseRecordService;
    }

    @PostMapping
    @Operation(summary = "添加消费记录")
    public ApiResponse<ExpenseRecordDTO> addExpenseRecord(@RequestBody ExpenseRecordDTO recordDTO) {
        ExpenseRecordDTO result = expenseRecordService.addExpenseRecord(recordDTO);
        return ApiResponse.success("添加成功", result);
    }

    @GetMapping("/all")
    @Operation(summary = "获取所有消费记录")
    public ApiResponse<List<ExpenseRecordDTO>> getAllExpenseRecords(ExpenseRecordQueryDTO queryDTO) {
        List<ExpenseRecordDTO> records = expenseRecordService.getAllExpenseRecords(queryDTO);
        return ApiResponse.success("获取成功", records);
    }

    @GetMapping
    @Operation(summary = "分页查询消费记录")
    public ApiResponse<PageResult<ExpenseRecordDTO>> getExpenseRecordsByPage(ExpenseRecordQueryDTO queryDTO) {
        if (queryDTO.getPage() == null) {
            queryDTO.setPage(1);
        }
        if (queryDTO.getSize() == null) {
            queryDTO.setSize(10);
        }

        PageResult<ExpenseRecordDTO> pageResult = expenseRecordService.getExpenseRecordsByPage(queryDTO);
        return ApiResponse.success("获取成功", pageResult);
    }
}
