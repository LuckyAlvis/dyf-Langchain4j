package com.shenzhen.dai.expense.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 消费记录实体类
 */
@Data
@TableName("expense_record")
public class ExpenseRecord {
    
    @TableId(type = IdType.AUTO)
    private Long id;
    
    @TableField("record_date")
    private LocalDate recordDate;
    
    @TableField("expense_detail")
    private String expenseDetail;
    
    @TableField("expense_type")
    private String expenseType;
    
    @TableField("amount")
    private BigDecimal amount;
    
    @TableField("create_time")
    private LocalDateTime createTime;
    
    @TableField("update_time")
    private LocalDateTime updateTime;
} 