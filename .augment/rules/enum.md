---
type: "manual"
---

# 枚举使用规则

## 规则
对于实体类（Entity），如果其字段的注释中描述了该字段是具有多个固定选择项的，那么应该为该字段创建一个枚举类型。

## 例外情况
- 对于表示"是否"、"开关"等概念的布尔类型字段，不需要创建枚举，应直接使用 `Boolean` 或 `boolean` 类型。

## 枚举类创建指南
1.  **存放位置**: 新创建的枚举类必须存放在对应模块的 `src/main/java/com/hrd/.../enums/` 目录下。
2.  **命名规范**: 枚举类名称应能清晰反映其所代表的业务含义。
3.  **实现接口**: 枚举类需要实现 `BaseEnum` 接口。
4.  **结构示例**:
    ```java
    @Getter
    @AllArgsConstructor
    public enum XxxEnum {
        OPTION_ONE(1, "选项一"),
        OPTION_TWO(2, "选项二"),
        ;
       @JsonValue
       @EnumValue
        private final int code;
        private final String msg;
    }
    ```
5.  **注释**: 必须为枚举类和枚举常量编写清晰的JavaDoc注释。

## 关联对象修改
创建枚举后，需要同步修改相关的BO（Business Object）和VO（Value Object）对象，将原来的字段类型（如 `Integer` 或 `String`）修改为新创建的枚举类型。

