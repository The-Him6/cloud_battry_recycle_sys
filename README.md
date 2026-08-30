# 绿源回收积分平台（微服务版）

将单体「绿源回收积分平台」按业务边界拆分为**9 个独立模块**（7 业务服务 + 1 公共模块 + 1 远程调用接口），实践服务拆分、服务治理、网关鉴权与分布式事务。

## 模块结构

| 模块 | 端口 | 说明 |
|---|---|---|
| **br-gateway** | 8080 | 网关：统一入口、JWT 鉴权、白名单放行、用户信息透传 |
| **br-user** | 8081 | 用户服务：注册登录、JWT 签发、用户管理 |
| **br-points** | 8082 | 积分服务：用户积分账户与流水管理 |
| **br-recycle** | 8083 | 回收服务：回收订单、电池种类、数据统计 |
| **br-exchange** | 8084 | 兑换服务：积分商品、兑换记录、秒杀券核销 |
| **br-seckill** | 8085 | 秒杀服务：秒杀活动、预热库存、异步发券 |
| **br-notice** | 8086 | 公告服务：系统公告、已读管理 |
| **br-api** | — | 远程调用接口模块：OpenFeign Client + DTO 定义 |
| **br-common** | — | 公共模块：异常处理、常量、工具类、缓存组件 |

## 技术栈

| 技术 | 说明 |
|---|---|
| **Spring Boot 3.2 / Spring Cloud Alibaba** | 微服务基础框架 |
| **Nacos** | 服务注册发现 + 配置中心 |
| **Gateway** | 统一网关（路由、鉴权、透传） |
| **OpenFeign** | 跨服务远程调用 |
| **Sentinel** | 服务流控与熔断降级 |
| **Seata** | 分布式事务（AT 模式） |
| **MyBatis** | 数据持久化 |
| **MySQL 8.0** | 数据库 |
| **Redis / Redisson** | 缓存、登录态、秒杀预扣、分布式锁 |
| **RabbitMQ** | 秒杀异步发券消息队列 |
| **JWT (jjwt 0.12)** | 登录认证 |
| **Knife4j** | OpenAPI 3 接口文档 |
| **Lombok + Hutool** | 代码简化 |

## 环境要求

| 组件 | 版本要求 | 用途 |
|---|---|---|
| JDK | 17+ | 运行环境 |
| Maven | 3.8+ | 项目构建 |
| MySQL | 8.0+ | 数据存储 |
| Redis | 6+ | 缓存与秒杀 |
| RabbitMQ | 3+ | 消息队列 |
| Nacos | 2.x | 注册中心与配置中心 |
| Sentinel Dashboard | 可选 | 流控规则可视化配置 |
| Seata Server | 可选 | 分布式事务协调器 |

## 拆分大致步骤


1. **梳理依赖、确定边界**：盘点单体中跨域耦合点（订单读用户、兑换调积分、秒杀调积分等），按业务域划分 7 个服务 + 2 个 jar 模块，每服务独占自己的库和表，禁止跨库 Join。
2. **搭建公共模块 `br-common`**：抽离 `Result`、异常体系、`UserContext`/`UserInfoInterceptor`、常量、缓存、OSS 上传等公共能力，用 `AutoConfiguration.imports` 自动装配（Boot 3 不再用 `spring.factories`）。
3. **搭建 API 契约模块 `br-api`**：定义 Feign Client + 跨服务 DTO（如 `UserClient`、`UserPointsClient`、`UserSeckillCouponClient`），业务服务之间只共享 DTO，不共享 Mapper。
4. **逐服务拆分并跑通**：按 br-user → br-points → br-recycle → br-exchange → br-seckill → br-notice 顺序，把对应 Controller/Service/Mapper 迁入独立模块，跨库调用改为 Feign，统计并入 br-recycle，`battery_type` 归属 br-recycle。
5. **接入网关 `br-gateway`**：统一路由（`/api/**` 分发）、JWT 鉴权 + 白名单放行，解析后以 `user-info` 请求头向下游透传用户身份；服务内去掉 `context-path: /api` 避免路径重复。
6. **Nacos 共享配置**：把数据源、Redis、日志、swagger、OSS、Seata 等公共配置抽到 Nacos 共享配置，按服务名动态刷新；端口、`br.db.database`、JWT、Sentinel、路由等本服务特有配置留在本地。
7. **分布式事务**：秒杀链路保持 MQ 异步 + Redis 补偿（不强一致）；仅同步强一致场景引入 Seata AT（`@GlobalTransactional` 加在入口方法），TC 与客户端版本/分组保持一致。
8. **全链路验收**：每个服务可独立启动、走通自己接口，网关统一入口后全链路可用。

## Nacos 共享配置

启动前需在 Nacos 配置中心（默认 `192.168.150.102:8850`）准备以下共享配置：

| Data ID | 说明 |
|---|---|
| shared-jdbc.yaml | 数据源与 MyBatis 配置 |
| shared-redis.yaml | Redis 连接与连接池配置 |
| shared-common.yaml | 通用配置（Jackson、文件上传限制等） |
| shared-log.yaml | 日志级别与输出路径 |
| shared-swagger.yaml | Knife4j 接口文档配置 |

## 前端地址

前端项目与单体共用同一套 `frontend/` 目录（Vue3 + Vite），**不需要额外改造**：

- 启动：`cd frontend && npm install && npm run dev`
- 访问：`http://localhost:3000`
- 代理：Vite 将 `/api` 代理到网关 `http://localhost:8080`，由网关路由到各业务服务

> 说明：前后端通过 `/api` 前缀解耦，前端只认网关地址，不关心后端具体拆了几个服务。

## 快速启动

```bash
# 1. 编译公共模块与 API 模块
mvn install -pl br-common,br-api -am -DskipTests

# 2. 按依赖顺序启动服务（可在 IDE 中逐步启动）
# 建议顺序：br-user → br-points → br-recycle → br-exchange → br-seckill → br-notice → br-gateway

# 3. 启动网关后访问接口文档
# http://localhost:8080/doc.html
```


