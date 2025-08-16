---
type: "always_apply"
description: "globs:"
---
# 项目规范

## 1. 技术栈

*   **Java Development Kit (JDK):** 本项目使用 JDK 17。
*   **Spring Boot:** 核心框架为 Spring Boot 3.3.5。

## 2. API 设计规范

*   所有对外接口设计应严格遵循 **RESTful** 风格。
*   资源命名应使用名词复数。
*   使用标准的 HTTP 方法 (GET, POST, PUT, DELETE, PATCH)。
*   **接口路径 (Path):** 采用小驼峰命名法 (lowerCamelCase)，例如 `/api/userManagement/getUserInfo`。

## 4. 数据库与持久层

*   项目持久层使用 **MyBatis Plus**。
*   对于多表联合查询，推荐使用 `mybatis-plus-join` 插件以简化开发和提高可读性。
*   **查询条件构造:** 在仓储层（Repository）中构造查询条件时，必须使用 **Lambda 表达式** 以避免使用魔法值（如硬编码的数据库字段名），增强代码的类型安全和可维护性。

## 5. 代码规范

*   **依赖注入:** 实现类和控制层（Controller）优先使用 `@Resource` 注解进行属性注入。
*   **实现类注释:** 在 `service` 的实现类中，每个逻辑操作都应有清晰的注释。重要的操作步骤需使用数字进行标示，例如：`// 1. xxx`、`// 2. xxx`。
*   **方法注释:** 所有 `public` 方法都必须包含清晰的 Javadoc 注释，说明方法的功能、参数 (`@param`) 和返回值 (`@return`)。
*   **统一代码风格:** 遵循项目既定的代码格式化规范。
*   **Lombok:** 推荐使用 Lombok 简化代码，例如 `@Data`, `@Slf4j`, `@AllArgsConstructor` 等注解。
*   **类注释模板:** 所有新建的类都应包含标准注释头。其中 `@since` 标签后的日期请使用联网获取的北京时间（即中国标准时间，CST），不要使用本地时间或UTC时间。
    ```java
    /**
     * 中文类名
     * @author  lyj
     * @since {{YYYY-MM-DD}}
     */
    ```
*   **命名规范:**
    *   **通用原则:** 所有命名都应清晰、简洁，做到见名知意。
    *   **类与接口 (Class & Interface):** 采用大驼峰命名法 (UpperCamelCase)。
    *   **方法与变量 (Method & Variable):** 采用小驼峰命名法 (lowerCamelCase)。

## 6. API 文档

*   **Swagger:** 项目 API 文档使用 `knife4j-openapi3` 进行生成和展示。

## 7. 项目分层结构与职责

*   **controller:** 接口层，负责接收前端请求，参数校验，并调用 `service` 层。
*   **service:** 业务逻辑层，负责核心业务处理。
    *   `service` 接口应定义清晰的业务方法。
    *   `impl` 包下存放 `service` 接口的实现类。
    *   只能调用 `repository` 层的方法。
*   **repository:** 防腐层（仓储层），负责数据访问的适配和转换。
    *   隔离业务逻辑与底层数据源。
    *   所有数据库相关的逻辑（如数据转换、复杂查询封装等）都应在此层实现。
    *   只能调用 `mapper` 层的接口。
*   **mapper:** 数据持久层，负责与数据库交互。
    *   Mapper 接口必须继承 `MPJBaseMapper` 以支持联表查询。
    *   所有 Entity 实体类必须继承 `BaseEntity` 以获得通用字段支持（creator、createTime、updater、updateTime、isDelete）。
*   **model:** 视图与业务模型。
    *   `bo` (Business Object): 业务对象，用于 `service` 层内部的数据传输。
    *   `vo` (View Object): 视图对象，用于 `controller` 层返回给前端的数据。
    *   `dto` (Data Transfer Object): 数据传输对象，用于前端传输的参数，主要用于接收前端请求参数和数据校验。
    *   **对象转换规范:** bo、vo、dto、Entity 之间的转换必须使用 `BeanUtil` 进行转换，确保代码的一致性和可维护性。

## 8. 通用规则

*   **配置文件**: 优先使用 `.yml` 格式进行配置，便于层级化管理。
*   **日志记录**: 使用 SLF4J 作为日志门面，配合 Logback 或 Log4j2 进行日志记录。请确保日志输出级别在不同环境（开发、测试、生产）的正确配置。
*   **工具类使用**: 优先使用 `hutool` 库中的工具类。

*   **异常处理**: 使用全局异常处理器来捕获和处理通用异常。业务异常应定义清晰的异常类和错误码，并统一使用抛出。