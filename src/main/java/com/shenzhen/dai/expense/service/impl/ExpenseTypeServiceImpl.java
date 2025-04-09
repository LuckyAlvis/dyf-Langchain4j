package com.shenzhen.dai.expense.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shenzhen.dai.expense.entity.ExpenseType;
import com.shenzhen.dai.expense.mapper.ExpenseTypeMapper;
import com.shenzhen.dai.expense.service.ExpenseTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 花销类型服务实现类
 */
@Service
public class ExpenseTypeServiceImpl implements ExpenseTypeService {
    
    private final ExpenseTypeMapper expenseTypeMapper;
    
    public ExpenseTypeServiceImpl(ExpenseTypeMapper expenseTypeMapper) {
        this.expenseTypeMapper = expenseTypeMapper;
    }
    
    @Override
    public List<ExpenseType> getAllExpenseTypes() {
        LambdaQueryWrapper<ExpenseType> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(ExpenseType::getId);
        return expenseTypeMapper.selectList(wrapper);
    }
} 