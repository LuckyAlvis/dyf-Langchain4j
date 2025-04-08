package com.shenzhen.dai.langchain4j.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.shenzhen.dai.langchain4j.enums.CategoryType;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 分类实体类
 */
@Data
@TableName("category")
public class Category {

    @TableId(type = IdType.INPUT)
    private String id;

    @TableField("name")
    private String name;

    @TableField("type")
    private Integer type; // 0-收入，1-支出

    @TableField("icon")
    private String icon;

    @TableField("color")
    private String color;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
    
    // 获取类型枚举
    public CategoryType getTypeEnum() {
        return CategoryType.getByCode(this.type);
    }
    
    // 设置类型枚举
    public void setTypeEnum(CategoryType categoryType) {
        this.type = categoryType.getCode();
    }
}
