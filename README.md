# suo-medical

一个医疗 SaaS 后端学习项目 —— 从 Spring Boot 单体起步,按"单体优先、逐步演进"的路线,一步步长成微服务 + 中间件 + AI 的完整后端系统。

> 目标:以真实工程的方式练透 Java 后端全栈,过程中每个技术都亲手引入、能讲清"为什么用、什么时候不用"。

## 业务主题

医疗 SaaS(患者 / 医生 / 挂号预约),模型刻意保持精简,专注技术练习。

## 技术演进路线

| 阶段 | 形态 | 主要技术 |
|------|------|----------|
| 1 单体基础 | 单个 Spring Boot 服务 | Spring Boot/MVC、MyBatis-Plus、MySQL、Redis、JWT、Knife4j |
| 2 工程化 | 健壮的单体 | 全局异常、统一返回体、参数校验、AOP 日志、事务、单元测试 |
| 3 微服务 | 拆分多服务 | Spring Cloud Alibaba(Nacos / OpenFeign / Gateway / Sentinel / Seata) |
| 4 中间件 | 解耦与搜索 | RabbitMQ、ElasticSearch |
| 5 AI 能力 | 智能化 | Spring AI / RAG |
| 6 部署运维 | 上线 | Docker、Jenkins CI/CD |

## 当前状态

🚧 阶段 1 · 开发中 —— Spring Boot 项目骨架已搭建。

## 技术栈

- Java 21
- Spring Boot 3.x
- Maven

## 许可证

[MIT](LICENSE)
