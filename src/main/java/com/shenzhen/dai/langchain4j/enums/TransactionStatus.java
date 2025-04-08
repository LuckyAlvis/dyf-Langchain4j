package com.shenzhen.dai.langchain4j.enums;

import lombok.Getter;

/**
 * 交易状态枚举
 */
@Getter
public enum TransactionStatus {
    COMPLETED(0, "已完成"),
    PENDING(1, "待处理");

    private final Integer code;
    private final String desc;

    TransactionStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static TransactionStatus getByCode(Integer code) {
        for (TransactionStatus status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }
}