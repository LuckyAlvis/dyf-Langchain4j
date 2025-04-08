
根据当前前端项目的看板页面和个人记账模块，以下是需要开发的SpringBoot后端接口清单。

## 一、用户认证接口

### 1. 用户登录
- **URL**: `/api/auth/login`
- **方法**: POST
- **请求参数**:
  ```json
  {
    "username": "用户名/邮箱",
    "password": "密码",
    "remember": true/false
  }
  ```1
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "登录成功",
    "data": {
      "token": "jwt令牌",
      "userId": "用户ID",
      "username": "用户名",
      "avatar": "头像URL"
    }
  }
  ```

### 2. 用户登出
- **URL**: `/api/auth/logout`
- **方法**: POST
- **请求头**: 需要包含Authorization令牌
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "登出成功"
  }
  ```

## 二、看板页面接口

### 1. 获取财务概览数据
- **URL**: `/api/dashboard/overview`
- **方法**: GET
- **请求头**: 需要包含Authorization令牌
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "income": {
        "amount": 12500,
        "trend": 12.5,
        "trendType": "positive"
      },
      "expense": {
        "amount": 8300,
        "trend": -5.2,
        "trendType": "negative"
      },
      "balance": {
        "amount": 45200,
        "trend": 8.3,
        "trendType": "positive"
      },
      "savingTarget": {
        "percentage": 75,
        "status": "已完成"
      }
    }
  }
  ```

### 2. 获取收支趋势图数据
- **URL**: `/api/dashboard/trend`
- **方法**: GET
- **请求参数**: 
  - `period`: 时间周期，可选值：month(月度)、year(年度)
  - `year`: 年份，如2024
- **请求头**: 需要包含Authorization令牌
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "xAxis": ["1月", "3月", "5月", "7月", "9月", "11月"],
      "series": [
        {
          "name": "收入",
          "data": [15000, 12000, 18000, 20000, 25000, 32000]
        },
        {
          "name": "支出",
          "data": [10000, 8000, 15000, 12000, 15000, 18000]
        },
        {
          "name": "结余",
          "data": [5000, 4000, 3000, 8000, 10000, 14000]
        }
      ]
    }
  }
  ```

### 3. 获取支出分类图数据
- **URL**: `/api/dashboard/category`
- **方法**: GET
- **请求参数**: 
  - `period`: 时间周期，可选值：month(月度)、year(年度)
  - `year`: 年份，如2024
  - `month`: 月份，如4（当period为month时需要）
- **请求头**: 需要包含Authorization令牌
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": [
      { "name": "餐饮", "value": 2500, "color": "#5470C6" },
      { "name": "交通", "value": 1800, "color": "#91CC75" },
      { "name": "购物", "value": 3500, "color": "#FAC858" },
      { "name": "娱乐", "value": 1200, "color": "#EE6666" },
      { "name": "医疗", "value": 800, "color": "#73C0DE" },
      { "name": "居住", "value": 3800, "color": "#3BA272" },
      { "name": "教育", "value": 1500, "color": "#FC8452" },
      { "name": "其他", "value": 1000, "color": "#9A60B4" }
    ]
  }
  ```

### 4. 获取最近交易记录
- **URL**: `/api/dashboard/recent-transactions`
- **方法**: GET
- **请求参数**: 
  - `limit`: 返回记录数量，默认为5
- **请求头**: 需要包含Authorization令牌
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": [
      {
        "id": 1,
        "date": "2024-04-08",
        "description": "工资收入",
        "category": "salary",
        "categoryName": "收入",
        "amount": 12500,
        "type": "income",
        "status": "completed"
      },
      {
        "id": 2,
        "date": "2024-04-07",
        "description": "超市购物",
        "category": "shopping",
        "categoryName": "日常支出",
        "amount": 328.50,
        "type": "expense",
        "status": "completed"
      },
      {
        "id": 3,
        "date": "2024-04-06",
        "description": "房租支出",
        "category": "housing",
        "categoryName": "住房",
        "amount": 3500,
        "type": "expense",
        "status": "pending"
      }
    ]
  }
  ```

## 三、个人记账模块接口

### 1. 获取交易记录列表
- **URL**: `/api/transactions`
- **方法**: GET
- **请求参数**: 
  - `page`: 页码，从1开始
  - `size`: 每页记录数
  - `type`: 交易类型，可选值：all(全部)、income(收入)、expense(支出)
  - `category`: 分类，可选值：all(全部)或具体分类ID
  - `minAmount`: 最小金额
  - `maxAmount`: 最大金额
  - `period`: 时间周期，可选值：week(本周)、month(本月)、year(本年)、custom(自定义)
  - `startDate`: 开始日期，当period为custom时需要
  - `endDate`: 结束日期，当period为custom时需要
- **请求头**: 需要包含Authorization令牌
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": {
      "total": 100,
      "pages": 10,
      "current": 1,
      "records": [
        {
          "id": 1,
          "date": "2024-04-08",
          "description": "工资收入",
          "category": "salary",
          "categoryName": "工资",
          "amount": 12500,
          "type": "income",
          "status": "completed"
        },
        {
          "id": 2,
          "date": "2024-04-07",
          "description": "超市购物",
          "category": "shopping",
          "categoryName": "购物",
          "amount": 328.50,
          "type": "expense",
          "status": "completed"
        }
      ]
    }
  }
  ```

### 2. 新增交易记录
- **URL**: `/api/transactions`
- **方法**: POST
- **请求参数**:
  ```json
  {
    "type": "income/expense",
    "amount": 1000,
    "category": "category_id",
    "date": "2024-04-10",
    "description": "交易描述"
  }
  ```
- **请求头**: 需要包含Authorization令牌
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "添加成功",
    "data": {
      "id": 4,
      "date": "2024-04-10",
      "description": "交易描述",
      "category": "category_id",
      "categoryName": "分类名称",
      "amount": 1000,
      "type": "income/expense",
      "status": "completed"
    }
  }
  ```

### 3. 更新交易记录
- **URL**: `/api/transactions/{id}`
- **方法**: PUT
- **请求参数**:
  ```json
  {
    "type": "income/expense",
    "amount": 1200,
    "category": "category_id",
    "date": "2024-04-10",
    "description": "更新后的交易描述"
  }
  ```
- **请求头**: 需要包含Authorization令牌
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "更新成功",
    "data": {
      "id": 4,
      "date": "2024-04-10",
      "description": "更新后的交易描述",
      "category": "category_id",
      "categoryName": "分类名称",
      "amount": 1200,
      "type": "income/expense",
      "status": "completed"
    }
  }
  ```

### 4. 删除交易记录
- **URL**: `/api/transactions/{id}`
- **方法**: DELETE
- **请求头**: 需要包含Authorization令牌
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "删除成功"
  }
  ```

### 5. 获取交易分类列表
- **URL**: `/api/categories`
- **方法**: GET
- **请求参数**: 
  - `type`: 分类类型，可选值：all(全部)、income(收入)、expense(支出)
- **请求头**: 需要包含Authorization令牌
- **响应数据**:
  ```json
  {
    "code": 200,
    "message": "获取成功",
    "data": [
      {
        "id": "salary",
        "name": "工资",
        "type": "income"
      },
      {
        "id": "bonus",
        "name": "奖金",
        "type": "income"
      },
      {
        "id": "food",
        "name": "餐饮",
        "type": "expense"
      },
      {
        "id": "shopping",
        "name": "购物",
        "type": "expense"
      },
      {
        "id": "housing",
        "name": "住房",
        "type": "expense"
      }
    ]
  }
  ```

## 四、数据模型建议

### 1. 用户表(User)
- id: 用户ID
- username: 用户名
- password: 密码(加密存储)
- email: 邮箱
- avatar: 头像URL
- createTime: 创建时间
- updateTime: 更新时间

### 2. 交易记录表(Transaction)
- id: 交易ID
- userId: 用户ID
- type: 交易类型(income/expense)
- amount: 金额
- categoryId: 分类ID
- date: 交易日期
- description: 交易描述
- status: 交易状态(completed/pending)
- createTime: 创建时间
- updateTime: 更新时间

### 3. 分类表(Category)
- id: 分类ID
- name: 分类名称
- type: 分类类型(income/expense)
- icon: 图标
- color: 颜色
- createTime: 创建时间
- updateTime: 更新时间

### 4. 储蓄目标表(SavingTarget)
- id: 目标ID
- userId: 用户ID
- name: 目标名称
- targetAmount: 目标金额
- currentAmount: 当前金额
- startDate: 开始日期
- endDate: 结束日期
- status: 状态
- createTime: 创建时间
- updateTime: 更新时间

## 五、接口通用规范

1. 所有接口返回格式统一为：
```json
{
  "code": 状态码,
  "message": "提示信息",
  "data": 数据对象
}
```

2. 常见状态码：
   - 200: 成功
   - 400: 请求参数错误
   - 401: 未授权
   - 403: 禁止访问
   - 404: 资源不存在
   - 500: 服务器内部错误

3. 所有需要认证的接口都需要在请求头中包含Authorization令牌：
```
Authorization: Bearer {token}
```

4. 分页接口统一使用page和size参数，返回格式为：
```json
{
  "total": 总记录数,
  "pages": 总页数,
  "current": 当前页码,
  "records": [数据列表]
}
```
```
