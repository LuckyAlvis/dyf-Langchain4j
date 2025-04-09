package com.shenzhen.dai.expense.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 消费记录查询参数
 */
@Data
public class ExpenseRecordQueryDTO {

    private Integer page;
    private Integer size;

    private String expenseType;

    private BigDecimal minAmount;
    private BigDecimal maxAmount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private Integer month;
}
