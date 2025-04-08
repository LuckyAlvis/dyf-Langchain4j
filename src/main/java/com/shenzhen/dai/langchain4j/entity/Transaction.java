package com.shenzhen.dai.langchain4j.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.shenzhen.dai.langchain4j.enums.TransactionStatus;
import com.shenzhen.dai.langchain4j.enums.TransactionType;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 交易记录实体类
 */
@Data
@TableName("transaction")
public class Transaction {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("type")
    private Integer type;  // 0-收入，1-支出

    @TableField("amount")
    private BigDecimal amount;

    @TableField("category_id")
    private String categoryId;

    @TableField("date")
    private LocalDate date;

    @TableField("description")
    private String description;

    @TableField("status")
    private Integer status;  // 0-已完成，1-待处理

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
    
    // 获取交易类型枚举
    public TransactionType getTypeEnum() {
        return TransactionType.getByCode(this.type);
    }
    
    // 设置交易类型枚举
    public void setTypeEnum(TransactionType transactionType) {
        this.type = transactionType.getCode();
    }
    
    // 获取交易状态枚举
    public TransactionStatus getStatusEnum() {
        return TransactionStatus.getByCode(this.status);
    }
    
    // 设置交易状态枚举
    public void setStatusEnum(TransactionStatus transactionStatus) {
        this.status = transactionStatus.getCode();
    }
}
