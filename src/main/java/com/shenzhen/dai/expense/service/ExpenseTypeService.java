package com.shenzhen.dai.expense.service;

import com.shenzhen.dai.expense.entity.ExpenseType;
import java.util.List;

/**
 * 花销类型服务接口
 */
public interface ExpenseTypeService {
    
    /**
     * 获取所有花销类型
     */
    List<ExpenseType> getAllExpenseTypes();
} 