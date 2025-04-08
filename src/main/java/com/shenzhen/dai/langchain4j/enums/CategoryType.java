package com.shenzhen.dai.langchain4j.enums;

import lombok.Getter;

/**
 * 分类类型枚举
 */
@Getter
public enum CategoryType {
    INCOME(0, "收入"),
    EXPENSE(1, "支出");

    private final int code;
    private final String desc;

    CategoryType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CategoryType getByCode(int code) {
        for (CategoryType type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }
}