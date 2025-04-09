package com.shenzhen.dai.expense.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 花销类型实体类
 */
@Data
@TableName("expense_type")
public class ExpenseType {

    @TableId(type = IdType.INPUT)
    private String id;

    @TableField("name")
    private String name;

    @TableField("description")
    private String description;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
