---
type: "always_apply"
description: "测试驱动开发规范"
---

# 测试驱动开发规范

## 核心原则

**测试先行**：在编写任何业务代码之前，必须先编写相应的测试类，采用测试驱动开发（TDD）模式。

## TDD 开发流程

### 1. Red-Green-Refactor 循环
1. **Red（红）**：编写失败的测试用例
2. **Green（绿）**：编写最少的代码使测试通过
3. **Refactor（重构）**：优化代码结构，保持测试通过

### 2. 开发顺序
```
编写测试 → 运行测试（失败）→ 编写实现代码 → 运行测试（通过）→ 重构代码 → 重复循环
```

## 测试分层策略

### 1. 单元测试 (Unit Tests)
- **覆盖范围**：Service层、Repository层、工具类
- **测试框架**：JUnit 5 + Mockito
- **命名规范**：`{ClassName}Test.java`
- **位置**：`src/test/java` 对应包结构下

### 2. 集成测试 (Integration Tests)
- **覆盖范围**：Controller层、数据库交互、外部服务调用
- **测试框架**：Spring Boot Test + TestContainers
- **命名规范**：`{ClassName}IntegrationTest.java`
- **注解**：`@SpringBootTest`、`@AutoConfigureTestDatabase`

### 3. 端到端测试 (E2E Tests)
- **覆盖范围**：完整业务流程
- **测试框架**：RestAssured + TestContainers
- **命名规范**：`{FeatureName}E2ETest.java`

## 测试编写规范

### 1. 测试类结构
```java
/**
 * {功能模块}测试类
 * @author lyj
 * @since 2024-12-20
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserServiceImpl userService;
    
    @BeforeEach
    void setUp() {
        // 测试前置准备
    }
    
    @Test
    @DisplayName("应该成功创建用户")
    void shouldCreateUserSuccessfully() {
        // Given - 准备测试数据
        // When - 执行测试方法
        // Then - 验证测试结果
    }
    
    @AfterEach
    void tearDown() {
        // 测试后清理
    }
}
```

### 2. 测试方法命名
- **格式**：`should{ExpectedBehavior}When{StateUnderTest}`
- **示例**：
  - `shouldReturnUserWhenValidIdProvided()`
  - `shouldThrowExceptionWhenUserNotFound()`
  - `shouldUpdateUserSuccessfullyWhenValidDataProvided()`

### 3. Given-When-Then 模式
```java
@Test
@DisplayName("应该在提供有效ID时返回用户信息")
void shouldReturnUserWhenValidIdProvided() {
    // Given - 准备测试数据
    Long userId = 1L;
    User expectedUser = User.builder()
        .id(userId)
        .username("testuser")
        .build();
    when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));
    
    // When - 执行被测试的方法
    UserDTO result = userService.getUserById(userId);
    
    // Then - 验证结果
    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo(userId);
    assertThat(result.getUsername()).isEqualTo("testuser");
    verify(userRepository).findById(userId);
}
```

## 测试数据管理

### 1. 测试数据构建
- **使用Builder模式**：创建测试对象
- **使用TestDataFactory**：统一管理测试数据
- **避免硬编码**：使用常量或配置文件

### 2. 数据库测试
```java
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class UserRepositoryTest {
    
    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private UserRepository userRepository;
}
```

## Mock 策略

### 1. Mock 原则
- **只Mock外部依赖**：数据库、外部服务、第三方API
- **不Mock被测试类**：避免过度Mock
- **使用真实对象**：简单的值对象和工具类

### 2. Mock 示例
```java
@Mock
private UserRepository userRepository;

@Mock
private EmailService emailService;

@Spy
private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
```

## 测试覆盖率要求

### 1. 覆盖率目标
- **单元测试覆盖率**：≥ 80%
- **集成测试覆盖率**：≥ 60%
- **关键业务逻辑**：≥ 95%

### 2. 覆盖率工具
- **JaCoCo**：代码覆盖率统计
- **SonarQube**：代码质量分析
- **Maven插件**：`jacoco-maven-plugin`

## 测试环境配置

### 1. 测试配置文件
```yaml
# application-test.yml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: create-drop
  profiles:
    active: test
```

### 2. 测试切片注解
- `@WebMvcTest`：测试Controller层
- `@DataJpaTest`：测试Repository层
- `@JsonTest`：测试JSON序列化
- `@MockBean`：Spring上下文中的Mock

## 性能测试

### 1. 基准测试
```java
@Test
@Timeout(value = 2, unit = TimeUnit.SECONDS)
void shouldCompleteWithinTimeLimit() {
    // 性能测试逻辑
}
```

### 2. 压力测试
- **JMeter**：接口压力测试
- **Gatling**：高并发性能测试
- **Spring Boot Actuator**：监控指标

## 测试最佳实践

### 1. 测试独立性
- 每个测试方法独立运行
- 不依赖其他测试的执行顺序
- 清理测试产生的副作用

### 2. 测试可读性
- 使用描述性的测试名称
- 添加`@DisplayName`注解
- 保持测试方法简洁明了

### 3. 测试维护性
- 定期重构测试代码
- 删除过时的测试用例
- 保持测试代码与业务代码同步更新

### 4. 异常测试
```java
@Test
@DisplayName("应该在用户不存在时抛出异常")
void shouldThrowExceptionWhenUserNotFound() {
    // Given
    Long nonExistentId = 999L;
    when(userRepository.findById(nonExistentId)).thenReturn(Optional.empty());
    
    // When & Then
    assertThrows(UserNotFoundException.class, 
        () -> userService.getUserById(nonExistentId));
}
```

## 持续集成中的测试

### 1. CI/CD 流水线
```yaml
# .github/workflows/test.yml
- name: Run Tests
  run: mvn clean test
  
- name: Generate Test Report
  run: mvn jacoco:report
  
- name: Upload Coverage
  uses: codecov/codecov-action@v1
```

### 2. 测试报告
- **Surefire Reports**：单元测试报告
- **Failsafe Reports**：集成测试报告
- **JaCoCo Reports**：覆盖率报告

## 测试工具依赖

### 1. Maven 依赖
```xml
<dependencies>
    <!-- Spring Boot Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    
    <!-- TestContainers -->
    <dependency>
        <groupId>org.testcontainers</groupId>
        <artifactId>junit-jupiter</artifactId>
        <scope>test</scope>
    </dependency>
    
    <!-- RestAssured -->
    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

---

**文档状态：** ✅ 已完成  
**下一步：** 开始TDD模式开发各个模块  
**联系人：** 开发团队