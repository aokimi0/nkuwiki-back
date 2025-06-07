# nkuwiki 知识共享平台 - 后端服务

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## 1. 项目定位与二次开发说明

本项目 **`nkuwiki-back`** 是 **`nkuwiki` 南开校园知识共享平台**的后端服务。它是在优秀的开源项目 [RuoYi-Vue-Pro](https://gitee.com/zhijiantianya/ruoyi-vue-pro) (由芋道源码团队开发，特此感谢) 的基础上进行的**二次开发**。

二次开发的核心目标是**精准服务于 `nkuwiki` 项目定义的应用场景**，对原项目进行了以下关键调整：

*   **聚焦核心需求**：根据 `nkuwiki` 项目需求，定制和新增了后端业务模块。
*   **数据库重新设计**：依据 `nkuwiki` 的ER图和关系模式，重新设计和实现了数据表结构，以支持用户、文章、课程、资源、评论及其特定的继承关系。
*   **API 接口适配**：调整和开发了符合 `nkuwiki-front` 前端需求的API接口。
*   **功能精简与增强**：在保留原有核心功能的基础上，新增了知识库管理等符合校园知识共享场景的专用模块。

## 2. 后端服务如何支撑 nkuwiki 应用场景

`nkuwiki-back` 通过以下模块和功能，为 `nkuwiki` 平台的核心应用场景提供支持：

### a. 用户管理 (User Management)
*   **场景需求**：支持学生、教师等不同角色用户的注册与身份管理。
*   **后端实现 (`yudao-module-member`, `yudao-module-system`)**：
    *   提供用户注册、登录、登出接口。
    *   基于 Spring Security 进行认证与授权，管理用户角色（学生、教师）及其权限。
    *   存储和管理用户基本信息、学号/工号、专业/院系等。
    *   支持用户个人信息的查询与修改。

### b. 知识存储与管理 (Knowledge Storage & Management)
*   **场景需求**：存储和管理课程资料、学术文章、校园资讯等多类型知识内容。
*   **后端实现 (`yudao-module-knowledge`)**：
    *   提供文章的创建、编辑、发布、删除等完整生命周期管理。
    *   支持文章内容的存储和检索，包括标题、内容、发布时间、作者等信息。
    *   支持评论系统，实现用户对知识内容的互动反馈。
    *   提供分页查询、批量操作、Excel导出等高效的数据管理功能。
    *   集成权限控制，确保内容访问的安全性。

### c. 系统管理 (System Management)
*   **场景需求**：提供完整的后台管理功能，支持平台的日常运维。
*   **后端实现 (`yudao-module-system`, `yudao-module-infra`)**：
    *   用户权限管理：角色分配、菜单管理、权限控制。
    *   系统监控：操作日志、登录日志、系统性能监控。
    *   基础设施：文件管理、代码生成、定时任务管理。

### d. 业务扩展模块 (Business Extensions)
*   **多样化业务支持**：
    *   **CRM模块** (`yudao-module-crm`)：客户关系管理，可用于校友关系维护。
    *   **ERP模块** (`yudao-module-erp`)：企业资源计划，支持校园资源管理。
    *   **支付模块** (`yudao-module-pay`)：支付服务，支持付费课程等增值服务。
    *   **商城模块** (`yudao-module-mall`)：在线商城，支持教材、用品销售。
    *   **IoT模块** (`yudao-module-iot`)：物联网设备管理，支持智慧校园建设。
    *   **AI模块** (`yudao-module-ai`)：人工智能服务，提供智能推荐等功能。
    *   **BPM模块** (`yudao-module-bpm`)：业务流程管理，支持校园审批流程。
    *   **报表模块** (`yudao-module-report`)：数据报表生成，支持数据分析。
    *   **公众号模块** (`yudao-module-mp`)：微信公众号集成，扩展移动端服务。

## 3. 主要技术栈与架构

### a. 核心技术 (源自 RuoYi-Vue-Pro 并保留)
*   Spring Boot 2.7.x
*   Spring Security (权限控制)
*   MyBatis Plus (ORM)
*   MySQL 5.7+ / PostgreSQL / Oracle 等多数据库支持
*   Redis (缓存)
*   Druid (数据库连接池)
*   Swagger (API文档)
*   Excel处理 (导入导出)

### b. 项目模块架构

| 模块名                     | 主要职责                                                          | 开发状态 |
|---------------------------|-------------------------------------------------------------------|----------|
| `yudao-dependencies`      | 统一依赖管理                                                      | 核心保留 |
| `yudao-framework`         | 框架核心封装 (Web, 安全, 数据库, 缓存等)                           | 核心保留 |
| `yudao-server`            | Web服务启动入口、全局配置                                          | 核心保留 |
| `yudao-module-system`     | 系统管理 (用户、角色、菜单、权限、日志等)                          | 核心保留 |
| `yudao-module-infra`      | 基础设施 (文件管理、代码生成、监控等)                              | 核心保留 |
| **`yudao-module-knowledge`** | **知识库管理 (文章管理、评论系统)** - **nkuwiki核心功能模块**    | **新增** |
| `yudao-module-member`     | 会员管理 (用户中心、积分等)                                        | 功能保留 |
| `yudao-module-pay`        | 支付服务 (多渠道支付、订单管理)                                    | 功能保留 |
| `yudao-module-mall`       | 商城服务 (商品、订单、促销等)                                      | 功能保留 |
| `yudao-module-crm`        | 客户关系管理                                                      | 功能保留 |
| `yudao-module-erp`        | 企业资源计划                                                      | 功能保留 |
| `yudao-module-bpm`        | 工作流引擎                                                        | 功能保留 |
| `yudao-module-iot`        | 物联网设备管理                                                    | 功能保留 |
| `yudao-module-ai`         | AI人工智能服务                                                    | 功能保留 |
| `yudao-module-report`     | 报表服务                                                          | 功能保留 |
| `yudao-module-mp`         | 微信公众号管理                                                    | 功能保留 |

### c. 核心特色

*   **多数据库支持**：MySQL、PostgreSQL、Oracle、SQL Server 等
*   **多租户架构**：支持 SaaS 多租户部署
*   **前后端分离**：提供完整的 RESTful API
*   **权限控制**：基于 RBAC 的细粒度权限管理
*   **代码生成**：支持快速生成 CRUD 代码
*   **监控运维**：集成监控、日志、性能分析
*   **云原生支持**：支持 Docker、K8s 部署

## 4. nkuwiki 知识库核心功能

### 文章管理系统
*   **文章生命周期管理**：创建、编辑、发布、删除
*   **内容管理**：支持富文本内容存储
*   **元数据管理**：标题、作者、发布时间、来源平台
*   **批量操作**：支持批量删除、导出等操作
*   **权限控制**：基于角色的访问控制

### 评论互动系统
*   **评论管理**：发布、回复、删除评论
*   **用户关联**：评论与用户、文章的关联关系
*   **时间管理**：评论时间戳记录
*   **内容审核**：支持评论内容的管理和审核

### 数据导出功能
*   **Excel导出**：支持文章和评论数据的Excel导出
*   **分页查询**：高效的分页数据检索
*   **条件筛选**：支持多条件组合查询

## 5. 快速上手

### 环境要求
*   **JDK**: 1.8+ (推荐 JDK 11)
*   **Maven**: 3.6+
*   **数据库**: MySQL 5.7+ / PostgreSQL 12+
*   **缓存**: Redis 5.0+
*   **Node.js**: 14+ (前端开发需要)

### 本地开发启动
1. **克隆项目**
   ```bash
   git clone [项目地址]
   cd nkuwiki-back
   ```

2. **配置数据库**
   - 创建数据库并导入初始化脚本 (位于 `sql/` 目录)
   - 修改 `application-dev.yml` 中的数据库连接配置

3. **配置Redis**
   - 启动 Redis 服务
   - 修改 `application-dev.yml` 中的 Redis 连接配置

4. **启动项目**
   ```bash
   mvn clean install
   mvn spring-boot:run -pl yudao-server
   ```

5. **访问系统**
   - 后端服务：http://localhost:48080
   - API文档：http://localhost:48080/doc.html
   - 管理后台：需要启动前端项目

### Docker 部署
```bash
# 构建镜像
docker build -t nkuwiki-back .

# 启动服务
docker-compose up -d
```

## 6. API 文档

项目集成了 Swagger3 (Springdoc)，启动后可通过以下地址访问：
*   **Swagger UI**: http://localhost:48080/doc.html
*   **OpenAPI JSON**: http://localhost:48080/v3/api-docs

主要 API 模块：
*   **知识库 API**: `/knowledge/**` - 文章和评论管理
*   **系统管理 API**: `/system/**` - 用户权限管理
*   **基础设施 API**: `/infra/**` - 文件上传等基础服务

## 7. 开源协议

本项目基于 `RuoYi-Vue-Pro` 二次开发，遵循 [MIT License](https://gitee.com/zhijiantianya/ruoyi-vue-pro/blob/master/LICENSE)。因此，`nkuwiki-back` 项目同样采用 **MIT License** 开源协议。

---

## 致谢

感谢芋道源码团队开发的优秀开源项目 [RuoYi-Vue-Pro](https://gitee.com/zhijiantianya/ruoyi-vue-pro)，为本项目提供了坚实的技术基础。
