package com.shenzhen.dai.expense.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shenzhen.dai.common.response.PageResult;
import com.shenzhen.dai.expense.dto.ExpenseRecordDTO;
import com.shenzhen.dai.expense.dto.ExpenseRecordQueryDTO;
import com.shenzhen.dai.expense.entity.ExpenseRecord;
import com.shenzhen.dai.expense.entity.ExpenseType;
import com.shenzhen.dai.expense.mapper.ExpenseRecordMapper;
import com.shenzhen.dai.expense.mapper.ExpenseTypeMapper;
import com.shenzhen.dai.expense.service.ExpenseRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 消费记录服务实现类
 */
@Service
public class ExpenseRecordServiceImpl implements ExpenseRecordService {

    private final ExpenseRecordMapper expenseRecordMapper;
    private final ExpenseTypeMapper expenseTypeMapper;

    public ExpenseRecordServiceImpl(ExpenseRecordMapper expenseRecordMapper, ExpenseTypeMapper expenseTypeMapper) {
        this.expenseRecordMapper = expenseRecordMapper;
        this.expenseTypeMapper = expenseTypeMapper;
    }

    @Override
    public ExpenseRecordDTO addExpenseRecord(ExpenseRecordDTO recordDTO) {
        ExpenseRecord record = new ExpenseRecord();
        BeanUtils.copyProperties(recordDTO, record);

        expenseRecordMapper.insert(record);

        recordDTO.setId(record.getId());
        return convertToDTO(record);
    }

    @Override
    public List<ExpenseRecordDTO> getAllExpenseRecords(ExpenseRecordQueryDTO queryDTO) {
        LambdaQueryWrapper<ExpenseRecord> wrapper = buildQueryWrapper(queryDTO);

        List<ExpenseRecord> records = expenseRecordMapper.selectList(wrapper);
        return convertToDTOList(records);
    }

    @Override
    public PageResult<ExpenseRecordDTO> getExpenseRecordsByPage(ExpenseRecordQueryDTO queryDTO) {
        LambdaQueryWrapper<ExpenseRecord> wrapper = buildQueryWrapper(queryDTO);

        Page<ExpenseRecord> page = new Page<>(queryDTO.getPage(), queryDTO.getSize());
        Page<ExpenseRecord> recordPage = expenseRecordMapper.selectPage(page, wrapper);

        List<ExpenseRecordDTO> records = convertToDTOList(recordPage.getRecords());

        return PageResult.of(
                records,
                recordPage.getTotal(),
                (int) recordPage.getCurrent(),
                (int) recordPage.getSize()
        );
    }

    private LambdaQueryWrapper<ExpenseRecord> buildQueryWrapper(ExpenseRecordQueryDTO queryDTO) {
        LambdaQueryWrapper<ExpenseRecord> wrapper = new LambdaQueryWrapper<>();

        // 按类型查询
        if (queryDTO.getExpenseType() != null && !queryDTO.getExpenseType().isEmpty()) {
            wrapper.eq(ExpenseRecord::getExpenseType, queryDTO.getExpenseType());
        }

        // 按金额范围查询
        if (queryDTO.getMinAmount() != null) {
            wrapper.ge(ExpenseRecord::getAmount, queryDTO.getMinAmount());
        }
        if (queryDTO.getMaxAmount() != null) {
            wrapper.le(ExpenseRecord::getAmount, queryDTO.getMaxAmount());
        }

        // 按日期范围查询
        if (queryDTO.getStartDate() != null) {
            wrapper.ge(ExpenseRecord::getRecordDate, queryDTO.getStartDate());
        }
        if (queryDTO.getEndDate() != null) {
            wrapper.le(ExpenseRecord::getRecordDate, queryDTO.getEndDate());
        }

        // 按月份查询
        if (queryDTO.getMonth() != null) {
            wrapper.apply("MONTH(record_date) = {0}", queryDTO.getMonth());
        }

        // 按时间倒序
        wrapper.orderByDesc(ExpenseRecord::getRecordDate);

        return wrapper;
    }

    private List<ExpenseRecordDTO> convertToDTOList(List<ExpenseRecord> records) {
        // 获取所有类型
        List<ExpenseType> types = expenseTypeMapper.selectList(null);
        Map<String, String> typeNameMap = types.stream()
                .collect(Collectors.toMap(ExpenseType::getId, ExpenseType::getName));

        return records.stream().map(record -> {
            ExpenseRecordDTO dto = convertToDTO(record);
            // 设置类型名称
            dto.setExpenseTypeName(typeNameMap.getOrDefault(record.getExpenseType(), "未知类型"));
            return dto;
        }).collect(Collectors.toList());
    }

    private ExpenseRecordDTO convertToDTO(ExpenseRecord record) {
        ExpenseRecordDTO dto = new ExpenseRecordDTO();
        BeanUtils.copyProperties(record, dto);

        // 设置额外信息
        LocalDate date = record.getRecordDate();
        if (date != null) {
            // 设置月份
            dto.setMonth(date.getMonthValue());

            // 设置周数
            int weekOfYear = date.get(WeekFields.of(Locale.getDefault()).weekOfYear());
            dto.setWeekNumber("全年第" + weekOfYear + "周");

            // 设置星期几
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            String[] weekdays = {"一", "二", "三", "四", "五", "六", "日"};
            dto.setWeekday("星期" + weekdays[dayOfWeek.getValue() - 1]);
        }

        return dto;
    }
}
