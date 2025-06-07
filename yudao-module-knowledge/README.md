# yudao-module-knowledge

## 模块简介

知识库模块是一个基于 Spring Boot 的知识管理系统模块，提供文章管理和评论管理功能。该模块采用经典的 MVC 架构，集成了用户权限控制、数据验证、Excel 导出等功能。

## 主要功能

### 文章管理
- 文章的增删改查
- 文章分页查询
- 文章批量删除
- 文章数据 Excel 导出
- 权限控制：创建、更新、删除、查询权限

### 评论管理
- 评论的增删改查
- 评论分页查询
- 评论批量删除
- 评论数据 Excel 导出
- 权限控制：创建、更新、删除、查询权限

## 技术栈

- **核心框架**: Spring Boot
- **权限控制**: Spring Security
- **数据库**: MyBatis Plus
- **缓存**: Redis
- **文档**: Swagger 3
- **数据验证**: Spring Validation
- **Excel处理**: EasyExcel
- **多租户**: 支持租户隔离

## 项目结构

```
yudao-module-knowledge/
├── src/main/java/cn/iocoder/yudao/module/knowledge/
│   ├── api/              # API 接口定义
│   ├── controller/       # 控制器层
│   │   └── admin/        # 管理端控制器
│   │       ├── articles/ # 文章管理控制器
│   │       └── comments/ # 评论管理控制器
│   ├── convert/          # 对象转换器
│   ├── dal/              # 数据访问层
│   │   ├── dataobject/   # 数据对象
│   │   └── mysql/        # MySQL 数据访问
│   ├── enums/            # 枚举类
│   ├── job/              # 定时任务
│   ├── service/          # 业务逻辑层
│   └── util/             # 工具类
├── src/main/resources/
│   └── mapper/           # MyBatis 映射文件
│       ├── articles/     # 文章相关 SQL
│       └── comments/     # 评论相关 SQL
└── src/test/             # 测试代码
```

## 数据库设计

### 文章表 (knowledge_articles)
- `id`: 文章编号（主键）
- `title`: 文章标题
- `content`: 文章内容
- `publish_date`: 发布时间
- `author_id`: 作者ID
- `source_platform`: 来源平台
- 继承 `BaseDO`：包含创建时间、更新时间、创建者、更新者等字段

### 评论表 (knowledge_comments)
- `id`: 评论编号（主键）
- `comment_content`: 评论内容
- `comment_date`: 评论时间
- `user_id`: 评论用户ID
- `article_id`: 关联文章ID
- 继承 `BaseDO`：包含创建时间、更新时间、创建者、更新者等字段

## API 接口

### 文章管理接口

| 方法 | 路径 | 描述 | 权限 |
|------|------|------|------|
| POST | `/knowledge/articles/create` | 创建文章 | `knowledge:articles:create` |
| PUT | `/knowledge/articles/update` | 更新文章 | `knowledge:articles:update` |
| DELETE | `/knowledge/articles/delete` | 删除文章 | `knowledge:articles:delete` |
| DELETE | `/knowledge/articles/delete-list` | 批量删除文章 | `knowledge:articles:delete` |
| GET | `/knowledge/articles/get` | 获取文章详情 | `knowledge:articles:query` |
| GET | `/knowledge/articles/page` | 分页查询文章 | `knowledge:articles:query` |
| GET | `/knowledge/articles/export-excel` | 导出文章Excel | `knowledge:articles:export` |

### 评论管理接口

| 方法 | 路径 | 描述 | 权限 |
|------|------|------|------|
| POST | `/knowledge/comments/create` | 创建评论 | `knowledge:comments:create` |
| PUT | `/knowledge/comments/update` | 更新评论 | `knowledge:comments:update` |
| DELETE | `/knowledge/comments/delete` | 删除评论 | `knowledge:comments:delete` |
| DELETE | `/knowledge/comments/delete-list` | 批量删除评论 | `knowledge:comments:delete` |
| GET | `/knowledge/comments/get` | 获取评论详情 | `knowledge:comments:query` |
| GET | `/knowledge/comments/page` | 分页查询评论 | `knowledge:comments:query` |
| GET | `/knowledge/comments/export-excel` | 导出评论Excel | `knowledge:comments:export` |

## 依赖配置

模块主要依赖：
- `yudao-common`: 公共组件
- `yudao-spring-boot-starter-biz-tenant`: 多租户支持
- `yudao-spring-boot-starter-excel`: Excel 处理
- `yudao-spring-boot-starter-web`: Web 功能
- `yudao-spring-boot-starter-security`: 安全认证
- `yudao-spring-boot-starter-mybatis`: 数据库访问
- `yudao-spring-boot-starter-redis`: 缓存支持

## 开发指南

### 添加新功能
1. 在 `controller` 包下创建对应的控制器
2. 在 `service` 包下创建业务逻辑服务
3. 在 `dal` 包下创建数据访问对象和映射器
4. 在 `resources/mapper` 下创建 SQL 映射文件
5. 配置相应的权限标识

### 权限配置
所有接口都需要配置相应的权限标识，格式为：`knowledge:{模块名}:{操作}`
- `create`: 创建权限
- `update`: 更新权限
- `delete`: 删除权限
- `query`: 查询权限
- `export`: 导出权限

### 数据验证
使用 `@Valid` 注解进行请求参数验证，确保数据的完整性和正确性。

## 注意事项

1. 所有数据库操作都支持多租户隔离
2. 接口返回统一使用 `CommonResult` 包装
3. 支持 API 访问日志记录
4. Excel 导出功能已集成
5. 数据库主键支持多种数据库的自增策略

## 作者

- @aokimi 