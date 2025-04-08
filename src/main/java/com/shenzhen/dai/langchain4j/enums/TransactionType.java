package com.shenzhen.dai.langchain4j.enums;

import lombok.Getter;

/**
 * 交易类型枚举
 */
@Getter
public enum TransactionType {
    INCOME(0, "收入"),
    EXPENSE(1, "支出");

    private final Integer code;
    private final String desc;

    TransactionType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static TransactionType getByCode(Integer code) {
        for (TransactionType type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}