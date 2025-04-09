-- 创建记账数据库
CREATE DATABASE IF NOT EXISTS langchain4j CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE langchain4j;

-- 创建消费记录表
CREATE TABLE IF NOT EXISTS `expense_record`
(
    `id`             BIGINT         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `record_date`    DATE           NOT NULL COMMENT '记录日期',
    `expense_detail` VARCHAR(100)   NOT NULL COMMENT '花销明细',
    `expense_type`   VARCHAR(50)    NOT NULL COMMENT '花销类型',
    `amount`         DECIMAL(10, 2) NOT NULL COMMENT '金额',
    `create_time`    DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_record_date` (`record_date`),
    INDEX `idx_expense_type` (`expense_type`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
    COMMENT = '消费记录表';

-- 创建花销类型表
CREATE TABLE IF NOT EXISTS `expense_type`
(
    `id`          VARCHAR(50) NOT NULL COMMENT '类型ID',
    `name`        VARCHAR(50) NOT NULL COMMENT '类型名称',
    `description` VARCHAR(100) COMMENT '描述',
    `create_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
    COMMENT = '花销类型表';

-- 初始化基础花销类型数据
INSERT INTO `expense_type` (`id`, `name`, `description`)
VALUES ('transport', '交通', '交通相关支出，包括共享单车、高速费、地铁、停车费、洗车等'),
       ('meal', '工作餐', '工作期间的餐饮消费'),
       ('communication', '通信', '通信相关支出，如充电宝等'),
       ('social', '社交', '社交活动相关支出'),
       ('housing', '居住', '住房相关支出，如房租等')
ON DUPLICATE KEY UPDATE `name`        = VALUES(`name`),
                        `description` = VALUES(`description`),
                        `update_time` = CURRENT_TIMESTAMP;
