package com.shenzhen.dai.expense.service;

import com.shenzhen.dai.common.response.PageResult;
import com.shenzhen.dai.expense.dto.ExpenseRecordDTO;
import com.shenzhen.dai.expense.dto.ExpenseRecordQueryDTO;

import java.util.List;

/**
 * 消费记录服务接口
 */
public interface ExpenseRecordService {

    /**
     * 添加消费记录
     */
    ExpenseRecordDTO addExpenseRecord(ExpenseRecordDTO recordDTO);

    /**
     * 查询所有消费记录（不分页）
     */
    List<ExpenseRecordDTO> getAllExpenseRecords(ExpenseRecordQueryDTO queryDTO);

    /**
     * 分页查询消费记录
     */
    PageResult<ExpenseRecordDTO> getExpenseRecordsByPage(ExpenseRecordQueryDTO queryDTO);
}
