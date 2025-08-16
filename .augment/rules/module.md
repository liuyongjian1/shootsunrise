---
type: "always_apply"
description: "数据表模块分配规范"
---

# 数据表模块分配规范

## 📊 模块分配原则

### 🔧 Core 模块（数据访问层）
**职责：** 统一管理所有数据访问逻辑，提供数据仓储服务

**包含内容：**
- **所有 Entity 实体类** - 对应数据库表结构
- **所有 Mapper 接口** - 数据访问接口
- **所有 Repository 实现** - 仓储层逻辑

**Core 模块包含所有数据表：**
- `users` - 用户基础信息表
- `photographer_profiles` - 摄影师认证信息表
- `user_roles` - 用户角色关联表
- `portfolios` - 作品集表
- `portfolio_categories` - 作品分类表
- `portfolio_tags` - 作品标签表
- `portfolio_tag_relations` - 作品标签关联表
- `service_packages` - 服务套餐表
- `bookings` - 预约表
- `orders` - 订单表
- `reviews` - 评价表
- `favorites` - 收藏记录表
- `user_likes` - 用户点赞记录表
- `user_follows` - 用户关注关系表
- `promotions` - 营销活动表
- `promotion_participants` - 营销活动参与记录表
- `promotion_statistics` - 营销活动统计表
- `admin_users` - 管理员用户表
- `audit_logs` - 内容审核日志表
- `system_statistics` - 系统统计数据表
- `system_configs` - 系统配置表
- `operation_logs` - 操作日志表
- `file_uploads` - 文件上传记录表
- `notifications` - 系统通知表

### 📱 Miniapp 模块（小程序业务层）
**职责：** 实现小程序端的业务逻辑，面向C端用户

**包含内容：**
- **Service 接口和实现** - 小程序业务逻辑
- **Controller 控制器** - 小程序API接口
- **DTO/VO/BO 模型** - 小程序数据传输对象

**业务范围：**
- 用户认证与管理
- 作品展示与浏览
- 摄影师服务预订
- 订单管理与支付
- 用户互动功能
- 营销活动参与

### 🖥️ Admin 模块（管理后台业务层）
**职责：** 实现管理后台的业务逻辑，面向B端管理员

**包含内容：**
- **Service 接口和实现** - 管理后台业务逻辑
- **Controller 控制器** - 管理后台API接口
- **DTO/VO/BO 模型** - 管理后台数据传输对象

**业务范围：**
- 管理员认证与权限
- 用户管理与审核
- 内容审核与管理
- 系统配置管理
- 数据统计分析
- 运营管理功能

## 🏗️ 模块依赖关系

### 依赖层次
```
┌─────────────┐    ┌─────────────┐
│   Miniapp   │    │    Admin    │
│   Module    │    │   Module    │
└─────────────┘    └─────────────┘
       │                   │
       └───────┬───────────┘
               │
        ┌─────────────┐
        │    Core     │
        │   Module    │
        └─────────────┘
```

### 依赖规则
1. **Miniapp** 依赖 **Core** 模块
2. **Admin** 依赖 **Core** 模块  
3. **Miniapp** 和 **Admin** 之间相对独立，不直接依赖

## 📋 实体类创建规范

### Core 模块实体类
**位置：** `shoot-sunrise-core/src/main/java/com/shootsunrise/core/entity/`

**命名规范：**
- 实体类名使用大驼峰命名法
- 对应数据库表名（下划线转驼峰）
- 必须继承 `BaseEntity`

**示例：**
```java
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("users")
public class User extends BaseEntity {
    // 字段定义...
}
```

### Miniapp 模块实体类
**位置：** `shoot-sunrise-miniapp/src/main/java/com/shootsunrise/miniapp/entity/`

**依赖关系：**
- 可以引用 Core 模块的实体类
- 不能引用 Admin 模块的实体类

### Admin 模块实体类
**位置：** `shoot-sunrise-admin/src/main/java/com/shootsunrise/admin/entity/`

**依赖关系：**
- 可以引用 Core 模块的实体类
- 不能引用 Miniapp 模块的实体类

## 🔧 Mapper 接口创建规范

### 继承规范
所有 Mapper 接口必须继承 `BaseMapperX<T>`：

```java
public interface UserMapper extends BaseMapperX<User> {
    // 自定义查询方法...
}
```

### 模块分配
- **Core 模块：** 所有表对应的 Mapper 接口都在 Core 模块中
- **Miniapp 模块：** 不包含 Mapper 接口
- **Admin 模块：** 不包含 Mapper 接口

## 🏛️ 仓储层架构规范

### Core 模块仓储层职责
**Core 模块只实现仓储层（Repository）逻辑，不实现业务逻辑层（Service）**

**Core 模块包含：**
- `Entity` - 实体类
- `Mapper` - 数据访问接口
- `Repository` - 仓储层实现

**Core 模块不包含：**
- `Service` - 业务逻辑层
- `Controller` - 控制器层
- `DTO/VO/BO` - 业务模型

### 仓储层实现规范
```java
// Core 模块仓储层示例
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public PageResult<User> findPage(UserPageRequest request) {
        return userMapper.selectPage(request, buildQueryWrapper(request));
    }

    // 其他数据访问方法...
}
```

### 其他模块调用规范
**Miniapp 和 Admin 模块的 ServiceImpl 直接调用 Core 模块的 Repository**

```java
// Miniapp/Admin 模块业务层示例
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository; // 直接注入 Core 模块的 Repository

    @Override
    public UserVO getUserInfo(Long userId) {
        // 1. 调用 Core 模块仓储层获取数据
        User user = userRepository.findById(userId);

        // 2. 业务逻辑处理
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        // 3. 数据转换
        return BeanUtil.copyProperties(user, UserVO.class);
    }
}
```

## 🎯 设计优势

### 1. 模块职责清晰
- 每个模块专注于特定的业务领域
- 避免功能混乱和职责不明

### 2. 依赖关系合理
- 避免循环依赖
- 保持架构清晰
- 便于独立开发和测试

### 3. 扩展性良好
- 便于后续功能扩展
- 支持模块独立部署
- 易于维护和升级

### 4. 复用性高
- Core 模块提供公共服务
- 避免重复开发
- 统一数据访问层

## ⚠️ 注意事项

1. **严格遵循模块分配**：不得随意将业务逻辑分配到错误的模块
2. **依赖方向控制**：下层模块不能依赖上层模块
3. **实体类位置**：所有实体类都必须放在 Core 模块的 entity 包下
4. **Core 模块职责限制**：Core 模块只实现 Repository 层，不实现 Service 层
5. **跨模块数据访问**：其他模块的 ServiceImpl 直接注入并调用 Core 模块的 Repository
6. **禁止直接调用 Mapper**：业务层不得直接调用 Mapper，必须通过 Repository 层
7. **模块业务划分**：模块划分只涉及 Service 和 Controller，所有数据访问层都在 Core 中
