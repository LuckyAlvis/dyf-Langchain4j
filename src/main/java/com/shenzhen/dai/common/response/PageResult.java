package com.shenzhen.dai.common.response;

import lombok.Data;

import java.util.List;

/**
 * 分页结果包装类
 */
@Data
public class PageResult<T> {

    private Long total;
    private Integer pages;
    private Integer current;
    private List<T> records;

    public static <T> PageResult<T> of(List<T> records, Long total, Integer current, Integer size) {
        PageResult<T> result = new PageResult<>();
        result.setRecords(records);
        result.setTotal(total);
        result.setCurrent(current);
        result.setPages((int) Math.ceil((double) total / size));
        return result;
    }
}
