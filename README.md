# nkuwiki 知识共享平台 - 后端服务

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## 1. 项目定位与二次开发说明

本项目 **`nkuwiki-back`** 是 **`nkuwiki` 南开校园知识共享平台**的后端服务。它是在优秀的开源项目 [RuoYi-Vue-Pro](https://gitee.com/zhijiantianya/ruoyi-vue-pro) (由芋道源码团队开发，特此感谢) 的基础上进行的**二次开发**。

二次开发的核心目标是**精准服务于 `nkuwiki` 项目定义的应用场景**，对原项目进行了以下关键调整：

*   **聚焦核心需求**：根据主 `README.md` 中描述的 `nkuwiki` 平台"领域需求描述"，定制和新增了后端业务模块。
*   **数据库重新设计**：依据 `nkuwiki` 的ER图和关系模式，重新设计和实现了数据表结构，以支持用户、文章、课程、资源、评论及其特定的继承关系。
*   **API 接口适配**：调整和开发了符合 `nkuwiki-front` 前端需求的API接口。
*   **功能精简**：移除了 `RuoYi-Vue-Pro` 中与 `nkuwiki` 校园知识共享场景不直接相关的模块 (如工作流、商城、CRM、ERP等)，使后端服务更轻量、更专注。

## 2. 后端服务如何支撑 nkuwiki 应用场景

`nkuwiki-back` 通过以下模块和功能，为 `nkuwiki` 平台的核心应用场景提供支持：

### a. 用户管理 (User Management)
*   **场景需求**：支持学生、教师等不同角色用户的注册与身份管理。
*   **后端实现 (`nkuwiki-module-member`, `yudao-module-system`)**：
    *   提供用户注册、登录、登出接口。
    *   基于 Spring Security 进行认证与授权，管理用户角色（学生、教师）及其权限。
    *   存储和管理用户基本信息、学号/工号、专业/院系等。
    *   支持用户个人信息的查询与修改。

### b. 知识存储 (Knowledge Storage)
*   **场景需求**：存储课程资料、学术文章、校园资讯等多类型知识内容。
*   **后端实现 (`nkuwiki-module-article`)**：
    *   提供文章（包括课程资源）的创建、编辑、发布、删除接口。
    *   支持 Markdown 格式内容的存储和检索。
    *   管理文章标题、内容、发布日期、作者等元数据。

### c. 资源关联 (Resource Association)
*   **场景需求**：建立课程与教师、课程资源与文章、用户与评论的多维关联。
*   **后端实现 (`nkuwiki-module-course`, `nkuwiki-module-article`, `nkuwiki-module-comment`)**：
    *   课程模块 (`nkuwiki-module-course`) 管理课程信息，并关联到授课教师（`Teachers` 实体）。
    *   文章模块 (`nkuwiki-module-article`) 中的课程资源类型，通过外键关联到课程模块的课程实体。
    *   评论模块 (`nkuwiki-module-comment`) 将评论关联到具体的用户（`Users` 实体）和文章（`Articles` 实体）。

### d. 内容互动 (Content Interaction)
*   **场景需求**：支持用户对知识内容的评论与反馈。
*   **后端实现 (`nkuwiki-module-comment`)**：
    *   提供评论的发布、查看、删除（作者或管理员）接口。
    *   存储评论内容、评论时间、评论用户信息和被评论文章信息。

### e. 继承扩展 (Inheritance/Extension)
*   **场景需求**：实现用户角色（学生/教师）和内容类型（课程资源/普通文章）的继承关系。
*   **后端实现 (数据库设计与相应模块)**：
    *   **用户继承**：`Users` 表作为父表，`Students` 和 `Teachers` 表作为子表，通过共享主键（`user_id`）并作为外键实现继承。相关用户操作会涉及这些表的联动。
    *   **文章继承**：`Articles` 表作为父表，`CourseResources` 表作为子表，通过共享主键（`article_id` 作为 `CourseResources` 的 `resource_id`）并作为外键实现继承。课程资源的创建和管理会基于此结构。

## 3. 主要技术栈与模块

### a. 核心技术 (源自 RuoYi-Vue-Pro 并保留)
*   Spring Boot 2.7.x
*   Spring Security (权限控制)
*   MyBatis Plus (ORM)
*   MySQL 5.7+
*   Redis (缓存)
*   Druid (数据库连接池)
*   Swagger (Springdoc for API documentation)

### b. nkuwiki 后端模块划分

| 模块名                  | 主要职责 (对应 `nkuwiki` 应用场景)                               | 二次开发状态 |
|-------------------------|--------------------------------------------------------------------|------------|
| `yudao-common`          | 公共工具类、常量                                                      | 沿用       |
| `yudao-framework`       | 框架核心封装 (AOP, Web, Redis等)                                    | 沿用       |
| `yudao-server`          | Web服务启动、全局配置                                                 | 沿用       |
| `yudao-module-system`   | 核心系统管理API (用户、角色、菜单、字典、日志) - 为`nkuwiki-module-member`提供基础 | 核心保留   |
| `nkuwiki-module-member` | **nkuwiki 用户中心**：处理学生/教师注册、登录、个人信息 (用户管理场景)           | 新增/重构  |
| `nkuwiki-module-article`| **nkuwiki 文章与资源中心**：处理文章、课程资源的增删改查 (知识存储、资源关联场景)  | 新增/重构  |
| `nkuwiki-module-course` | **nkuwiki 课程中心**：管理课程信息，关联教师与资源 (资源关联场景)             | 新增/重构  |
| `nkuwiki-module-comment`| **nkuwiki 评论中心**：处理文章评论与回复 (内容互动场景)                  | 新增/重构  |

## 4. 快速上手

*   **环境要求**: JDK 1.8+, Maven 3.5+, MySQL 5.7+, Redis
*   **启动配置**: 详细配置请参考 `application.yml` 和 `application-xxx.yml` 文件，主要配置数据库连接和 Redis 连接。
*   **启动文档**: [TODO: 提供详细的后端项目本地部署和启动步骤文档链接]
*   **API接口**: 项目启动后，可通过 Swagger UI ([TODO: 提供Swagger UI访问路径, e.g., /doc.html]) 查看和测试API。

## 5. 开源协议

本项目基于 `RuoYi-Vue-Pro` 二次开发，其遵循 [MIT License](https://gitee.com/zhijiantianya/ruoyi-vue-pro/blob/master/LICENSE)。因此，`nkuwiki-back` 项目同样采用 **MIT License** 开源协议。
