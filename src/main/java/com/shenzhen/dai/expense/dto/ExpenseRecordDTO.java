package com.shenzhen.dai.expense.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 消费记录数据传输对象
 */
@Data
public class ExpenseRecordDTO {
    
    private Long id;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate recordDate;
    
    private String expenseDetail;
    
    private String expenseType;
    
    private String expenseTypeName;
    
    private BigDecimal amount;
    
    // 扩展字段 - 提取自recordDate
    private Integer month;
    private String weekNumber;
    private String weekday;
} 